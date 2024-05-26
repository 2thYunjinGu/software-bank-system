package View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.User;

public class ChildMainView extends JFrame {
    private JLabel userNameLabel;
    private JLabel userTypeLabel;
//    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transactionHistoryButton;
    private JButton studyButton;
    private JButton returnToLoginButton;


    public ChildMainView(User user) {
        initializeUI(user.getUsername(), user.getUserType());
    }

    private void initializeUI(String username, String userType) {
        int width = 400;
        int height = 400;
        setTitle("Main Page for Child");
        setSize(width, height); // 设置窗口大小
        setLayout(null);
        setLocationRelativeTo(null); // 窗口居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel(new GridLayout(2, 1));
        p1.setBounds(0, 0, width, 50);
        JPanel p2 = new JPanel(new GridLayout(4, 1));
        p2.setBounds(0, 50, width, 250);


//        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transactionHistoryButton = new JButton("Transaction History");
        studyButton = new JButton("Study");
        returnToLoginButton = new JButton("Return");

        userNameLabel = new JLabel("Username: " + username);
        userTypeLabel = new JLabel("User Type: " + userType);
        p1.add(userNameLabel);
        p1.add(userTypeLabel);
//        p2.add(depositButton);
        p2.add(withdrawButton);
        p2.add(transactionHistoryButton);
        p2.add(studyButton);
        p2.add(returnToLoginButton); // 添加返回登录按钮到panel

        add(p1);
        add(p2);
    }

//    public JButton getDepositButton() {
//        return depositButton;
//    }

    public JButton getWithdrawButton() {
        return withdrawButton;
    }

    public JButton getTransactionHistoryButton() {
        return transactionHistoryButton;
    }

    public JButton getStudyButton() {
        return studyButton;
    }
    public JButton getReturnToLoginButton() {
        return returnToLoginButton;
    }

}
