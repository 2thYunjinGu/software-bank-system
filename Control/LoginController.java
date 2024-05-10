package Control;

import View.LoginView;
import View.RegisterView;
import View.MainView;
import Model.User;

import javax.swing.*;

public class LoginController {
    private LoginView loginView;
    private RegisterView registerView;
    private RegisterController registerController;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.registerView = new RegisterView();
        this.registerController = new RegisterController(registerView, new User(), loginView);
        initView();
    }

    private void initView() {
        loginView.getLoginButton().addActionListener(e -> performLogin());
        loginView.getRegisterButton().addActionListener(e -> performRegistration());
        loginView.setVisible(true);
        registerView.setVisible(false);
    }

    private void performLogin() {
        String username = loginView.getUsernameField().getText();
        String password = new String(loginView.getPasswordField().getPassword());
        User user = User.validate(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(loginView, "Log in successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            loginView.setVisible(false);
            MainView mainView = new MainView(user);
            mainView.setVisible(true);
            MainController mainController = new MainController(mainView,user);
        } else {
            JOptionPane.showMessageDialog(loginView, "Failed to log in: Incorrect username or password", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performRegistration() {
        loginView.setVisible(false);
        registerView.setVisible(true);
    }
}
