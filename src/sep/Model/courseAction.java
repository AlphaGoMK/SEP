package sep.Model;

import sep.Entity.Course;
import sep.Entity.CourseDAO;
import sep.Entity.Group;

public class courseAction {

    public Course getCourseById(int courseId){
        return CourseDAO.getCoursebyId(courseId);
    }

    public Group getGrpById(int courseId, Integer groupId){
        Course c=getCourseById(courseId);
        return c.getGrpById(groupId);
    }

<<<<<<< HEAD
    public void createCourse(String name, String desc, int teacherId, int maxcrew, int mincrew){
        System.out.println("name: "+name);
        System.out.println("maxcrew: "+Integer.toString(maxcrew));
        System.out.println(maxcrew);
        CourseDAO.addCourse(name, desc, teacherId, maxcrew, mincrew);
=======
    public static void setCourse(int name, int id, String desc, int teacherId){
        // TODO DAO
>>>>>>> parent of 8f293e6... Commit
    }

    public void setGrpCfg(int courseId, String prefix, int maxcrew, int mincrew){
        Course c=getCourseById(courseId);
        c.setGrpPrefix(prefix);
        c.setMaxcrew(maxcrew);
        c.setMincrew(mincrew);
<<<<<<< HEAD
    }
=======
    } 
>>>>>>> parent of 8f293e6... Commit
}
