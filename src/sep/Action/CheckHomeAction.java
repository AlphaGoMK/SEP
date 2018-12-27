package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.Homework;
import sep.Model.courseAction;

import java.util.Date;
import java.util.List;

// display unranked group homework
public class CheckHomeAction extends ActionSupport {
    // input
    public int courseId;
    public Integer grpId;

    // set
    private List<String> unrankedhome;
    private courseAction courseaction=new courseAction();

    @Override
    public String execute() throws Exception{
        unrankedhome=courseaction.getGrpById(courseId, grpId).getUnrankedSubmit();

        return SUCCESS;
    }

    public List<String> getUnrankedhome() {
        return unrankedhome;
    }

    public void setUnrankedhome(List<String> unrankedhome) {
        this.unrankedhome = unrankedhome;
    }
}
