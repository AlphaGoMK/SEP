package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sep.Model.adminAction;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class FindUserAction extends ActionSupport {
    private String uid;
    private adminAction op;
    private int flag; // 1 for teacher, 2 for student
    private Object res;
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String execute() throws Exception{

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
        PrintWriter out = null;

        try{
            flag=op.getUserType(Integer.parseInt(uid));
            if(flag==1){
                res=op.getTeacherById(Integer.parseInt(uid));
            }
            else res=op.getStudentById(Integer.parseInt(uid));

//            out = response.getWriter();
//            out.print("<script>window.location.reload(true)'</script>");
//            out.flush();
//            out.close();

            return "success";

        }catch(Exception e){

            out = response.getWriter();
            out.print("<script>alert('Only integer is allowed')</script>");
            out.print("<script>window.location.href='/FindUser.jsp'</script>");
            out.flush();
            out.close();

            return "error";
        }

    }
}
