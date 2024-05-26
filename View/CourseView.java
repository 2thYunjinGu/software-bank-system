package View;

import Control.CourseController;

import javax.swing.*;
import java.awt.*;

public class CourseView {
    private CourseController controller;
    private JFrame frame;
    private JList<String> courseList;
    private JTextArea courseContent;

    public CourseView() {
        createComponents();
    }

    public void setController(CourseController controller) {
        this.controller = controller;
    }

    private void createComponents() {
        frame = new JFrame("Learning Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 创建课程列表
        courseList = new JList<>();
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedCourse = courseList.getSelectedValue();
                if (selectedCourse != null) {
                    controller.loadCourseContent(selectedCourse);
                }
            }
        });
        JScrollPane listScrollPane = new JScrollPane(courseList);
        listScrollPane.setPreferredSize(new Dimension(200, 0));

        // 创建显示课程内容的文本区域
        courseContent = new JTextArea();
        courseContent.setEditable(false);
        JScrollPane contentScrollPane = new JScrollPane(courseContent);

        frame.add(listScrollPane, BorderLayout.WEST);
        frame.add(contentScrollPane, BorderLayout.CENTER);

        // 设置窗口的初始大小
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);  // Center on screen
    }

    public void createAndShowGUI() {
        frame.setVisible(true);
    }

    public void updateCourseList(String[] courses) {
        courseList.setListData(courses);
    }

    public void displayCourseContent(String content) {
        courseContent.setText(content);
    }
}
