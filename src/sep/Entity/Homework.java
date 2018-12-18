package sep.Entity;

import java.util.Date;

public class Homework {
    private String name;
    private Integer courseId;
    private String content;
    private Date assigntime;
    private Date deadline;
    private double percentage;
    private int id;

    public Homework(){}
    public Homework(String name, Integer courseId, String content, Date deadline, double percentage){
        this.name=name;
        this.courseId=courseId;
        this.content=content;
        this.assigntime=new Date();
        this.deadline=deadline;
        this.percentage=percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAssigntime() {
        return assigntime;
    }

    public void setAssigntime(Date assigntime) {
        this.assigntime = assigntime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Integer getId(){return id;}

    public void setId(Integer id) { this.id = id;}
}
