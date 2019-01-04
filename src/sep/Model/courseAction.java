package sep.Model;

import sep.Entity.Course;
import sep.Entity.Group;

public class courseAction {

    private courseAction courseaction = new courseAction();

    public Course getCourseById(int courseId){
        // TODO DAO
        return null;
    }

    public Group getGrpById(int courseId, Integer groupId){
        Course c=getCourseById(courseId);
        return c.getGrpById(groupId);
    }

    public void createCourse(String name, int id, String desc, int teacherId){
        // TODO DAO
    }

    public void setGrpCfg(int courseId, String prefix, int maxcrew, int mincrew){
        Course c=getCourseById(courseId);
        c.setGrpPrefix(prefix);
        c.setMaxcrew(maxcrew);
        c.setMincrew(mincrew);
    }

    public courseAction getCourseaction() {
        return courseaction;
    }

    public void setCourseaction(courseAction courseaction) {
        this.courseaction = courseaction;
    }
}
