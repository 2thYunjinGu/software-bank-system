
import javax.swing.*;

public class monitor {
    private JFrame frame; // JFrame对象，用于创建窗口
    private JButton setDepositLimitButton; // 设置存款限制按钮
    private JButton setWithdrawLimitButton; // 设置取款限制按钮
    private JButton reviewTransactionsButton; // 审查交易记录按钮
    private JButton lockAccountButton; // 锁定账户按钮

    public monitor() {
        frame = new JFrame("Parental Control");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 设置窗口关闭行为为关闭窗口并释放资源

        setDepositLimitButton = new JButton("Set Deposit Limit");
        setWithdrawLimitButton = new JButton("Set Withdraw Limit");
        reviewTransactionsButton = new JButton("Review Transactions");
        lockAccountButton = new JButton("Lock Account");

        JPanel panel = new JPanel();
        panel.add(setDepositLimitButton); // 将设置存款限制按钮添加到面板中
        panel.add(setWithdrawLimitButton); // 将设置取款限制按钮添加到面板中
        panel.add(reviewTransactionsButton); // 将审查交易记录按钮添加到面板中
        panel.add(lockAccountButton); // 将锁定账户按钮添加到面板中

        frame.add(panel); // 将面板添加到窗口中
    }

    public void show() {
        frame.setVisible(true); // 显示窗口
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                monitor monitorInterface = new monitor(); // 创建监管界面对象
                monitorInterface.show(); // 显示监管界面
            }
        });
    }
}