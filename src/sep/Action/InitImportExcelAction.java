package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sep.Model.adminAction;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InitImportExcelAction extends ActionSupport {
    private String excelfilename;
    private adminAction op=new adminAction();

    public String getExcelfilename() {
        return excelfilename;
    }

    public void setExcelfilename(String excelfilename) {
        this.excelfilename = excelfilename;
    }

    public adminAction getOp() {
        return op;
    }

    public void setOp(adminAction op) {
        this.op = op;
    }

    @Override
    public String execute() throws Exception {
        try{
            op.importTeacher(excelfilename);
            return "success";
        }catch (Exception e){
            return "error";
        }

    }

    public String importTeacher() throws Exception {

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
        PrintWriter out = null;

        try {

            op.importTeacher(excelfilename);

            out = response.getWriter();
            out.print("<script>alert('Import Teacher Success')</script>");
            out.print("<script>window.location.href='/AdminMain.jsp'</script>");
            out.flush();
            out.close();

            return "success";

        } catch (Exception e) {

            out = response.getWriter();
            out.print("<script>alert('Import Teacher Error')</script>");
            out.print("<script>window.location.href='/AdminMain.jsp'</script>");
            out.flush();
            out.close();

            return "error";
        }

    }

    public String importStudent() throws Exception {
        op=new adminAction();
        HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType("text/html;charset=UTF-8");

        response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码

        PrintWriter out = null;
        System.out.println("hahaha");
        try {

            op.importStudent(excelfilename);

            out = response.getWriter();
            out.print("<script>alert('Import Student Success')</script>");
            out.print("<script>window.location.href='/AdminMain.jsp'</script>");
            out.flush();
            out.close();

            return "success";

        } catch (Exception e) {

            out = response.getWriter();
            out.print("<script>alert('Import Student Error')</script>");
            out.print("<script>window.location.href='/AdminMain.jsp'</script>");
            out.flush();
            out.close();

            return "error";
        }
    }
}
