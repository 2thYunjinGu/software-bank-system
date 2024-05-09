package Control;

import View.MainView;
import Model.User;
import Model.Transaction;
import javax.swing.*;

public class MainController {
    private MainView mainView;
    private User user;

    public MainController(MainView mainView, User user) {
        this.mainView = mainView;
        this.user = user;
        initView();
    }

    private void initView() {
        // 绑定事件处理器
        mainView.getDepositButton().addActionListener(e -> handleDeposit());
        mainView.getWithdrawButton().addActionListener(e -> handleWithdraw());
        mainView.getTransactionHistoryButton().addActionListener(e -> displayTransactionHistory());
        mainView.getTaskButton().addActionListener(e -> openTaskPage());
        mainView.getStudyButton().addActionListener(e -> openStudyPage());
        mainView.getShopButton().addActionListener(e -> openShopPage());
        mainView.getRegulationButton().addActionListener(e -> openRegulationPage());
        mainView.setVisible(true); // 显示主页面
    }

    private void handleDeposit() {
        String amountString = JOptionPane.showInputDialog(mainView, "请输入存款金额:");
        if (amountString == null || amountString.isEmpty()) {
            JOptionPane.showMessageDialog(mainView, "存款取消或输入无效！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] options = {"活期", "死期"};
        int depositType = JOptionPane.showOptionDialog(mainView, "请选择存款类型:", "存款类型",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String duration = "";
        if (depositType == 1) { // 死期存款
            duration = JOptionPane.showInputDialog(mainView, "请输入存款的锁定天数（1秒=1天）:");
            if (duration == null || duration.isEmpty()) {
                JOptionPane.showMessageDialog(mainView, "未输入锁定时间，存款取消！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            double amount = Double.parseDouble(amountString);
            if (depositType == 0) {
                user.deposit(amount, "活期", "");
            } else {
                user.deposit(amount, "死期", duration);
            }
            JOptionPane.showMessageDialog(mainView, "存款成功！");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainView, "请输入有效的金额！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void handleWithdraw() {
        String amountString = JOptionPane.showInputDialog(mainView, "请输入取款金额:");
        if (amountString == null || amountString.isEmpty()) {
            JOptionPane.showMessageDialog(mainView, "取款取消或输入无效！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 提供用户选择存款类型
        String[] options = {"活期", "死期"};
        int typeChoice = JOptionPane.showOptionDialog(mainView, "请选择取款类型:", "取款类型",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (typeChoice == -1) {  // 用户未做选择
            JOptionPane.showMessageDialog(mainView, "未选择取款类型，取款取消！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String type = options[typeChoice];  // 获取选择的存款类型

        try {
            double amount = Double.parseDouble(amountString);
            user.withdraw(amount, type);  // 现在传递了两个参数
            JOptionPane.showMessageDialog(mainView, "取款成功！");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainView, "请输入有效的金额！", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {  // 更广泛的异常捕捉，包括自定义逻辑可能抛出的异常
            JOptionPane.showMessageDialog(mainView, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }


//private void displayTransactionHistory() {
//    StringBuilder history = new StringBuilder("<html>交易记录：<br>");
//    for (Transaction transaction : user.getTransactions()) {
//        history.append(transaction.getType())
//                .append(" - ")
//                .append(transaction.getAmount())
//                .append(" (")
//                .append(transaction.getTimestamp())  // 正确调用 getter 方法
//                .append(")<br>")
//                .append(user.getBalance());
//    }
//    history.append("</html>");
//    JOptionPane.showMessageDialog(mainView, history.toString());
//}
private void displayTransactionHistory() {
    StringBuilder history = new StringBuilder("<html>交易记录：<br>");
    for (Transaction transaction : user.getTransactions()) {
        history.append(transaction.getType())
                .append(" - ")
                .append(String.format("%.2f", transaction.getAmount()))
                .append(" 元 (")
                .append(transaction.getTimestamp())
                .append(") - 余额: ")
                .append( user.getBalance())
                .append(" 元<br>");  // 每条记录后都添加换行
    }
    history.append("</html>");
    JOptionPane.showMessageDialog(mainView, history.toString(), "交易历史", JOptionPane.INFORMATION_MESSAGE);
}



    private void openTaskPage() {
        // 打开任务页面逻辑
    }

    private void openStudyPage() {
        // 打开学习页面逻辑
    }

    private void openShopPage() {
        // 打开商店页面逻辑
    }

    private void openRegulationPage() { // 创建监管页面的逻辑
    // 创建监控界面对象实例
    monitor monitorInterface = new monitor();
    
    // 显示监控界面
    monitorInterface.show();
        // 打开监管页面逻辑
    }
}
