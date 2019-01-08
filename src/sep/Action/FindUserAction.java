package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sep.Entity.Student;
import sep.Entity.Teacher;
import sep.Model.InitInfo;
import sep.Model.adminAction;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

public class FindUserAction extends ActionSupport {
    private String uid;
    private adminAction op=new adminAction();
    private String flag; // 1 for teacher, 2 for student
    private InitInfo usr;
    private String name;
    private String classid;
    private boolean ateacher;
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
            ateacher=(flag.equals("teacher")?true:false);
            System.out.println("Admin find user: ");
            System.out.println(ateacher);
            if(ateacher){
                Teacher t=op.getTeacherById(Integer.parseInt(uid));
                name=t.getName();
            }
            else {
                Student s=op.getStudentById(Integer.parseInt(uid));
                name=s.getName();
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

    public String add(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
        PrintWriter out = null;

        ateacher=(flag.equals("teacher")?true:false);
        System.out.println("Admin add user:");
        System.out.println(name);
        System.out.println(flag);
        System.out.println(ateacher);

        usr=new InitInfo();
        usr.setName(name);
        if(!ateacher){
            usr.setAttr1(classid);
            usr.setId(Integer.parseInt(uid));
        }


        try{
            if(!ateacher&&op.getStudentById(usr.getId())!=null){
                out = response.getWriter();
                out.print("<script>alert('Student Already Exists')</script>");
                out.print("<script>window.location.href='/AddUser.jsp'</script>");
                out.flush();
                out.close();
                return "error";
            }
            else{
                op.addUser(ateacher, usr);

                return "success";
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Add Error");
            return "error";
        }

    }


    public void setOp(adminAction op) {
        this.op = op;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }


    public boolean isAteacher() {
        return ateacher;
    }

    public void setAteacher(boolean ateacher) {
        this.ateacher = ateacher;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
