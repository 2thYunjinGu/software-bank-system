package Control;

import Model.*;
import View.*;
import javax.swing.*;
import java.io.IOException;

public class MainController {
    private MainView parentMainView;
    private User user;

    public MainController(MainView parentMainView, User user) {
        this.parentMainView = parentMainView;
        this.user = user;
        initView();
    }

    private void initView() {
        // 绑定事件处理器
        parentMainView.getDepositButton().addActionListener(e -> handleDeposit());
        parentMainView.getWithdrawButton().addActionListener(e -> handleWithdraw());
        parentMainView.getTransactionHistoryButton().addActionListener(e -> displayTransactionHistory());
        parentMainView.getTaskButton().addActionListener(e -> openTaskPage());
        parentMainView.getStudyButton().addActionListener(e -> openStudyPage());
        parentMainView.getShopButton().addActionListener(e -> openShopPage());
        parentMainView.getRegulationButton().addActionListener(e -> openRegulationPage());
        parentMainView.setVisible(true); // 显示主页面
    }

    private void handleDeposit() {
        String amountString = JOptionPane.showInputDialog(parentMainView, "Please enter the amount of deposit");
        if (amountString == null || amountString.isEmpty()) {
            JOptionPane.showMessageDialog(parentMainView, "Cancelled deposit or invalid input!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] options = {"current deposit", "time deposit"};
        int depositType = JOptionPane.showOptionDialog(parentMainView, "Please choose the type of deposit: ", "Type of deposit",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String duration = "";
        if (depositType == 1) {
            duration = JOptionPane.showInputDialog(parentMainView, "Please enter the duration of time period (1second == 1day)");
            if (duration == null || duration.isEmpty()) {
                JOptionPane.showMessageDialog(parentMainView, "You don't enter the duration. The deposit is cancelled!", "ERROR", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(parentMainView, "Deposit successfully");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentMainView, "Please enter a valid amount!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleWithdraw() {
        String amountString = JOptionPane.showInputDialog(parentMainView, "Please enter the withdrawal amount:");
        if (amountString == null || amountString.isEmpty()) {
            JOptionPane.showMessageDialog(parentMainView, "Withdrawal cancelled or invalid input!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] options = {"current deposit", "time deposit"};
        int typeChoice = JOptionPane.showOptionDialog(parentMainView, "Please choose the type of withdrawal:", "Type of withdrawal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (typeChoice == -1) {
            JOptionPane.showMessageDialog(parentMainView, "Withdrawal cancelled due to no type selected.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String type = options[typeChoice];

        try {
            double amount = Double.parseDouble(amountString);
            user.withdraw(amount, type);  // 尝试取款，根据存款类型
            JOptionPane.showMessageDialog(parentMainView, "Withdrawal successful");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentMainView, "Please enter a valid amount!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // 异常处理显示具体错误信息，包括未到期定期存款的错误
            JOptionPane.showMessageDialog(parentMainView, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayTransactionHistory() {
        StringBuilder history = new StringBuilder("<html>Transaction history: <br>");
        for (Transaction transaction : user.getTransactions()) {
            history.append(transaction.getType())
                    .append(" - ")
                    .append(String.format("%.2f", transaction.getAmount()))
                    .append(" 元 (")
                    .append(transaction.getTimestamp())
                    .append(") - 余额: ")
                    .append(user.getBalance())
                    .append(" 元<br>");
        }
        history.append("</html>");
        JOptionPane.showMessageDialog(parentMainView, history.toString(), "Transaction history", JOptionPane.INFORMATION_MESSAGE);
    }

    private void openTaskPage() {
        // 创建模型
        TaskManager taskManager = new TaskManager();

        // 创建视图
        TaskView taskView = new TaskView();

        // 创建控制器，并注入模型和视图
        TaskController taskController = new TaskController(taskManager, taskView);

        // 将控制器设置给视图，这样视图就可以调用控制器的方法
        taskView.setController(taskController);

        // 初始化视图的GUI，这通常会使窗口变为可见，并开始监听用户事件
        taskController.initialize();
    }

    private void openStudyPage() {
        // 创建模型
        CourseManager courseManager = new CourseManager();

        // 创建视图
        CourseView courseView = new CourseView();

        // 创建控制器，并注入模型和视图
        CourseController courseController = new CourseController(courseManager, courseView);

        // 初始化控制器，这通常包括加载数据和显示视图
        courseController.initialize();
    }

    private void openShopPage() {
        Shop shop = new Shop();
        ShopView shopView = new ShopView();
        ShopController shopController = new ShopController(shop, shopView);
    }

    private void openRegulationPage() {
        MonitorView view = new MonitorView();
        MonitorController monitorController = new MonitorController(view, user);
    }
}
