package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShowCourseAction extends ActionSupport {

    public List<CourseInfo> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseInfo> courses) {
        this.courses = courses;
    }

    private List<CourseInfo> courses;

    public String execute() {
        System.out.println("Showcourse Action called");
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        StudentDAO sdao = new StudentDAO();
        Student s = sdao.getStudentbyId((Integer)session.get("USER_ID"));
        Set<Integer> courseset = s.getCourseset();
        List<CourseInfo> clist = new ArrayList<CourseInfo>();
        System.out.println(courseset);
        for (Integer i: courseset) {
            Course c = CourseDAO.getCoursebyId(i);
            System.out.println(c.getName());
            if (c != null) {
                clist.add(new CourseInfo(c));
            }
        }
        setCourses(clist);
        System.out.println("finished");
        return "success";
    }

}