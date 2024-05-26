package Control;

import View.ChildMainView;
import View.LoginView;
import View.MonitorView;
import View.ParentMainView;
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

    void initView() {
        loginView.getLoginButton().addActionListener(e -> performLogin());
        loginView.getRegisterButton().addActionListener(e -> performRegistration());
        loginView.setVisible(true);
        registerView.setVisible(false);
    }

    void performLogin() {
        String username = loginView.getUsernameField().getText();
        String password = new String(loginView.getPasswordField().getPassword());
        User user = User.validate(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(loginView, "Log in successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            loginView.setVisible(false);
            System.out.println(user.getUserType());
            if (user.getUserType().equals("Parent") ){
                ParentMainView parentMainView = new ParentMainView(user);
                ParentMainController parentMainController = new ParentMainController(parentMainView, user);
                // MonitorView monitorView = new MonitorView();
                // MonitorController monitorController = new MonitorController(monitorView,user);

            }else{
                ChildMainView childMainView = new ChildMainView(user);
                childMainView.setVisible(true);
                ChildMainController childMainController = new ChildMainController(childMainView, user);
                // MainView mainView = new MainView(user);
                // mainView.setVisible(true);
                // MainController mainController = new MainController(mainView,user);
            }

        } else {
            JOptionPane.showMessageDialog(loginView, "Failed to log in: Incorrect username or password", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    void performRegistration() {
        loginView.setVisible(false);
        registerView.setVisible(true);
    }
}
