package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import sep.Model.InitInfo;
import sep.Model.adminAction;


public class EditUserAction {
    private int opflag;
    private adminAction op;
    private InitInfo usr;

    public int getOpflag() {
        return opflag;
    }

    public void setOpflag(int opflag) {
        this.opflag = opflag;
    }

    public adminAction getOp() {
        return op;
    }

    public void setOp(adminAction op) {
        this.op = op;
    }

    public String edit(){
        try{
            op.editUser(usr);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    public String remove(){
        try{
            op.removeUser(usr);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }
    public InitInfo getUsr() {
        return usr;
    }

    public void setUsr(InitInfo usr) {
        this.usr = usr;
    }
}
