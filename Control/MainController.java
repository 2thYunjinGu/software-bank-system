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
        mainView.getDepositButton().addActionListener(e -> handleDeposit());
        mainView.getWithdrawButton().addActionListener(e -> handleWithdraw());
        mainView.getTransactionHistoryButton().addActionListener(e -> displayTransactionHistory());
        mainView.getTaskButton().addActionListener(e -> openTaskPage());
        mainView.getStudyButton().addActionListener(e -> openStudyPage());
        mainView.getShopButton().addActionListener(e -> openShopPage());
        mainView.getRegulationButton().addActionListener(e -> openRegulationPage());
        mainView.setVisible(true); 

    private void handleDeposit() {
        String amountString = JOptionPane.showInputDialog(mainView, "Please enter the amount of deposit");
        if (amountString == null || amountString.isEmpty()) {
            JOptionPane.showMessageDialog(mainView, "Cancelled deposit or invalid input!", "ERROR", JOptionPane.ERROR_MESSAGE); 
            return;
        }

        String[] options = {"current deposit", "time deposit"};
        int depositType = JOptionPane.showOptionDialog(mainView, "Please choose the type of deposit: ", "Type of deposit",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String duration = "";
        if (depositType == 1) { 
            duration = JOptionPane.showInputDialog(mainView, "Please enter the duration of time period (1second == 1day)"); 
            if (duration == null || duration.isEmpty()) {
                JOptionPane.showMessageDialog(mainView, "You don't enter the duration. The deposit is cancelled!", "ERROR", JOptionPane.ERROR_MESSAGE); 
                return;
            }
        }

        try {
            double amount = Double.parseDouble(amountString);
            if (depositType == 0) {
                user.deposit(amount, "current deposit", "");
            } else {
                user.deposit(amount, "time deposit", duration);
            }
            JOptionPane.showMessageDialog(mainView, "Deposit successfully"); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainView, "Please enter a valid amount!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void handleWithdraw() {
        String amountString = JOptionPane.showInputDialog(mainView, "Please enter the withdraw amount: "); 
        if (amountString == null || amountString.isEmpty()) {
            JOptionPane.showMessageDialog(mainView, "Cancelled withdrawaal or invalid input!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] options = {"current deposit", "time deposit"};
        int typeChoice = JOptionPane.showOptionDialog(mainView, "Please choose the type of withdrawal: ", "Type of withdrawal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (typeChoice == -1) {  
            JOptionPane.showMessageDialog(mainView, "You don't enter the withdrawal tyoe, the withdrawal is cancelled!", "ERROR", JOptionPane.ERROR_MESSAGE);//未选择取款类型，取款取消！
            return;
        }

        String type = options[typeChoice]; 

        try {
            double amount = Double.parseDouble(amountString);
            user.withdraw(amount, type);  
            JOptionPane.showMessageDialog(mainView, "Withdraw successfully");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainView, "Please enter a valid amount!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {  
            JOptionPane.showMessageDialog(mainView, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }


private void displayTransactionHistory() {
    StringBuilder history = new StringBuilder("<html>Trandaction history: <br>");
    for (Transaction transaction : user.getTransactions()) {
        history.append(transaction.getType())
                .append(" - ")
                .append(String.format("%.2f", transaction.getAmount()))
                .append(" 元 (")
                .append(transaction.getTimestamp())
                .append(") - 余额: ")
                .append( user.getBalance())
                .append(" 元<br>");  
    }
    history.append("</html>");
    JOptionPane.showMessageDialog(mainView, history.toString(), "Trandaction history: ", JOptionPane.INFORMATION_MESSAGE);
}



    private void openTaskPage() {
        
    }

    private void openStudyPage() {
    
    }

    private void openShopPage() {
        // 打开商店页面逻辑
    }
import javax.swing.*;

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
    }
}
    private void openRegulationPage() { MonitorView MonitorViewInterface = new MonitorView();
    
    // 显示监控界面
    MonitorViewInterface.show();
        // 打开监管页面逻辑
    }
}
