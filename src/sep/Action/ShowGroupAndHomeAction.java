package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.*;

import java.util.*;

public class ShowGroupAndHomeAction extends ActionSupport {

    private int courseId;
    private CourseInfo courseInfo;
    private Course c;
    private Student s;

    private String has_group;
    private Group g;
    private List<String> hwState;
    private List<Double> hwScore;

    public Group getG() {
        return g;
    }
    public void setG(Group g) {
        this.g = g;
    }

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

    public String getHas_group() {
        return has_group;
    }
    public void setHas_group(String has_group) {
        this.has_group = has_group;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }
    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<String> getHwState() {
        return hwState;
    }

    public void setHwState(List<String> hwState) {
        this.hwState = hwState;
    }

    public List<Double> getHwScore() {
        return hwScore;
    }

    public void setHwScore(List<Double> hwScore) {
        this.hwScore = hwScore;
    }

    public String execute() {
        System.out.println("ShowGroupAndHomeAction called.");
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        s = StudentDAO.getStudentbyId((Integer)session.get("USER_ID"));
        if(session.containsKey("COURSE_ID")){
            this.courseId = (Integer)session.get("COURSE_ID");
        }
        c = CourseDAO.getCoursebyId(courseId);
        session.put("COURSE_ID", this.courseId);
        Integer grpId = s.getGroupId(this.courseId);
        if(grpId != null){
            has_group = "true";
            g = GroupDAO.getGroupById(grpId);
            hwState = new ArrayList<String>();
            hwScore = new ArrayList<Double>();

            Map<String, MySubmit> submitted = g.getSubmit();
            Map<String, Double> submitScore = g.getScore();
            for (Iterator<Homework> it = c.getHomework().iterator(); it.hasNext();) {
                Homework h = (Homework)it.next();
                if (submitted.containsKey(h.getName())) {
                    hwState.add("true");
                    if(submitScore.containsKey(h.getName())) {
                        hwScore.add(submitScore.get(h.getName()));
                    } else {
                        hwScore.add(-1.0);
                    }

                } else {
                    hwState.add("false");
                    hwScore.add(-1.0);
                }
            }
            session.put("GROUP_ID", this.g.getId());
        } else {
            has_group = "false";
            g = null;
        }

        this.courseInfo = new CourseInfo(CourseDAO.getCoursebyId(courseId));
        return "success";
    }

}