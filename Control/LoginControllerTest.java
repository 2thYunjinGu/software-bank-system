package Control;

import Model.User;
import View.LoginView;
import View.RegisterView;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginControllerTest {

    private LoginView loginView;
    private RegisterView registerView;
    private LoginController loginController;

    @Before
    public void setUp() throws IOException {
        // 初始化视图对象
        loginView = new LoginView();
        registerView = new RegisterView();

        // 创建测试用户并写入到 users.json 文件
        createTestUser();

        // 初始化 LoginController 实例
        loginController = new LoginController(loginView);
    }


    private void createTestUser() throws IOException {
        User testUser = new User("testUser", "testPassword", "Parent", new ArrayList<>(), "USD", 1000);
        String userData = String.format(
                "[{\"username\": \"%s\", \"password\": \"%s\", \"userType\": \"%s\", \"familyMembers\": [], \"currency\": \"%s\", \"balance\": %f}]",
                testUser.getUsername(), testUser.getPassword(), testUser.getUserType(), testUser.getCurrency(), testUser.getBalance()
        );
        Files.write(Paths.get("users.json"), userData.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void testPerformLoginSuccess() {
        // 设置测试数据
        String testUsername = "testUser";
        String testPassword = "testPassword";

        // 设置用户输入
        loginView.getUsernameField().setText(testUsername);
        loginView.getPasswordField().setText(Arrays.toString(testPassword.toCharArray()));

        // 执行登录操作
        loginController.performLogin();

        // 验证用户登录成功
        if (loginView.isVisible()) {
            System.out.println("Login view is still visible. Login might have failed.");
        } else {
            System.out.println("Login view is hidden. Login succeeded.");
        }
        assertFalse("Login view should be hidden after successful login.", loginView.isVisible());
    }

    @Test
    public void testPerformLoginFailure() {
        // 设置测试数据
        String testUsername = "testUser";
        String testPassword = "wrongPassword";

        // 设置用户输入
        loginView.getUsernameField().setText(testUsername);
        loginView.getPasswordField().setText(Arrays.toString(testPassword.toCharArray()));

        // 执行登录操作
        loginController.performLogin();

        // 验证用户登录失败
        if (!loginView.isVisible()) {
            System.out.println("Login view is hidden. Login might have succeeded unexpectedly.");
        } else {
            System.out.println("Login view is still visible. Login failed as expected.");
        }
        assertTrue("Login view should still be visible after failed login.", loginView.isVisible());
    }

    @Test
    public void testPerformRegistration() {
        // 执行注册操作
        loginController.performRegistration();

        // 验证注册视图显示
        assertFalse(loginView.isVisible());
        assertTrue(registerView.isVisible());
    }
}


