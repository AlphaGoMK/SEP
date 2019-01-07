package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sep.Entity.Group;
import sep.Entity.GroupDAO;
import sep.Entity.MySubmit;

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

    @Override
    public String execute() throws Exception{
        try{
            HttpServletRequest request = ServletActionContext.getRequest();
            ActionContext actionContext = ActionContext.getContext();
            Map session = actionContext.getSession();
            submitId=(int)session.get("SUBMIT_ID");
            grpId=(int)session.get("GROUP_ID");
            courseId=(int)session.get("COURSE_ID");
            teacherId=(int)session.get("TEACHER_ID");
            mysubmit= GroupDAO.getSubmitById(submitId);
            homeworkname=mysubmit.getHomeworkname();
            submittime=mysubmit.getDate();
            String fullpath=mysubmit.getPathList().get(mysubmit.getPathList().size()-1);
            String[] splitted = fullpath.split("\\/");
            filename=splitted[splitted.length - 1];
            // TODO: UPLOADER NAME

            downloadUrl="localhost:8081\\hw\\"+Integer.toString(grpId)+"\\";

            System.out.println("downloadUrl:  "+downloadUrl);
            if(mysubmit.isRanked()==true){
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

        //TODO DAO?
        return null;
    }
}
