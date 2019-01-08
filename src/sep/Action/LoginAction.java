package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sep.Entity.Student;
import sep.Entity.StudentDAO;
import sep.Entity.Teacher;
import sep.Entity.TeacherDAO;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class LoginAction extends ActionSupport {
    private String uid;
    private String passwd;
    private String userName;
    private String passwd2;

    public boolean isLegalPwd(String s){
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]\\w*$";
        return s.matches(regex);
    }

    public String execute() throws Exception{
        return check();
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

        System.out.println("test teacher");
        boolean test = false;
        // teacher check
        TeacherDAO tdao = new TeacherDAO();
        Integer id = Integer.parseInt(this.uid);
        test = tdao.checkExistence(id, this.passwd);
        if (test) {

            if(this.passwd.equals("123456")){
                session.put("USER_ID", this.uid);
                return "init";
            }

            System.out.println("teacher test OK");
            Teacher t = tdao.getTeacherbyId(id);
            session.put("USER_NAME", t.getName());
            session.put("USER_ID", t.getTeacherId());
            session.put("USER_TYPE", "teacher");
            session.put("SELECTED", 0);
            return "teacher";
        }

        // student check
        StudentDAO sdao = new StudentDAO();
        test = sdao.checkExistence(id, this.passwd);
        if (test) {

            if(this.passwd.equals("123456")){
                session.put("USER_ID", this.uid);
                return "init";
            }

            Student s = sdao.getStudentbyId(id);
            session.put("USER_ID", s.getStuId());
            session.put("USER_NAME", s.getName());
            session.put("USER_TYPE", "student");
            return "student";
        }
        addFieldError("filedError", "用户名或密码错误");
        return "input";
    }

    public String changePassword() throws Exception{
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
        PrintWriter out = null;

        if(!passwd.equals(passwd2)){
            // 此时已经产生response，不会按照struts.xml中执行error对应的jsp
            out = response.getWriter();
            out.print("<script>alert('两次输入密码不一致')</script>");
            out.print("<script>window.location.href='/CreatePassword.jsp'</script>");
            out.flush();
            out.close();
            return "error";
        }
        if(!isLegalPwd(passwd)){
            out = response.getWriter();
            out.print("<script>alert('密码必须为6-10位，且包含字母和数字')</script>");
            out.print("<script>window.location.href='/CreatePassword.jsp'</script>");
            out.flush();
            out.close();
            return "error";
        }

        if(TeacherDAO.checkExistence(Integer.parseInt(uid),"123456")){
            TeacherDAO.changePassword(Integer.parseInt(uid), passwd);
            out = response.getWriter();
            out.print("<script>alert('修改成功，请登录')</script>");
            out.print("<script>window.location.href='/Login.jsp'</script>");
            out.flush();
            out.close();

            return "success";
        }
        else if(StudentDAO.checkExistence(Integer.parseInt(uid),"123456")){
            StudentDAO.changePassword(Integer.parseInt(uid), passwd);
            out = response.getWriter();
            out.print("<script>alert('修改成功，请登录')</script>");
            out.print("<script>window.location.href='/Login.jsp'</script>");
            out.flush();
            out.close();
            return "success";
        }
        else {
            out = response.getWriter();
            out.print("<script>alert('找不到用户')</script>");
            out.print("<script>window.location.href='/CreatePassword.jsp'</script>");
            out.flush();
            out.close();
            return "error";
        }
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

    public String getPasswd2() {
        return passwd2;
    }

    public void setPasswd2(String passwd2) {
        this.passwd2 = passwd2;
    }
}
