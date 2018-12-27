package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.*;

import java.util.*;

public class CreateGroupAction extends ActionSupport {

    private int courseId;
    private CourseInfo courseInfo;

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }
    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    private Course c;
    private Student s;

    public Course getC() {
        return c;
    }
    public void setC(Course c) {
        this.c = c;
    }

    public Student getS() {
        return s;
    }
    public void setS(Student s) {
        this.s = s;
    }

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String execute() {
        System.out.println("CreateGroupAction called.");
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        s = StudentDAO.getStudentbyId((Integer)session.get("USER_ID"));
        if(session.containsKey("COURSE_ID")){
            this.courseId = (Integer)session.get("COURSE_ID");
        }
        c = CourseDAO.getCoursebyId(courseId);

        this.courseInfo = new CourseInfo(CourseDAO.getCoursebyId(courseId));
        return "success";
    }

}