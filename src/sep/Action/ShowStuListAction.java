package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShowStuListAction extends ActionSupport {
    private Integer courseId;
    private List<StudentInfo> stuInfo;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public List<StudentInfo> getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(List<StudentInfo> stuInfo) {
        this.stuInfo = stuInfo;
    }

    public List<Homework> getHwList() {
        return hwList;
    }

    public void setHwList(List<Homework> hwList) {
        this.hwList = hwList;
    }

    private List<Homework> hwList;

    public String execute() {
        System.out.println("ShowStuListAction called.");
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        if (session.containsKey("COURSE_ID")) {
            courseId = (Integer)session.get("COURSE_ID");
        }
        Course c = CourseDAO.getCoursebyId(courseId);
        hwList = c.getHomework();
        stuInfo = new ArrayList<>();

        List<Student> stuLists = StudentDAO.getStudentByCourseId(courseId);
        for (Iterator i = stuLists.iterator(); i.hasNext(); ){
            Student s = (Student) i.next();
            StudentInfo sInfo = new StudentInfo();
            sInfo.setStuId(s.getStuId());
            sInfo.setName(s.getName());

            sInfo.setClassid(s.getClassid());
            List<Double> hwScore = new ArrayList<>();
            List<Double> hwPer = new ArrayList<>();
            Integer grpId = s.getGroupmap().get(courseId);
            if (grpId != null) {
                double contrib = -1.0;
                Group g = GroupDAO.getGroupById(grpId);
                if (g.getContrib().containsKey(s.getStuId())) {
                    contrib = g.getStuContrib(s.getStuId());
                }
                sInfo.setGrpContrib(contrib);
                for (Iterator h = hwList.iterator(); h.hasNext(); ) {
                    Homework hw = (Homework) h.next();
                    if (g.getScore().containsKey(hw.getName())) {
                        hwScore.add(g.getScore().get(hw.getName()));
                        hwPer.add(CourseDAO.getCoursebyId(courseId).getHomeByName(hw.getName()).getPercentage());
                    } else {
                        hwScore.add(-1.0);
                    }
                }

            } else {
                sInfo.setGrpContrib(-2.0);
                for (int idx = 0; idx < hwList.size(); idx++) {
                    hwScore.add(-1.0);

                }
            }
            sInfo.setHwScore(hwScore);
            sInfo.setHwPercentage(hwPer);
            System.out.println("ALL SCORE :");
            System.out.println(sInfo.calcAllscore());
            stuInfo.add(sInfo);
            System.out.println(sInfo.getName() + sInfo.getGrpContrib().toString());
        }

        return "success";
    }
}
