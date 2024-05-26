package View;

import Control.TaskController;
import Model.TaskItem;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class TaskView {
    private TaskController controller;
    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JLabel periodTimeLabel;
    private JLabel currentTimeLabel;
    private JLabel lastUpdateTimeLabel;

    public TaskView() {
        taskListModel = new DefaultListModel<>();
        createComponents();
    }

    public void setController(TaskController controller) {
        this.controller = controller;
    }

    private void createComponents() {
        frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create UI components
        JPanel panel = new JPanel(new BorderLayout());

        // Task list
        JList<String> taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Labels for displaying information
        JPanel infoPanel = new JPanel(new GridLayout(3, 2));
        periodTimeLabel = new JLabel("Period Time: - milliseconds");
        currentTimeLabel = new JLabel("Current Time: " + new Date().toString());
        lastUpdateTimeLabel = new JLabel("Last Update Time: -");
        infoPanel.add(periodTimeLabel);
        infoPanel.add(currentTimeLabel);
        infoPanel.add(lastUpdateTimeLabel);
        panel.add(infoPanel, BorderLayout.NORTH);

        // Buttons for task operations
        JButton publishButton = new JButton("Publish Task");
        JButton completeButton = new JButton("Mark as Complete");
        JButton interestRateButton = new JButton("Set Interest Rate");
        JButton periodTimeButton = new JButton("Set Period Time");
        JButton clearCompletedButton = new JButton("Clear Completed Tasks");

        publishButton.addActionListener(e -> {
            String content = JOptionPane.showInputDialog(frame, "Enter task content:");
            if (content != null && !content.isEmpty()) {
                controller.publishTask(frame, content);
            }
        });

        completeButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                controller.completeTask(frame, selectedIndex);
            }
        });

        interestRateButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String rateStr = JOptionPane.showInputDialog(frame, "Enter interest rate:");
                try {
                    double rate = Double.parseDouble(rateStr);
                    controller.setInterestRate(frame, selectedIndex, rate);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid interest rate!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        periodTimeButton.addActionListener(e -> {
            String periodStr = JOptionPane.showInputDialog(frame, "Enter period time (in milliseconds):");
            try {
                int period = Integer.parseInt(periodStr);
                controller.setPeriodTime(frame, period);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid period time!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearCompletedButton.addActionListener(e -> controller.clearCompletedTasks(frame));

        // Add buttons to a toolbar
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(publishButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(interestRateButton);
        buttonPanel.add(periodTimeButton);
        buttonPanel.add(clearCompletedButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);

        // Start a timer to update current time label every second
        Timer timer = new Timer(1000, e -> currentTimeLabel.setText("Current Time: " + new Date().toString()));
        timer.start();
    }

    public void createAndShowGUI() {
        frame.setVisible(true);
    }

    public void updateTaskList(List<TaskItem> tasks) {
        taskListModel.clear();
        for (TaskItem task : tasks) {
            String status = task.isCompleted() ? "[Completed]" : "[Pending]";
            String interestRateInfo = task.getInterestRate() > 0 ? "Interest Rate: " + task.getInterestRate() + "%" : "";
            String lastUpdateTime = task.getLastUpdateTime() != null ? "Last Update: " + task.getLastUpdateTime().toString() : "";
            taskListModel.addElement(status + " " + task.getContent() + " " + interestRateInfo + " " + lastUpdateTime);
        }
    }

    public void updatePeriodTimeLabel(int period) {
        periodTimeLabel.setText("Period Time: " + period + " milliseconds");
    }
}
