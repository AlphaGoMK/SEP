package sep.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySubmit{
    private int id;
    private int courseId;
    private int grpId;
    private String homeworkname;
    private Date date;
    private List<String> pathList = new ArrayList<String>();
    private boolean ranked=false;
    private Integer uploaderId;

    public List<String> getPathList() {
        return pathList;
    }
    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getGrpId() {
        return grpId;
    }

    public void setGrpId(int grpId) {
        this.grpId = grpId;
    }

    public String getHomeworkname() {
        return homeworkname;
    }

    public void setHomeworkname(String homeworkname) {
        this.homeworkname = homeworkname;
    }

    public boolean isRanked() {
        return ranked;
    }

    public void setRanked(boolean ranked) {
        this.ranked = ranked;
    }

    public void addPath(String path) {
        this.pathList.add(path);
    }

    public Integer getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Integer uploaderId) {
        this.uploaderId = uploaderId;
    }
}
