package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<TaskItem> tasks;
    private int periodTime;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.periodTime = 0;
    }

    public void publishTask(String content) {
        TaskItem newTask = new TaskItem(content);
        tasks.add(newTask);
    }

    public void completeTask(TaskItem task) {
        task.setCompleted(true);
    }

    public void setInterestRate(TaskItem task, double rate) {
        task.setInterestRate(rate);
    }

    public void setPeriodTime(int period) {
        this.periodTime = period;
    }

    public List<TaskItem> getTasks() {
        return new ArrayList<>(tasks);  // Return a copy of tasks to prevent external modification
    }

    public int getPeriodTime() {
        return periodTime;
    }

    public boolean checkInterestRateSum(TaskItem newTask, double newRate) {
        double sum = newRate;
        for (TaskItem task : tasks) {
            if (!task.equals(newTask) && !task.isCompleted()) {
                sum += task.getInterestRate();
            }
        }
        return sum <= 100;
    }

    public void clearCompletedTasks() {
        tasks.removeIf(TaskItem::isCompleted);
    }
}
