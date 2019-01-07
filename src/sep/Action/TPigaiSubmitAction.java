package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sep.Entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

public class TPigaiSubmitAction extends ActionSupport {
    private int submitId;
    private int grpId;
    private int courseId;
    private int teacherId;
    private String downloadUrl;
    private double score;
    private String homeworkname;
    private String filename;
    private String uploadername;
    private Date submittime;
    private MySubmit mysubmit;
    private boolean ranked;

    @Override
    public String execute() throws Exception{
        try{
            HttpServletRequest request = ServletActionContext.getRequest();
            ActionContext actionContext = ActionContext.getContext();
            Map session = actionContext.getSession();
            System.out.println("submit id: "+Integer.toString(submitId));
            courseId=(int)session.get("COURSE_ID");
            teacherId=(int)session.get("USER_ID");
            session.put("SUBMIT_ID", submitId);
            mysubmit= GroupDAO.getSubmitById(submitId);
            grpId=mysubmit.getGrpId();
            homeworkname=mysubmit.getHomeworkname();
            submittime=mysubmit.getDate();
            String fullpath=mysubmit.getPathList().get(mysubmit.getPathList().size()-1);
            String[] splitted = fullpath.split("\\\\");
            System.out.println(splitted);
            filename=splitted[splitted.length - 1];
            uploadername= StudentDAO.getStudentbyId(mysubmit.getUploaderId()).getName();

            downloadUrl="\\hw\\"+Integer.toString(grpId)+"\\"+filename;

            System.out.println("downloadUrl:  "+downloadUrl);
            ranked=GroupDAO.getGroupById(mysubmit.getGrpId()).getScore().containsKey(mysubmit.getHomeworkname());
            if(ranked==true){
                session.put("IS_RANKED", 1);
                score=GroupDAO.getGroupById(grpId).getScoreByHname(homeworkname);
            }
            else{
                session.put("IS_RANKED", 0);
            }
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }

    }

    public String scoreSubmit() throws Exception{
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        submitId=(int)session.get("SUBMIT_ID"); // 不同值栈，重新赋值
        System.out.println("score");
        System.out.println(submitId);
        System.out.println(score);
        TeacherDAO.scoreHomework(submitId, score);
        return "success";
    }

    public boolean isRanked() {
        return ranked;
    }

    public void setRanked(boolean ranked) {
        this.ranked = ranked;
    }

    public int getSubmitId() {
        return submitId;
    }

    public void setSubmitId(int submitId) {
        this.submitId = submitId;
    }

    public int getGrpId() {
        return grpId;
    }

    public void setGrpId(int grpId) {
        this.grpId = grpId;
    }

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

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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

    public String getUploadername() {
        return uploadername;
    }

    public void setUploadername(String uploadername) {
        this.uploadername = uploadername;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    public MySubmit getMysubmit() {
        return mysubmit;
    }

    public void setMysubmit(MySubmit mysubmit) {
        this.mysubmit = mysubmit;
    }
}
