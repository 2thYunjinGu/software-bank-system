package Control;

import java.io.IOException;

import javax.swing.JOptionPane;

import Model.CourseManager;
import Model.Shop;
import Model.TaskManager;
import Model.Transaction;
import Model.User;
import View.*;

public class ChildMainController {
    private ChildMainView childMainView;
    private User user;

    public ChildMainController(ChildMainView childMainView, User user) {
        this.childMainView = childMainView;
        this.user = user;
        initView();
    }

    private void initView() {
        // 绑定事件处理器
//        childMainView.getDepositButton().addActionListener(e -> handleDeposit());
        childMainView.getWithdrawButton().addActionListener(e -> handleWithdraw());
        childMainView.getTransactionHistoryButton().addActionListener(e -> displayTransactionHistory());
        childMainView.getStudyButton().addActionListener(e -> openStudyPage());
        childMainView.getReturnToLoginButton().addActionListener(e -> returnToLogin());
        childMainView.setVisible(true); // 显示主页面
    }

    private void handleDeposit() {
        String amountString = JOptionPane.showInputDialog(childMainView, "Please enter the amount of deposit");
        if (amountString == null || amountString.isEmpty()) {
            JOptionPane.showMessageDialog(childMainView, "Cancelled deposit or invalid input!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] options = {"current deposit", "time deposit"};
        int depositType = JOptionPane.showOptionDialog(childMainView, "Please choose the type of deposit: ", "Type of deposit",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String duration = "";
        if (depositType == 1) {
            duration = JOptionPane.showInputDialog(childMainView, "Please enter the duration of time period (1second == 1day)");
            if (duration == null || duration.isEmpty()) {
                JOptionPane.showMessageDialog(childMainView, "You don't enter the duration. The deposit is cancelled!", "ERROR", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(childMainView, "Deposit successfully");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(childMainView, "Please enter a valid amount!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleWithdraw() {
        String amountString = JOptionPane.showInputDialog(childMainView, "Please enter the withdrawal amount:");
        if (amountString == null || amountString.isEmpty()) {
            JOptionPane.showMessageDialog(childMainView, "Withdrawal cancelled or invalid input!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] options = {"current deposit", "time deposit"};
        int typeChoice = JOptionPane.showOptionDialog(childMainView, "Please choose the type of withdrawal:", "Type of withdrawal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (typeChoice == -1) {
            JOptionPane.showMessageDialog(childMainView, "Withdrawal cancelled due to no type selected.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String type = options[typeChoice];

        try {
            double amount = Double.parseDouble(amountString);
            user.withdraw(amount, type);  // 尝试取款，根据存款类型
            JOptionPane.showMessageDialog(childMainView, "Withdrawal successful");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(childMainView, "Please enter a valid amount!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // 异常处理显示具体错误信息，包括未到期定期存款的错误
            JOptionPane.showMessageDialog(childMainView, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
        JOptionPane.showMessageDialog(childMainView, history.toString(), "Transaction history", JOptionPane.INFORMATION_MESSAGE);
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
    private void returnToLogin() {
        childMainView.dispose(); // 关闭当前界面
        LoginView loginView = new LoginView(); // 创建登录视图的新实例
        LoginController loginController = new LoginController(loginView); // 创建登录控制器的实例并传入视图
        loginController.initView(); // 确保界面已正确初始化和监听器已设置
    }
}
