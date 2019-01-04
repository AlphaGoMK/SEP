package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import sep.Entity.Course;
import sep.Model.adminAction;
import sep.Model.courseAction;
import com.opensymphony.xwork2.ActionSupport;
import sep.Model.teacherAction;

import java.util.*;

public class TSetCourseAction extends ActionSupport {
    private int courseId;
    private String courseName;
    private int teacherId;
    private String description;
    private courseAction ca=new courseAction();
    private teacherAction ta=new teacherAction();
    private adminAction aa=new adminAction();
    private List<Course> courseList;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String execute() throws Exception{
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        teacherId=(int)session.get("USER_ID");
        courseList=new ArrayList<Course>();
        try{
            Set<Integer> courseIdSet=aa.getTeacherById(teacherId).getCourseset();
            Iterator<Integer> it=courseIdSet.iterator();
            while(it.hasNext()){
                int id=it.next();
                courseList.add(ca.getCourseById(id));
            }
            return "success";
        }catch(Exception e){
            return "error";
        }
    }

    public String createCourse() throws Exception{
        try{
            ca.createCourse(courseName,courseId,description,teacherId);
            return "success";
        }catch(Exception e){
            return "error";
        }
    }

    public courseAction getCa() {
        return ca;
    }

    public void setCa(courseAction ca) {
        this.ca = ca;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public teacherAction getTa() {
        return ta;
    }

    public void setTa(teacherAction ta) {
        this.ta = ta;
    }
}
