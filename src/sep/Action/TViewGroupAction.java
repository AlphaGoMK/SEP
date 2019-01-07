package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.Group;
import sep.Entity.GroupDAO;
import sep.Entity.MySubmit;
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

class SubmitInfo{
    private String homeworkname;
    private String filename;
    private String uploader;
    private Date submittime;

    public SubmitInfo(MySubmit mysubmit){
        homeworkname=mysubmit.getHomeworkname();
        String path=mysubmit.getPathList().get(mysubmit.getPathList().size()-1);
        String[] splitted = path.split("\\/");
        filename=splitted[splitted.length - 1];
        // TODO: uploader
        uploader="xxx";
        submittime=mysubmit.getDate();
    }

    public String getHomeworkname() {
        return homeworkname;
    }

    public void setHomeworkname(String homeworkname) {
        this.homeworkname = homeworkname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }
}

public class TViewGroupAction extends ActionSupport {
    private int courseId;
    private int teacherId;
    private List<GroupInfo> grpList;
    private courseAction ca=new courseAction();
//    private int grpId;
//    private List<SubmitInfo> submitList;

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
        session.put("SELECTED", 1);
        teacherId=(int)session.get("USER_ID");
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

//    public String flushSubmitList(){
//        ActionContext actionContext = ActionContext.getContext();
//        Map session = actionContext.getSession();
//        session.put("COURSE_ID", courseId);
//        session.put("SELECTED", 1);
//        teacherId=(int)session.get("USER_ID");
//        session.put("GROUP_ID", grpId);
//
//        System.out.println("FLUSH SUBMIT");
//        // TODO: group dao
//
//        submitList=new ArrayList<SubmitInfo>();
//
//        return "success";
//
//    }

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

//    public int getGrpId() {
//        return grpId;
//    }
//
//    public void setGrpId(int grpId) {
//        this.grpId = grpId;
//    }
//
//    public List<SubmitInfo> getSubmitList() {
//        return submitList;
//    }
//
//    public void setSubmitList(List<SubmitInfo> submitList) {
//        this.submitList = submitList;
//    }
}