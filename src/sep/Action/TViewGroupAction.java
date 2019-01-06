package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.Group;
import sep.Entity.GroupDAO;
import sep.Entity.StudentDAO;
import sep.Model.courseAction;

import java.util.*;

class GroupInfo{
    private String grpId;
    private String leadername;
    private String stulist;

    public GroupInfo(Group g){
        this.grpId=g.getGroupId();
        this.leadername= StudentDAO.getStudentbyId(g.getLeaderId()).getName();
        this.stulist="";
        Set<Integer> ss=g.getStulist();
        Iterator<Integer> it=ss.iterator();
        while(it.hasNext()){
            this.stulist+=(StudentDAO.getStudentbyId(it.next()).getName()+",");
        }
        int len=this.stulist.length();
        if(len!=0){
            this.stulist = this.stulist.substring(0, len-1);
        }
    }

    public String getGrpId() {
        return grpId;
    }

    public void setGrpId(String grpId) {
        this.grpId = grpId;
    }

    public String getLeadername() {
        return leadername;
    }

    public void setLeadername(String leadername) {
        this.leadername = leadername;
    }

    public String getStulist() {
        return stulist;
    }

    public void setStulist(String stulist) {
        this.stulist = stulist;
    }
}
public class TViewGroupAction extends ActionSupport {
    private int courseId;
    private int teacherId;
    private List<GroupInfo> grpList;
    private courseAction ca=new courseAction();

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String execute(){
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        session.put("COURSE_ID", courseId);
        grpList=new ArrayList<GroupInfo>();
        System.out.println("success");
        List<Group> gp= GroupDAO.getGroupListByCourseId(courseId);
        Iterator<Group> it=gp.iterator();
        while(it.hasNext()){
            Group tmp=it.next();
            GroupInfo tmpinfo=new GroupInfo(tmp);
            grpList.add(tmpinfo);
        }
        System.out.println("courseid: "+Integer.toString(courseId));
        System.out.println("length: "+Integer.toString(gp.size()));
        return "success";
    }

    public List<GroupInfo> getGrpList(){
        return grpList;
    }
    public void setGrpList(List<GroupInfo> grpList){
        this.grpList=grpList;
    }
    public courseAction getCa() {
        return ca;
    }

    public void setCa(courseAction ca) {
        this.ca = ca;
    }

}
