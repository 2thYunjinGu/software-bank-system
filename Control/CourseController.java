package Control;

import Model.CourseManager;
import View.CourseView;

public class CourseController {
    private CourseManager courseManager;
    private CourseView courseView;

    public CourseController(CourseManager courseManager, CourseView courseView) {
        this.courseManager = courseManager;
        this.courseView = courseView;
        this.courseView.setController(this);
    }


    public void initialize() {
        // 加载课程数据，这里假设课程文件存放在某个目录
        courseManager.loadCourses("D:\\学习材料\\大三下\\软件工程\\大作业\\0525最新\\software engineering (2)(2)\\software engineering\\src\\CourseData");
        // 更新视图的课程列表
        updateCourseListView();
        // 显示GUI
        courseView.createAndShowGUI();
    }

    private void updateCourseListView() {
        // 获取所有课程的名称
        String[] courseNames = courseManager.getCourses().stream()
                .map(course -> course.getName())
                .toArray(String[]::new);
        courseView.updateCourseList(courseNames);
    }

    public void loadCourseContent(String courseName) {
        // 根据课程名称获取课程内容
        String content = courseManager.getCourseContent(courseName);
        // 更新视图显示课程内容
        courseView.displayCourseContent(content);
    }
}
