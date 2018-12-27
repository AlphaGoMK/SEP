package sep.Entity;

public class CourseInfo {
    private Integer courseId;
    private String name;
    private String date;
    private String description;
    private String teacherName;

    public CourseInfo(Course c) {
        courseId = c.getCourseId();
        name = c.getName();
        date = c.getDate().toString();
        description = c.getDescription();
        teacherName = TeacherDAO.getTeacherbyId(c.getTeacherid()).getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
