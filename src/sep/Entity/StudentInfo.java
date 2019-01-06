package sep.Entity;

import java.util.List;

public class StudentInfo {
    private Integer stuId;
    private String name;
    private String classid;
    private List<Double> hwScore;
    private Double grpContrib;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public List<Double> getHwScore() {
        return hwScore;
    }

    public void setHwScore(List<Double> hwScore) {
        this.hwScore = hwScore;
    }

    public Double getGrpContrib() {
        return grpContrib;
    }

    public void setGrpContrib(Double grpContrib) {
        this.grpContrib = grpContrib;
    }
}
