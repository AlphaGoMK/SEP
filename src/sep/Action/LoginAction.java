package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.Student;
import sep.Entity.StudentDAO;
import sep.Entity.Teacher;
import sep.Entity.TeacherDAO;

import java.util.Map;

public class LoginAction extends ActionSupport {
    private String uid;
    private String passwd;
    private String userName;

    public String execute() throws Exception{
        //return teacherCheck();
        return check();
    }

    public String teacherCheck() throws Exception{
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        session.put("USER_NAME", "TEST");
        session.put("USER_ID", "123456");
        session.put("USER_TYPE", "teacher");
        return "teacher";
    }

    public String check() throws Exception{
        // admin check
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        if(uid.equals("") || passwd.equals("")) {
            addFieldError("filedError", "用户名或密码为空");
            return "input";
        }
        if (uid.equals(new String("admin"))) {
            if (passwd.equals(new String("admin"))) {
                session.put("USER_TYPE", "admin");
                return "admin";
            } else {
                addFieldError("filedError", "用户名或密码错误");
                return "input";
            }
        }

        boolean test = false;
        // teacher check
        TeacherDAO tdao = new TeacherDAO();
        Integer id = Integer.parseInt(this.uid);
        test = tdao.checkExistence(id, this.passwd);
        if (test) {

            Teacher t = tdao.getTeacherbyId(id);
            session.put("USER_NAME", t.getName());
            session.put("USER_ID", t.getTeacherId());
            session.put("USER_TYPE", "teacher");
            return "teacher";
        }

        // student check
        StudentDAO sdao = new StudentDAO();
        test = sdao.checkExistence(id, this.passwd);
        if (test) {
            Student s = sdao.getStudentbyId(id);
            session.put("USER_ID", s.getStuId());
            session.put("USER_NAME", s.getName());
            session.put("USER_TYPE", "student");
            return "student";
        }
        addFieldError("filedError", "用户名或密码错误");
        return "input";
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
