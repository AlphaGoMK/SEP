package sep.Entity;

import java.util.*;

// course with exact teacher and multiple students
public class Course {
    private String name;
    private int courseId;
    private Date date=new Date();   // current time
    private String description;
    private int teacherid;
    private List<Homework> homework=new ArrayList<Homework>();  // requested
    private int maxcrew;
    private int mincrew;
    private Set<Group> grp=new HashSet<Group>();
    private String grpPrefix;

    public Course(){}
    public Course(String name, String description, Integer teacherid, Integer maxcrew, Integer mincrew) {
        this.name = name;
        this.description = description;
        this.date = new Date();
        this.teacherid = teacherid;
        this.maxcrew = maxcrew;
        this.mincrew = mincrew;
        this.homework = new ArrayList<Homework>();
        this.grp = new HashSet<Group>();
        this.grpPrefix = "";
    }

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date){
        this.date=date;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getTeacherid() {
        return teacherid;
    }
    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public String getGrpPrefix() {
        return grpPrefix;
    }
    public void setGrpPrefix(String grpPrefix) {
        this.grpPrefix=grpPrefix;
    }

    public int getMaxcrew() {
        return maxcrew;
    }
    public void setMaxcrew(int maxcrew) {
        this.maxcrew = maxcrew;
    }

    public int getMincrew() {
        return mincrew;
    }
    public void setMincrew(int mincrew) {
        this.mincrew = mincrew;
    }


    public Set<Group> getGrp() {
        return grp;
    }
    public void setGrp(Set<Group> grp) {
        this.grp = grp;
    }
    public void clearGrp(){
        grp.clear();
    }
    public int getGrpNum(){
        return grp.size();
    }
    public Group getGrpById(Integer grpId){
        Group res;
        Iterator<Group> it=grp.iterator();
        while(it.hasNext()){
            res=it.next();
            if(res.getGroupId().equals(grpId)){
                return res;
            }
        }
        return null;
    }
    public void addGrp(Group group){
        grp.add(group);
    }
    public void removeGrpById(Integer grpId){
        Group tmp=getGrpById(grpId);
        grp.remove(tmp);
    }


    public List<Homework> getHomework() {
        return homework;
    }
    public void setHomework(List<Homework> homework) {
        this.homework = homework;
    }
    public void clearHome(){
        homework.clear();
    }
    public int getHomeNum(){
        return homework.size();
    }
    public Homework getHomeByName(String hname){
        Homework res;
        Iterator<Homework> it=homework.iterator();
        while(it.hasNext()){
            res=it.next();
            if(res.getName().equals(hname)) return res;
        }
        return null;
    }

    public Homework getHomeByIdx(int index){
        return homework.get(index);
    }

    public void addHome(String name, String content, Date deadline, double percentage){
        Homework h=new Homework(name, this.courseId, content, deadline, percentage);
        homework.add(h);
    }

    public void removeHomeByName(String hname){
        Homework tmp=getHomeByName(hname);
        homework.remove(tmp);
    }

    public void setHomePercent(String hname, double per){
        Homework h=getHomeByName(hname);
        h.setPercentage(per);
    }

    public double getScoreById(int stuId, Integer grpId){
        Group g=getGrpById(grpId);
        double grpscore=0.0,sumpercent=0.0;
        for(int i=0;i<getHomeNum();i++){
            sumpercent+=homework.get(i).getPercentage();
        }
        for(int i=0;i<getHomeNum();i++){
            grpscore+=g.getScoreByHname(homework.get(i).getName())*(homework.get(i).getPercentage()/sumpercent);
        }
        return (grpscore*g.getStuContrib(stuId))/100.0;
    }

}
