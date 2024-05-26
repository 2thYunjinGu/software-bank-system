package Control;

import org.junit.Before;
import org.junit.Test;
import org.stjs.javascript.annotation.Template;

import Model.CourseItem;
import Model.CourseManager;
import View.CourseView;

public class CourseTest {
    private CourseController courseController;
    private CourseManager courseManager;
    private CourseView courseView;

    @Before
    public void setUp() {
        courseView = new CourseView();
        courseManager = new CourseManager();
        courseController = new CourseController(courseManager, courseView);
    }

    @Test
    public void test_1() {
        courseController.initialize();
    }
}
