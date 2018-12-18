package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.Homework;
import sep.Model.courseAction;

import java.util.Date;

public class BuzhiHomeAction extends ActionSupport {

    private Homework homework;

    private String name;
    private int courseId;
    private String content;
    private Date deadline;
    private double percentage;
    private courseAction courseaction=new courseAction();

    public String execute() throws Exception{


        //Homework h=new Homework(name, courseId, content, deadline, percentage);
        // TODO: DAO
        courseaction.getCourseById(courseId).addHome(name, content, deadline, percentage);
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
