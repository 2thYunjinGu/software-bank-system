package Control;

import Model.User;
import View.MonitorView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MonitorControllerTest {
    private MonitorController monitorController;
    private MonitorView monitorView;
    private User user;

    @Before
    public void setUp() throws IOException {
        // 初始化用户和视图
        user = new User();
        user.setUsername("testUser");

        monitorView = new MonitorView();
        monitorController = new MonitorController(monitorView, user);

        // 创建测试用户数据
        String data = createTestUserData();
        Files.write(Paths.get("users.json"), data.getBytes());
    }

    @Test
    public void testHandleDepositLimit() throws IOException {
        // 模拟用户输入
        String depositLimit = "5000";

        // 调用方法
        monitorController.handleDepositLimit(depositLimit);

        // 读取文件并验证
        String updatedData = new String(Files.readAllBytes(Paths.get("users.json")));
        JSONArray usersArray = new JSONArray(updatedData);
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userJson = usersArray.getJSONObject(i);
            if (userJson.getString("username").equals("childUser")) {
                assertEquals(5000.0, userJson.getDouble("maxDepositLimit"), 0.1);
                break;
            }
        }
    }

    @Test
    public void testLockAccount() throws IOException {
        // 调用方法
        monitorController.LockAccount();

        // 读取文件并验证
        String updatedData = new String(Files.readAllBytes(Paths.get("users.json")));
        JSONArray usersArray = new JSONArray(updatedData);
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userJson = usersArray.getJSONObject(i);
            if (userJson.getString("username").equals("childUser")) {
                assertTrue(userJson.getBoolean("isLocked"));
                break;
            }
        }
    }

    @Test
    public void testDisplayTransactionHistory() throws IOException {
        // 创建含有交易记录的测试数据
        String data = createTestUserDataWithTransactions();
        Files.write(Paths.get("users.json"), data.getBytes());

        // 调用方法
        monitorController.displayTransactionHistory();

    }

    // Helper 方法，用于创建测试数据
    private String createTestUserData() {
        JSONArray usersArray = new JSONArray();
        JSONObject testUser = new JSONObject();
        testUser.put("username", "testUser");

        JSONArray familyMembers = new JSONArray();
        familyMembers.put("childUser");
        testUser.put("familyMembers", familyMembers);

        JSONObject childUser = new JSONObject();
        childUser.put("username", "childUser");
        childUser.put("userType", "Child");
        childUser.put("maxDepositLimit", 0);
        childUser.put("isLocked", false);

        usersArray.put(testUser);
        usersArray.put(childUser);

        return usersArray.toString();
    }

    private String createTestUserDataWithTransactions() {
        JSONArray usersArray = new JSONArray();
        JSONObject testUser = new JSONObject();
        testUser.put("username", "testUser");

        JSONArray familyMembers = new JSONArray();
        familyMembers.put("childUser");
        testUser.put("familyMembers", familyMembers);

        JSONObject childUser = new JSONObject();
        childUser.put("username", "childUser");
        childUser.put("userType", "Child");
        childUser.put("balance", 1000.0);

        JSONArray transactions = new JSONArray();
        JSONObject transaction = new JSONObject();
        transaction.put("type", "Deposit");
        transaction.put("amount", 1000);
        transaction.put("timestamp", "2024-05-25T12:34:56");
        transactions.put(transaction);

        childUser.put("transactions", transactions);

        usersArray.put(testUser);
        usersArray.put(childUser);

        return usersArray.toString();
    }
}
