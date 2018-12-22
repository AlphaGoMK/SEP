package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import sep.Model.adminAction;

public class InitImportExcelAction extends ActionSupport {
    private String excelfilename;
    private adminAction op;


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
}
