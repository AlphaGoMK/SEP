package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.StudentDAO;
import sep.Entity.TeacherDAO;

public class LoginAction extends ActionSupport {
    private String uid;
    private String passwd;

    public String execute() throws Exception{
        return check();
    }

    public String check() throws Exception{
        // admin check

        if (uid.equals(new String("admin"))) {
            if (passwd.equals(new String("admin"))) {
                return "admin";
            } else {
                return "error";
            }
        }

        boolean test = false;
        // teacher check
        TeacherDAO tdao = new TeacherDAO();
        Integer id = Integer.parseInt(this.uid);
        test = tdao.checkExistence(id, this.passwd);
        if (test) {
            return "teacher";
        }
        // student check
        StudentDAO sdao = new StudentDAO();
        test = sdao.checkExistence(id, this.passwd);
        if (test) {
            return "teacher";
        }

        return "error";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
