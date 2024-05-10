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
        setDepositLimitButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // 弹出输入框，让用户输入新的存款限制额度
        String depositLimitString = JOptionPane.showInputDialog(frame, "输入新的存款限额:");
        if (depositLimitString != null && !depositLimitString.isEmpty()) {
            try {
                double newDepositLimit = Double.parseDouble(depositLimitString);
                // 设置新的存款限制额度到 User 类中
                user.setDepositLimit(newDepositLimit);
                JOptionPane.showMessageDialog(frame, "存款限额设置成功!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "无效输入! 请输入一个有效的数字。", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});

setWithdrawLimitButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // 弹出输入框，让用户输入新的取款限制额度
        String withdrawLimitString = JOptionPane.showInputDialog(frame, "输入新的取款限额:");
        if (withdrawLimitString != null && !withdrawLimitString.isEmpty()) {
            try {
                double newWithdrawLimit = Double.parseDouble(withdrawLimitString);
                // 设置新的取款限制额度到 User 类中
                user.setWithdrawLimit(newWithdrawLimit);
                JOptionPane.showMessageDialog(frame, "取款限额设置成功!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "无效输入! 请输入一个有效的数字。", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});


    
     
        reviewTransactionsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               
                JOptionPane.showMessageDialog(frame, "Review Transactions button clicked!");
            }
        });

        lockAccountButton.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
              
                JOptionPane.showMessageDialog(frame, "Lock Account button clicked!");
            }
        });
    }
}
