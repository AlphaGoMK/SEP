package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import javafx.util.Pair;
import sep.Entity.StudentDAO;

import java.util.List;

public class AjaxGetSearchSuggestAction extends ActionSupport {
    private String name;
    private Integer courseId;
    private List<String> nameList;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNameList() {
        return nameList;
    }
    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }


    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String execute() throws Exception {
        System.out.println("Search suggestion called.");
        System.out.println("Name: " + name);
        System.out.println("CourseId: " + courseId);

        if(name != null && !name.equals("")){
            //将值放入集合中
            nameList = StudentDAO.getNameSuggest(this.name, this.courseId);
            System.out.println(nameList);
        }
        return SUCCESS;
    }

}
