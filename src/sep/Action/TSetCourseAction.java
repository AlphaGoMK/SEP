package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import sep.Entity.Course;
import sep.Model.adminAction;
import sep.Model.courseAction;
import com.opensymphony.xwork2.ActionSupport;
import sep.Model.teacherAction;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

public class TSetCourseAction extends ActionSupport {
    private int courseId;
    private String courseName;
    private int teacherId;
    private String description;
    private int maxcrew;
    private int mincrew;
    private String maxcrewtxt;
    private String mincrewtxt;
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


    public String flushCourse() throws Exception{
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
            System.out.println("course");
            System.out.println(courseList.size());
            Iterator<Course> itt=courseList.iterator();
            while(itt.hasNext()){
                Course tmp=itt.next();
                System.out.println(tmp.getName());
                System.out.println(tmp.getCourseId());
            }

        }catch(Exception e){
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
            PrintWriter out = null;
            out = response.getWriter();
            out.print("<script>alert('No course teached')</script>");
            out.print("<script>window.location.href='/TeacherMain.jsp'</script>");
            out.flush();
            out.close();
        }
        return "success";
    }



    public String createCourse() throws Exception{
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        teacherId=(int)session.get("USER_ID");
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
        PrintWriter out = null;

        if(mincrew<=0){

            out = response.getWriter();
            out.print("<script>alert('最小组员数不能小于1')</script>");
            out.print("<script>window.location.href='/TeacherMain.jsp'</script>");
            out.flush();
            out.close();
            return "error";
        }
        else if(mincrew>maxcrew){

            out = response.getWriter();
            out.print("<script>alert('最小组员数不能大于最大组员数')</script>");
            out.print("<script>window.location.href='/TeacherMain.jsp'</script>");
            out.flush();
            out.close();
            return "error";
        }
        else{
//            try{
//                ca.createCourse(courseName, description, teacherId, maxcrew, mincrew);
//                return "success";
//            }catch(Exception e){
//                return "error";
//            }
            ca.createCourse(courseName, description, teacherId, maxcrew, mincrew);
            return "success";
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

    public int getMaxcrew() {
        return maxcrew;
    }

    public void setMaxcrew(int maxcrew) {
        this.maxcrew = maxcrew;
    }

    public int getMincrew() {
        return mincrew;
    }

    public void setMincrew(int mincrew) {
        this.mincrew = mincrew;
    }

    public String getMaxcrewtxt() {
        return maxcrewtxt;
    }

    public void setMaxcrewtxt(String maxcrewtxt) {
        this.maxcrewtxt = maxcrewtxt;
    }

    public String getMincrewtxt() {
        return mincrewtxt;
    }

    public void setMincrewtxt(String mincrewtxt) {
        this.mincrewtxt = mincrewtxt;
    }
}
