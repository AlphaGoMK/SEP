package sep.Action;

import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.Group;
import sep.Model.courseAction;

import java.util.*;

// stu&group
public class ListAllStuAction extends ActionSupport {
    private int courseId;
    private courseAction courseaction=new courseAction();
    private Map<String, Set<Integer>> stulist;
    private List<String> grpname;

    public void getAllGrp(){
        grpname=new ArrayList<String>();
        Set<Group> tmp=courseaction.getCourseById(courseId).getGrp();
        Iterator<Group> it=tmp.iterator();
        while(it.hasNext()){
            grpname.add(it.next().getGroupId());
        }
    }

    public void getAllStu(){
        stulist=new TreeMap<String, Set<Integer>>();
        for(int i=0;i<grpname.size();i++){
            Set<Integer> li=courseaction.getGrpById(courseId, grpname.get(i)).getStulist();
            stulist.put(grpname.get(i),li);
        }
    }

    public String execute() throws Exception{
        getAllGrp();
        getAllStu();
        return SUCCESS;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
