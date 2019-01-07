package sep.Action;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import sep.Entity.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

public class SubmitHomeAction extends ActionSupport {

    private File hwFile;
    private String hwFileContentType;
    private String hwFileFileName;
    private String destPath;
    private String hwName;

    public String execute()
    {
        /* Copy file to a safe location */

//        this.destPath = "C:/Workspace/Courses/Software Engineering/SEP/hw/";
//        this.destPath = "/hw/";
//        System.out.println("SUBMIT HOMEWORK");



        try{
            HttpServletRequest request = ServletActionContext.getRequest();
            ActionContext actionContext = ActionContext.getContext();
            Map session = actionContext.getSession();

            String absolutepath= request.getSession().getServletContext().getRealPath("/");
            System.out.println("save file path "+absolutepath);

            String relativepath = absolutepath+"..\\..\\..\\hw\\"+Integer.toString((int)session.get("GROUP_ID"))+"\\";
            File myPath = new File(relativepath);

            myPath.mkdirs();
            this.destPath=relativepath;

            System.out.println(this.destPath + hwFileFileName);
            File destFile  = new File(destPath, hwFileFileName);
            FileUtils.copyFile(hwFile, destFile);

            Integer stuId = (Integer)session.get("USER_ID");

            if (GroupDAO.submitHome((Integer)session.get("GROUP_ID"), hwName, destPath + hwFileFileName, stuId)) {
                System.out.println("Submit success!");
                return "success";
            } else {
                return "error";
            }
        }catch(IOException e){
            e.printStackTrace();
            return "error";
        }
    }

    public File getHwFile() {
        return hwFile;
    }
    public void setHwFile(File hwFile) {
        this.hwFile = hwFile;
    }

    public String getHwFileContentType() {
        return hwFileContentType;
    }
    public void setHwFileContentType(String hwFileContentType) {
        this.hwFileContentType = hwFileContentType;
    }

    public String getHwFileFileName() {
        return hwFileFileName;
    }
    public void setHwFileFileName(String hwFileFileName) {
        this.hwFileFileName = hwFileFileName;
    }

    public String getDestPath() {
        return destPath;
    }
    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public String getHwName() {
        return hwName;
    }
    public void setHwName(String hwName) {
        this.hwName = hwName;
    }

}
