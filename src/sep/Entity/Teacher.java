package sep.Entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Teacher {
    private int teacherId;
    private String name;
    private Set<Integer> courseset=new HashSet<Integer>();

    public Teacher(){}
    public Teacher(String teacherName){
        this.name=teacherName;
    }

    public int getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getCourseset() {
        return courseset;
    }
    public void setCourseset(Set<Integer> courseset) {
        this.courseset = courseset;
    }
    public void clearCourse(){
        courseset.clear();
    }
    public boolean containCourse(Integer course){
        return courseset.contains(course);
    }
    public int getCourseNum(){
        return courseset.size();
    }
    public void addCourse(Integer course){
        courseset.add(course);
    }
    public void removeCourse(Integer course){
        if(courseset.contains(course)) courseset.remove(course);
    }

    @Override
    public int hashCode(){
        return teacherId+name.hashCode()*31;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null||getClass()!=o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if(teacherId!=teacher.teacherId) return false;
        if(name!=null?!name.equals(teacher.name):teacher.name!=null) return false;

        Iterator<Integer> courseit=courseset.iterator();
        while(courseit.hasNext()){
            Integer c=courseit.next();
            if(!teacher.courseset.contains(c)) return false;
        }

        return true;
    }


}
