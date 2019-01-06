package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.Student;
import sep.Entity.StudentDAO;

public class AjaxFetchNameAction extends ActionSupport {
    private Integer stuId;
    private String name;

    public Integer getStuId() {
        return stuId;
    }
    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String execute() throws Exception{
        System.out.println("Name Determination called.");
        System.out.println("StuID: " + stuId);
        name = null;
        if (stuId != null) {
            Student s = StudentDAO.getStudentbyId(stuId);
            if (s != null) {
                name = s.getName();
            }
        }
        return SUCCESS;
    }
}
