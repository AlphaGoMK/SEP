package sep.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.*;

import static java.lang.Math.abs;

@Entity
public class Student {
    private int stuId;
    private String name;
    private double score;
    private String classid;
    private String password;
    // courseId - groupId
    private Map<Integer, Integer> groupmap=new HashMap<Integer,Integer>();
    private Set<Integer> courseset=new HashSet<Integer>();

    public Student(){}
    public Student(String name, String classid){
        this.name = name;
        this.classid = classid;
        this.password = "123456";
    }

    public Student(Integer stuId, String name, String classid){
        this.stuId = stuId;
        this.name = name;
        this.classid = classid;
        this.password = "123456";
    }

    public int getStuId(){
        return stuId;
    }
    public void setStuId(int stuId){
        this.stuId=stuId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public double getScore(){
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }

    public String getClassid() {
        return classid;
    }
    public void setClassid(String classid) {
        this.classid = classid;
    }

    public Map<Integer, Integer> getGroupmap(){
        return groupmap;
    }
    public void setGroupmap(Map<Integer, Integer> groupmap){
        this.groupmap=groupmap;
    }
    public void clearGroupmap(){
        groupmap.clear();
    }
    public Integer getGroupId(Integer course){
        if(groupmap.containsKey(course)) return groupmap.get(course);
        else return null;
    }
    public void setGroupId(Integer course, Integer grpId){
        groupmap.put(course, grpId);
    }

    public Set<Integer> getCourseset(){
        return courseset;
    }
    public void setCourseset(Set<Integer> courseset){
        this.courseset=courseset;
    }
    public void clearCourseset(){
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
        if(groupmap.containsKey(course)) groupmap.remove(course);
    }

    @Override
    public int hashCode(){
        return stuId+name.hashCode()*31;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null||getClass()!=o.getClass()) return false;

        Student student = (Student) o;

        if(stuId!=student.stuId) return false;
        if(name!=null?!name.equals(student.name):student.name!=null) return false;
        if(abs(score-student.score)>1e-4) return false;
        if(classid!=null?!classid.equals(student.classid):student.classid!=null) return false;

        Set<Integer> keySet=groupmap.keySet();
        Set<Integer> stuKeySet=student.groupmap.keySet();
        if(keySet.size()!=stuKeySet.size()) return false;
        Iterator<Integer> it=keySet.iterator();
        while(it.hasNext()){
            Integer key=it.next();
            if(groupmap.get(key)==student.groupmap.get(key)) return false;
        }

        Iterator<Integer> courseit=courseset.iterator();
        while(courseit.hasNext()){
            Integer c=courseit.next();
            if(!student.courseset.contains(c)) return false;
        }

        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
