package Control;

import Model.User;
import View.RegisterView;
import View.LoginView;
import javax.swing.*;

public class RegisterController {
    private RegisterView registerView;
    private User user;
    private LoginView loginView;

    public RegisterController(RegisterView registerView, User user, LoginView loginView) {
        this.registerView = registerView;
        this.user = user;
        this.loginView = loginView;
        initController();
    }

    private void initController() {
        registerView.getRegisterButton().addActionListener(e -> performRegistration());
        registerView.getBackButton().addActionListener(e -> goBackToLogin());
        registerView.getAddFamilyMemberButton().addActionListener(e -> addFamilyMember());
        registerView.setVisible(true);
    }

    private void performRegistration() {
        String username = registerView.getUsername();
        String password = registerView.getPassword();
        String userType = registerView.getUserType();
        java.util.List<String> familyMembers = registerView.getFamilyMembers();
        String currency = registerView.getCurrency();

        // 假设注册时账户初始余额为0
        double initialBalance = 0.0;

        // 在注册用户时创建新的 User 对象
        User newUser = new User(username, password, userType, familyMembers, currency, initialBalance);

        boolean success = User.registerUser("users.json", newUser);


        if (success) {
            JOptionPane.showMessageDialog(registerView, "Register successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            goBackToLogin();
        } else {
            JOptionPane.showMessageDialog(registerView, "Fail to register, please check your input or enter again later.", "ERROR", JOptionPane.ERROR_MESSAGE); //注册失败，请检查输入或稍后重试。
        }
    }

    private void addFamilyMember() {
        String memberName = registerView.getFamilyMemberNameField().getText();
        if (!memberName.isEmpty()) {
            registerView.getFamilyMembersListModel().addElement(memberName);
            registerView.getFamilyMemberNameField().setText("");
        } else {
            JOptionPane.showMessageDialog(registerView, "The member name can't be empty!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void goBackToLogin() {
        // 假设有一个返回登录界面的方法
        // 此处应该实现视图的转换，例如关闭注册窗口，打开登录窗口
        registerView.setVisible(false);
        loginView.setVisible(true); // 假定 loginView 是已经实例化的登录视图
    }
}
