package View;
import javax.swing.*;

public class MonitorView {
    private JFrame frame; // JFrame对象，用于创建窗口
    private JButton setDepositLimitButton; // 设置存款限制按钮
    private JButton setWithdrawLimitButton; // 设置取款限制按钮
    private JButton reviewTransactionsButton; // 审查交易记录按钮
    private JButton lockAccountButton; // 锁定账户按钮
    private  JButton unlockAccountButton; // 解锁账户按钮

    public MonitorView() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Parental Control");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setDepositLimitButton = new JButton("Set Deposit Limit");
        setWithdrawLimitButton = new JButton("Set Withdraw Limit");
        reviewTransactionsButton = new JButton("Review Transactions");
        lockAccountButton = new JButton("Lock Account");
        unlockAccountButton = new JButton("Unlock Account");

        JPanel panel = new JPanel();
        panel.add(setDepositLimitButton);
        panel.add(setWithdrawLimitButton);
        panel.add(reviewTransactionsButton);
        panel.add(lockAccountButton);
        panel.add(unlockAccountButton);

        frame.add(panel); // 将面板添加到窗口中
    }
    public JButton getDepositLimitButton() {
        return setDepositLimitButton;
    }

    public JButton getWithdrawLimitButton() {
        return setWithdrawLimitButton;
    }

    public JButton getReviewTransactionsButton() {
        return reviewTransactionsButton;
    }

    public JButton getLockAccountButton() { return lockAccountButton; }

    public JButton getUnlockAccountButton() { return unlockAccountButton; }

    public JFrame getFrame() { return frame; }
}