package View;
import Model.User;
import javax.swing.*;
import java.awt.*;

public class ParentMainView extends JFrame {
    private JLabel userNameLabel;
    private JLabel userTypeLabel;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transactionHistoryButton;
    private JButton taskButton;
    private JButton studyButton;
    private JButton shopButton;
    private JButton regulationButton;

    private JButton returnToLoginButton;

    public ParentMainView(User user) {
        initializeUI(user.getUsername(), user.getUserType());
    }

    private void initializeUI(String username, String userType) {
        int width = 400;
        int height = 300;
        setTitle("Main Page for Parent");
        setSize(width, height); // 设置窗口大小
        setLayout(null);
        setLocationRelativeTo(null); // 窗口居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p2 = new JPanel(new GridLayout(2, 2));
        JPanel p3 = new JPanel();
        JPanel p1 = new JPanel(new GridLayout(2, 1));

        p2.setBounds(0, 50, width, 100);
        p3.setBounds(0, 150, width, 50);
        p1.setBounds(0, 0, width, 50);

        // 用户信息显示
        userNameLabel = new JLabel("Username: " + username);
        userTypeLabel = new JLabel("User Type: " + userType);

        p1.add(userNameLabel);
        p1.add(userTypeLabel);

        // 存款按钮
        depositButton = new JButton("Deposit");
        p3.add(depositButton);

        // 取款按钮
        withdrawButton = new JButton("Withdraw");
        p3.add(withdrawButton);

        // 交易历史按钮
        transactionHistoryButton = new JButton("Transaction History");
        p3.add(transactionHistoryButton);

        returnToLoginButton = new JButton("Return");
        p1.add(returnToLoginButton); // 添加返回登录按钮到panel
        // 任务按钮
        taskButton = new JButton("Task");
        p2.add(taskButton);

        // 学习按钮
        studyButton = new JButton("Study");
        p2.add(studyButton);

        // 商店按钮
        shopButton = new JButton("Shop");
        p2.add(shopButton);

        // 监管按钮
        regulationButton = new JButton("Regulation");
        p2.add(regulationButton);

        add(p1);
        add(p2);
        add(p3);
    }

    public JButton getDepositButton() {
        return depositButton;
    }

    public JButton getWithdrawButton() {
        return withdrawButton;
    }

    public JButton getTransactionHistoryButton() {
        return transactionHistoryButton;
    }

    public JButton getTaskButton() {
        return taskButton;
    }

    public JButton getStudyButton() {
        return studyButton;
    }

    public JButton getShopButton() {
        return shopButton;
    }

    public JButton getRegulationButton() {
        return regulationButton;
    }
    public JButton getReturnToLoginButton() {
        return returnToLoginButton;
    }

}
