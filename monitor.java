import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitorView {
    private JFrame frame; // JFrame对象，用于创建窗口
    private JButton setDepositLimitButton; // 设置存款限制按钮
    private JButton setWithdrawLimitButton; // 设置取款限制按钮
    private JButton reviewTransactionsButton; // 审查交易记录按钮
    private JButton lockAccountButton; // 锁定账户按钮

    public MonitorView() {
        frame = new JFrame("Parental Control");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setDepositLimitButton = new JButton("Set Deposit Limit");
        setWithdrawLimitButton = new JButton("Set Withdraw Limit");
        reviewTransactionsButton = new JButton("Review Transactions");
        lockAccountButton = new JButton("Lock Account");

        JPanel panel = new JPanel();
        panel.add(setDepositLimitButton);
        panel.add(setWithdrawLimitButton);
        panel.add(reviewTransactionsButton);
        panel.add(lockAccountButton);

        frame.add(panel); // 将面板添加到窗口中

        // 为按钮添加监听器，定义按钮的具体功能
        setDepositLimitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // 添加设置存款限制按钮的具体功能
                JOptionPane.showMessageDialog(frame, "Set Deposit Limit button clicked!");
            }
        });

        setWithdrawLimitButton.addActionListener(new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                // 添加设置取款限制按钮的具体功能
                JOptionPane.showMessageDialog(frame, "Set Withdraw Limit button clicked!");
            }
        });

        reviewTransactionsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // 添加审查交易记录按钮的具体功能
                JOptionPane.showMessageDialog(frame, "Review Transactions button clicked!");
            }
        });

        lockAccountButton.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                // 添加锁定账户按钮的具体功能
                JOptionPane.showMessageDialog(frame, "Lock Account button clicked!");
            }
        });
    }
}
