package Model;

public class CourseItem {
    private String name;
    private String content;

    public CourseItem(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
