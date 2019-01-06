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
    private List<List<String>> hwFileLists;

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

    public List<List<String>> getHwFileLists() {
        return hwFileLists;
    }
    public void setHwFileLists(List<List<String>> hwFileLists) {
        this.hwFileLists = hwFileLists;
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
            hwFileLists = new ArrayList<List<String>>();
            Map<String, MySubmit> submitted = g.getSubmit();
            Map<String, Double> submitScore = g.getScore();
            int i = 0;
            for (Iterator<Homework> it = c.getHomework().iterator(); it.hasNext();) {
                Homework h = (Homework)it.next();
                if (submitted.containsKey(h.getName())) {
                    hwState.add("true");
                    if(submitScore.containsKey(h.getName())) {
                        hwScore.add(submitScore.get(h.getName()));
                    } else {
                        hwScore.add(-1.0);
                    }
                    List<String> rawList = submitted.get(h.getName()).getPathList();
                    if (rawList.size() > 0) {
                        List<String> trimed = new ArrayList<>();
                        for (int j = 0; j < rawList.size(); j++) {
                            String[] splitted = rawList.get(j).split("\\/");
                            trimed.add(splitted[splitted.length - 1]);
                        }
                        hwFileLists.add(trimed);
                    } else {
                        hwFileLists.add(new ArrayList<>());
                    }
                } else {
                    hwState.add("false");
                    hwScore.add(-1.0);
                    hwFileLists.add(new ArrayList<>());
                }
            }
            session.put("GROUP_ID", this.g.getId());
        } else {
            has_group = "false";
            g = null;
        }
        System.out.println("hwFileLists");
        System.out.println(hwFileLists);
        System.out.println("end");
        this.courseInfo = new CourseInfo(CourseDAO.getCoursebyId(courseId));
        return "success";
    }

}