package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.Homework;
import sep.Model.studentAction;

import java.util.Date;

public class StuLookHomeAction extends ActionSupport {
    // input
    private int courseId;
    private int stuId;
    // set
    private String grpId;
    private Homework homework;
    private String name;
    private String content;
    private Date assigntime;
    private Date deadline;
    private double percentage;

    private studentAction studentaction = new studentAction();

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getGrpId() {
        return grpId;
    }

    public void setGrpId(String grpId) {
        this.grpId = grpId;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    // input stuId and courseId
    public String execute() throws Exception{

        grpId=studentaction.getStuGrpId(stuId, courseId);
        homework=studentaction.getRecentHome(courseId, grpId);

        name=homework.getName();
        content=homework.getContent();
        assigntime=homework.getAssigntime();
        deadline=homework.getDeadline();
        percentage=homework.getPercentage();

        return SUCCESS;
    }
}
