package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private List<CourseItem> courses;

    public CourseManager() {
        this.courses = new ArrayList<>();
    }

    public void loadCourses(String directoryPath) {
        try {
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        String name = path.getFileName().toString().replace(".txt", "");
                        try {
                            String content = new String(Files.readAllBytes(path));
                            courses.add(new CourseItem(name, content));
                        } catch (IOException e) {
                            System.err.println("Error reading file: " + path);
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error walking through directory: " + directoryPath);
        }
    }

    public List<CourseItem> getCourses() {
        return courses;
    }

    public String getCourseContent(String courseName) {
        for (CourseItem course : courses) {
            if (course.getName().equals(courseName)) {
                return course.getContent();
            }
        }
        return null; // Return null if no course matches the name
    }
}
