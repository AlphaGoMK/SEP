package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShowStuListAction extends ActionSupport {
    Integer courseId;
    List<StudentInfo> stuInfo;
    List<Homework> hwList;

    public String execute() {
        System.out.println("ShowStuListAction called.");
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        if (session.containsKey("COURSE_ID")) {
            courseId = (Integer)session.get("COURSE_ID");
        }
        Course c = CourseDAO.getCoursebyId(courseId);
        hwList = c.getHomework();

        List<Student> stuLists = StudentDAO.getStudentByCourseId(courseId);
        for (Iterator i = stuLists.iterator(); i.hasNext(); ){
            Student s = (Student) i.next();
            StudentInfo sInfo = new StudentInfo();
            sInfo.setStuId(s.getStuId());
            sInfo.setName(s.getName());
            sInfo.setClassid(s.getClassid());
            List<Double> hwScore = new ArrayList<>();
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
                    } else {
                        hwScore.add(-1.0);
                    }
                }
            } else {
                sInfo.setGrpContrib(-1.0);
                for (int idx = 0; idx < hwList.size(); idx++) {
                    hwScore.add(0.0);
                }
            }
        }
        return "success";
    }
}
