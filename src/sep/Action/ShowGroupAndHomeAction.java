package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sep.Entity.*;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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

    private String stulist;
    private String scorelist;

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

            if(g.getLeaderId()==s.getStuId()){
                session.put("IS_LEADER", 1);
            }
            else {
                session.put("IS_LEADER", 0);
            }
            session.put("GROUP_ID", this.g.getId());


            stulist="";
            scorelist="";
            Set<Integer> crewSet=g.getStulist();
            Map<Integer, Double> crewContrib=g.getContrib();
            Iterator<Integer> itt=crewSet.iterator();
            Double contribution=0.0;
            while(itt.hasNext()){
                int crewId=itt.next();
                String crewName=StudentDAO.getStudentbyId(crewId).getName();
                stulist+=crewName;
                System.out.println("Set score list");
                System.out.println(crewContrib);
                System.out.println(crewId);
                System.out.println(crewName);
                if(crewContrib.containsKey(crewId)){
                    scorelist+=Double.toString(crewContrib.get(crewId));
                    contribution+=crewContrib.get(crewId);
                }
                if(itt.hasNext()){
                    stulist+=",";
                    scorelist+=",";
                }
            }

            if(Math.abs(contribution-0.0)<1e-5){
                session.put("IS_SCORED", 0);
            }
            else{
                session.put("IS_SCORED", 1);
            }


        } else {
            has_group = "false";
            g = null;
            session.put("IS_LEADER", 0);
        }
        System.out.println("hwFileLists");
        System.out.println(hwFileLists);
        System.out.println("end");
        this.courseInfo = new CourseInfo(CourseDAO.getCoursebyId(courseId));
        return "success";
    }

    public String getStulist() {
        return stulist;
    }

    public void setStulist(String stulist) {
        this.stulist = stulist;
    }

    public String getScorelist() {
        return scorelist;
    }

    public void setScorelist(String scorelist) {
        this.scorelist = scorelist;
    }

    public String scoreOther() throws Exception{
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
        PrintWriter out = null;
        try{

            System.out.println("score other called.");
            ActionContext actionContext = ActionContext.getContext();
            Map session = actionContext.getSession();
            courseId=(int)session.get("COURSE_ID");
            s=StudentDAO.getStudentbyId((int)session.get("USER_ID"));
            g=GroupDAO.getGroupById(s.getGroupId(courseId));

            List<Integer> crewList=new ArrayList<Integer>();
            Set<Integer> crewSet=g.getStulist();
            Iterator<Integer> ittt=crewSet.iterator();
            while(ittt.hasNext()){
                crewList.add(ittt.next());
            }
            String[] strlist=scorelist.split(",");

            for(int i=0;i<strlist.length;i++){
                System.out.println(crewList.get(i));
                System.out.println(Double.parseDouble(strlist[i]));
                System.out.println(s.getGroupId(courseId));
                GroupDAO.addContrib(s.getGroupId(courseId), crewList.get(i), Double.parseDouble(strlist[i]));

            }


            out = response.getWriter();
            out.print("<script>alert('评分成功')</script>");
            out.print("<script>window.location.href='/sep/Action/showGroupAndHome.action?courseId="+Integer.toString(courseId)+ "'</script>");
            out.flush();
            out.close();

            session.put("IS_SCORED", 1);
            return "success";
        }catch(Exception e){
            e.printStackTrace();

            out = response.getWriter();
            out.print("<script>alert('输入格式错误')</script>");
            out.print("<script>window.location.href='/StuGroupAndHomework.jsp'</script>");
            out.flush();
            out.close();
            return "error";
        }

    }
}