package sep.Action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

public class SelectAction extends ActionSupport {
    private String act;

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    @Override
    public String execute() throws Exception {

        return SUCCESS;
    }
}
