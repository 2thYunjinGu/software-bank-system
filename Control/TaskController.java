package Control;

import Model.TaskManager;
import Model.TaskItem;
import View.TaskView;
import javax.swing.*;
import java.util.Date;

public class TaskController {
    private TaskManager taskManager;
    private TaskView taskView;
    private String pin = null;

    public TaskController(TaskManager taskManager, TaskView taskView) {
        this.taskManager = taskManager;
        this.taskView = taskView;
    }

    public void initialize() {
        taskView.setController(this);
        taskView.createAndShowGUI();
    }

    public boolean checkPIN(JFrame frame) {
        if (pin == null) {
            return true; // No PIN set, allow operation
        } else {
            String inputPIN = JOptionPane.showInputDialog(frame, "Enter PIN:");
            if (inputPIN != null && inputPIN.equals(pin)) {
                return true; // Correct PIN entered, allow operation
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect PIN!", "Error", JOptionPane.ERROR_MESSAGE);
                return false; // Incorrect PIN entered, deny operation
            }
        }
    }

    public void setPIN(JFrame frame) {
        if (pin == null || !pinSet(frame)) { // If PIN is not set or PIN setting fails
            String newPIN = JOptionPane.showInputDialog(frame, "Set PIN:");
            if (newPIN != null && !newPIN.isEmpty()) {
                pin = newPIN;
                JOptionPane.showMessageDialog(frame, "PIN set successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid PIN!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean pinSet(JFrame frame) {
        if (pin != null) {
            String inputPIN = JOptionPane.showInputDialog(frame, "Enter current PIN to confirm:");
            if (inputPIN != null && inputPIN.equals(pin)) {
                return false; // PIN already set and confirmed, do not set again
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect PIN!", "Error", JOptionPane.ERROR_MESSAGE);
                return true; // PIN set but not confirmed, prompt for new PIN
            }
        }
        return false; // PIN not set, proceed with setting new PIN
    }

    public void publishTask(JFrame frame, String content) {
        if (checkPIN(frame)) {
            taskManager.publishTask(content);
            taskView.updateTaskList(taskManager.getTasks());
        }
    }

    public void completeTask(JFrame frame, int taskIndex) {
        if (checkPIN(frame)) {
            TaskItem task = taskManager.getTasks().get(taskIndex);
            taskManager.completeTask(task);
            taskView.updateTaskList(taskManager.getTasks());
        }
    }

    public void setInterestRate(JFrame frame, int taskIndex, double rate) {
        if (checkPIN(frame)) {
            TaskItem task = taskManager.getTasks().get(taskIndex);
            if (taskManager.checkInterestRateSum(task, rate)) {
                taskManager.setInterestRate(task, rate);
                task.setLastUpdateTime(new Date());
                taskView.updateTaskList(taskManager.getTasks());
            } else {
                JOptionPane.showMessageDialog(frame, "Interest rate sum exceeds 100%! Setting interest rate to 0%.", "Error", JOptionPane.ERROR_MESSAGE);
                taskManager.setInterestRate(task, 0); // Set interest rate to 0% if sum exceeds 100%
            }
        }
    }

    public void setPeriodTime(JFrame frame, int period) {
        if (checkPIN(frame)) {
            taskManager.setPeriodTime(period);
            taskView.updatePeriodTimeLabel(period);
        }
    }

    public void clearCompletedTasks(JFrame frame) {
        if (checkPIN(frame)) {
            taskManager.clearCompletedTasks();
            taskView.updateTaskList(taskManager.getTasks());
        }
    }
}
