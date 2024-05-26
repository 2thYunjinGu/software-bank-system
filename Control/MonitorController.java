package Control;

import View.MonitorView;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MonitorController {
    private MonitorView monitorView;
    private User user;
    private JFrame frame;

    public MonitorController(MonitorView monitorView, User user) {
        this.monitorView = monitorView;
        this.user = user;
        this.frame = monitorView.getFrame();
        initView();
        frame.setVisible(true);
    }

    private void initView() {
        monitorView.getDepositLimitButton().addActionListener(e -> handleDepositLimit());
        monitorView.getWithdrawLimitButton().addActionListener(e -> handleWithdrawLimit());
        monitorView.getReviewTransactionsButton().addActionListener(e -> {
            try {
                displayTransactionHistory();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        monitorView.getLockAccountButton().addActionListener(e -> {
            try {
                LockAccount();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        monitorView.getUnlockAccountButton().addActionListener(e -> {
            try {
                UnlockAccount();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    void handleDepositLimit() {
        String depositLimitString = JOptionPane.showInputDialog(frame, "输入新的存款限额:");
        if (depositLimitString != null && !depositLimitString.isEmpty()) {
            handleDepositLimit(depositLimitString);
        }
    }

    void handleDepositLimit(String depositLimitString) {
        try {
            double newDepositLimit = Double.parseDouble(depositLimitString);
            System.out.println(newDepositLimit);
            String data = new String(Files.readAllBytes(Paths.get("users.json")), StandardCharsets.UTF_8);
            JSONArray usersArray = new JSONArray(data);
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                if (userJson.getString("username").equals(user.getUsername())) {
                    JSONArray familyMembersJsonArray = userJson.getJSONArray("familyMembers");
                    List<String> familyMembers = new ArrayList<>();
                    for (int j = 0; j < familyMembersJsonArray.length(); j++) {
                        familyMembers.add(familyMembersJsonArray.getString(j));
                    }
                    for (String familyMember : familyMembers) {
                        for (int k = 0; k < usersArray.length(); k++) {
                            JSONObject familyMemberJson = usersArray.getJSONObject(k);
                            if (familyMemberJson.getString("username").equals(familyMember)) {
                                if (familyMemberJson.getString("userType").equals("Child")) {
                                    familyMemberJson.put("maxDepositLimit", newDepositLimit);
                                    System.out.println(familyMemberJson.getString("username") + "存款限额设置成功!");
                                }
                            }
                        }
                    }
                    break; // 找到用户并更新后，跳出循环
                }
            }
            Files.write(Paths.get("users.json"), usersArray.toString().getBytes(StandardCharsets.UTF_8));
            JOptionPane.showMessageDialog(frame, "存款限额设置成功!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "无效输入! 请输入一个有效的数字。", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void handleWithdrawLimit() {
        String withdrawLimitString = JOptionPane.showInputDialog(frame, "输入新的取款限额:");
        if (withdrawLimitString != null && !withdrawLimitString.isEmpty()) {
            handleWithdrawLimit(withdrawLimitString);
        }
    }

    void handleWithdrawLimit(String withdrawLimitString) {
        try {
            double newWithdrawLimit = Double.parseDouble(withdrawLimitString);
            System.out.println(newWithdrawLimit);
            String data = new String(Files.readAllBytes(Paths.get("users.json")), StandardCharsets.UTF_8);
            JSONArray usersArray = new JSONArray(data);
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                if (userJson.getString("username").equals(user.getUsername())) {
                    JSONArray familyMembersJsonArray = userJson.getJSONArray("familyMembers");
                    List<String> familyMembers = new ArrayList<>();
                    for (int j = 0; j < familyMembersJsonArray.length(); j++) {
                        familyMembers.add(familyMembersJsonArray.getString(j));
                    }
                    for (String familyMember : familyMembers) {
                        for (int k = 0; k < usersArray.length(); k++) {
                            JSONObject familyMemberJson = usersArray.getJSONObject(k);
                            if (familyMemberJson.getString("username").equals(familyMember)) {
                                if (familyMemberJson.getString("userType").equals("Child")) {
                                    familyMemberJson.put("maxWithdrawLimit", newWithdrawLimit);
                                    System.out.println(familyMemberJson.getString("username") + "取款限额设置成功!");
                                }
                            }
                        }
                    }
                    break; // 找到用户并更新后，跳出循环
                }
            }
            Files.write(Paths.get("users.json"), usersArray.toString().getBytes(StandardCharsets.UTF_8));
            JOptionPane.showMessageDialog(frame, "取款限额设置成功!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "无效输入! 请输入一个有效的数字。", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void LockAccount() throws IOException {
        JOptionPane.showMessageDialog(null, "Lock success!", "success", JOptionPane.INFORMATION_MESSAGE);
        String data = new String(Files.readAllBytes(Paths.get("users.json")), StandardCharsets.UTF_8);
        JSONArray usersArray = new JSONArray(data);
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userJson = usersArray.getJSONObject(i);
            if (userJson.getString("username").equals(user.getUsername())) {
                JSONArray familyMembersJsonArray = userJson.getJSONArray("familyMembers");
                List<String> familyMembers = new ArrayList<>();
                for (int j = 0; j < familyMembersJsonArray.length(); j++) {
                    familyMembers.add(familyMembersJsonArray.getString(j));
                }
                for (String familyMember : familyMembers) {
                    for (int k = 0; k < usersArray.length(); k++) {
                        JSONObject familyMemberJson = usersArray.getJSONObject(k);
                        if (familyMemberJson.getString("username").equals(familyMember)) {
                            if (familyMemberJson.getString("userType").equals("Child")) {
                                familyMemberJson.put("isLocked", true);
                                System.out.println(familyMemberJson.getString("username") + "锁定成功!");
                            }
                        }
                    }
                }
                break; // 找到用户并更新后，跳出循环
            }
        }
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes(StandardCharsets.UTF_8));
    }

    void UnlockAccount() throws IOException {
        JOptionPane.showMessageDialog(null, "Unlock success!", "success", JOptionPane.INFORMATION_MESSAGE);
        String data = new String(Files.readAllBytes(Paths.get("users.json")), StandardCharsets.UTF_8);
        JSONArray usersArray = new JSONArray(data);
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userJson = usersArray.getJSONObject(i);
            if (userJson.getString("username").equals(user.getUsername())) {
                JSONArray familyMembersJsonArray = userJson.getJSONArray("familyMembers");
                List<String> familyMembers = new ArrayList<>();
                for (int j = 0; j < familyMembersJsonArray.length(); j++) {
                    familyMembers.add(familyMembersJsonArray.getString(j));
                }
                for (String familyMember : familyMembers) {
                    for (int k = 0; k < usersArray.length(); k++) {
                        JSONObject familyMemberJson = usersArray.getJSONObject(k);
                        if (familyMemberJson.getString("username").equals(familyMember)) {
                            if (familyMemberJson.getString("userType").equals("Child")) {
                                familyMemberJson.put("isLocked", false);
                                System.out.println(familyMemberJson.getString("username") + "解锁成功!");
                            }
                        }
                    }
                }
                break; // 找到用户并更新后，跳出循环
            }
        }
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes(StandardCharsets.UTF_8));
    }

    void displayTransactionHistory() throws IOException {
        StringBuilder history = new StringBuilder("<html>Transaction history: <br>");

        String data = new String(Files.readAllBytes(Paths.get("users.json")), StandardCharsets.UTF_8);
        JSONArray usersArray = new JSONArray(data);
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userJson = usersArray.getJSONObject(i);
            if (userJson.getString("username").equals(user.getUsername())) {
                JSONArray familyMembersJsonArray = userJson.getJSONArray("familyMembers");
                List<String> familyMembers = new ArrayList<>();
                for (int j = 0; j < familyMembersJsonArray.length(); j++) {
                    familyMembers.add(familyMembersJsonArray.getString(j));
                }
                for (String familyMember : familyMembers) {
                    for (int k = 0; k < usersArray.length(); k++) {
                        JSONObject familyMemberJson = usersArray.getJSONObject(k);
                        if (familyMemberJson.getString("username").equals(familyMember)) {
                            JSONArray transactionsJsonArray = familyMemberJson.getJSONArray("transactions");
                            history.append(familyMemberJson.getString("username"))
                                    .append(" transactions:<br>");
                            for (int l = 0; l < transactionsJsonArray.length(); l++) {
                                JSONObject transactionJson = transactionsJsonArray.getJSONObject(l);
                                history.append(transactionJson.getString("type"))
                                        .append(" - ")
                                        .append(String.format("%.2f", transactionJson.getDouble("amount")))
                                        .append(" 元 (")
                                        .append(transactionJson.getString("timestamp"))
                                        .append(") - 余额: ")
                                        .append(familyMemberJson.getDouble("balance"))
                                        .append(" 元<br>");
                            }
                            System.out.println(familyMemberJson.getString("username") + "取款记录查看成功!");
                        }
                    }
                }
                break; // 找到用户并更新后，跳出循环
            }
        }
        history.append("</html>");
        JOptionPane.showMessageDialog(frame, history.toString(), "Transaction history: ", JOptionPane.INFORMATION_MESSAGE);
    }
}
