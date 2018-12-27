package sep.Model;

import sep.Entity.Course;
import sep.Entity.Group;

public class courseAction {

    public Course getCourseById(int courseId){
        // TODO DAO
        return null;
    }

    public Group getGrpById(int courseId, Integer groupId){
        Course c=getCourseById(courseId);
        return c.getGrpById(groupId);
    }

    public static void setCourse(int name, int id, String desc, int teacherId){
        // TODO DAO
    }

    public void setGrpCfg(int courseId, String prefix, int maxcrew, int mincrew){
        Course c=getCourseById(courseId);
        c.setGrpPrefix(prefix);
        c.setMaxcrew(maxcrew);
        c.setMincrew(mincrew);
    } 
}
