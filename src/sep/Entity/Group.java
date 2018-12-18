package sep.Entity;

import javafx.util.Pair;

import java.io.File;
import java.util.*;

class MySubmit{
    private Date date;
    private String path;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}


public class Group {
    private String groupId;
    private double totalscore=-1;
    private int courseID;
    private int leaderId;
    private String contact;
    private Map<String, MySubmit> submit=new HashMap<String, MySubmit>();  // homework name - submitted path    submitted homework
    // stuId-contribution, final-term
    private Map<Integer, Double> contrib=new HashMap<Integer, Double>();
    private Set<Integer> stulist=new HashSet<Integer>();
    // homework name - score, homework-wise
    private Map<String, Double> score=new HashMap<String, Double>();

    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public double getTotalScore() {
        return totalscore;
    }
    public void setTotalScore(double totalscore) {
        this.totalscore = totalscore;
    }


    public Map<Integer, Double> getContrib() {
        return contrib;
    }
    public void setContrib(Map<Integer, Double> contrib) {
        this.contrib = contrib;
    }
    public double getStuContrib(int stuId){
        if(contrib.containsKey(stuId))
            return contrib.get(stuId);
        else
            return 0.0;
    }
    public void setStuContrib(int stuId, double con){
        contrib.put(stuId, con);
    }


    public Map<String, MySubmit> getSubmit() {
        return submit;
    }
    public void setSubmit(Map<String, MySubmit> submit) {
        this.submit = submit;
    }
    public Date getSubmitTimeByName(String name){
        return submit.get(name).getDate();
    }
    public String getSubmitPathByName(String name){
        return submit.get(name).getPath();
    }
    public List<String> getUnrankedSubmit(){
        Set<String> skey=submit.keySet();
        Iterator<String> it=skey.iterator();
        List<String> res=new ArrayList<String>();
        while(it.hasNext()){
            String key=it.next();
            if(!score.containsKey(key)) {
                res.add(key);
            }
        }
        return res;
    }
    public boolean containSubmit(String name){  // check homework is submitted or not
        return submit.containsKey(name);
    }
    public int getSubmitNum(){
        return submit.size();
    }
    public void addSubmit(String name, String path){    // add & modify
        MySubmit tmp=new MySubmit();
        Date cur=new Date();
        tmp.setDate(cur);
        tmp.setPath(path);
        submit.put(name,tmp);
    }
    public void removeSubmit(String name){
        submit.remove(name);
    }


    public Set<Integer> getStulist() {
        return stulist;
    }
    public void setStulist(Set<Integer> stulist) {
        this.stulist = stulist;
    }
    public boolean containStu(int stuId){
        return stulist.contains(stuId);
    }
    public int getStuNum(){
        return stulist.size();
    }
    public void addStu(int stuId){
        stulist.add(stuId);
        contrib.put(stuId,0.0);
    }
    public void removeStu(int stuId){
        if(stulist.contains(stuId)) {
            stulist.remove(stuId);
        }
        if(contrib.containsKey(stuId)){
            contrib.remove(stuId);
        }
    }


    public Map<String, Double> getScore() {
        return score;
    }
    public void setScore(Map<String, Double> score) {
        this.score = score;
    }
    public double getScoreByHname(String hname){
        return score.get(hname);
    }
    public int getScoreNum(){
        return score.size();
    }
    public void addScore(String hname, Double subscore){
        score.put(hname,subscore);
    }
    public void calcAvg(){
        Set<String> tmps=score.keySet();
        Iterator<String> it=tmps.iterator();
        double sum=0;
        while(it.hasNext()){
            String key=it.next();
            sum+=score.get(key);
        }
        if(getScoreNum()!=0) totalscore=sum/getScoreNum();
    }

    public int getLeaderId() {
        return leaderId;
    }
    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getCourseID() {
        return courseID;
    }
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
