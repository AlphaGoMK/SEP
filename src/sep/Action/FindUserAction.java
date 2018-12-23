package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sep.Entity.Student;
import sep.Entity.Teacher;
import sep.Model.InitInfo;
import sep.Model.adminAction;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

public class FindUserAction extends ActionSupport {
    private String uid;
    private adminAction op;
    private int flag; // 1 for teacher, 2 for student
    private InitInfo usr;
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
                Teacher t=op.getTeacherById(Integer.parseInt(uid));
            }
            else {
                Student s=op.getStudentById(Integer.parseInt(uid));
                usr.setName(s.getName());
                usr.setId(s.getStuId());
                usr.setPassword(s.getPassword());
                usr.setAttr1(s.getClassid());
                Set<Integer> tmp=s.getCourseset();
                Iterator<Integer> it=tmp.iterator();
                String cset="";
                while(it.hasNext()){
                    int cid=it.next();
                    cset+=(Integer.toString(cid));

                    // Debug needed
                    if(it.hasNext()) cset+=",";
                }
                usr.setAttr2(cset);
            }

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

    public InitInfo getUsr() {
        return usr;
    }

    public void setUsr(InitInfo usr) {
        this.usr = usr;
    }
}
