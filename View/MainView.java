package View;
import Model.User;
import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JLabel userInfoLabel;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transactionHistoryButton;
    private JButton taskButton;
    private JButton studyButton;
    private JButton shopButton;
    private JButton regulationButton;

    public MainView(User user) {
        initializeUI(user.getUsername());
    }

    private void initializeUI(String username) {
        setTitle("Main Page");
        setSize(600, 400); // 设置窗口大小
        setLayout(null);
        setLocationRelativeTo(null); // 窗口居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel_1 = new JPanel(new GridLayout(2, 2));
        JPanel panel_2 = new JPanel();
        JPanel panel_3 = new JPanel();

        panel_1.setBounds(0, 60, 600, 200);
        panel_2.setBounds(0, 300, 600, 50);
        panel_3.setBounds(0, 0, 600, 50);

        // 用户信息显示
        userInfoLabel = new JLabel("Username: " + username);
        panel_3.add(userInfoLabel);

        // 存款按钮
        depositButton = new JButton("Deposit");
        panel_2.add(depositButton);

        // 取款按钮
        withdrawButton = new JButton("Withdraw");
        panel_2.add(withdrawButton);

        // 交易历史按钮
        transactionHistoryButton = new JButton("Transaction History");
        panel_2.add(transactionHistoryButton);

        // 任务按钮
        taskButton = new JButton("Task");
        panel_1.add(taskButton);

        // 学习按钮
        studyButton = new JButton("Study");
        panel_1.add(studyButton);

        // 商店按钮
        shopButton = new JButton("Shop");
        panel_1.add(shopButton);

        // 监管按钮
        regulationButton = new JButton("Regulation");
        panel_1.add(regulationButton);

        add(panel_3);
        add(panel_1);
        add(panel_2);
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
}