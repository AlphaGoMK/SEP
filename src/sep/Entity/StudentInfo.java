package sep.Entity;

import java.util.List;

public class StudentInfo {
    private Integer stuId;
    private String name;
    private String classid;
    private List<Double> hwScore;
    private Double grpContrib;
    private Double allscore;
    private List<Double> hwPercentage;
    private String result;

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

    public Double getAllscore() {
        return allscore;
    }

    public void setAllscore(Double allscore) {
        this.allscore = allscore;
    }

    public Double calcAllscore(){
        if (this.grpContrib < 0.0) {
            allscore=0.0;
        }
        else{
            boolean flag=true;
            for(int i=0;i<hwScore.size();i++){
                if(hwScore.get(i)<0.0){
                    allscore=0.0;
                    flag=false;
                    break;
                }
            }
            if(flag==true){
                allscore=0.0;
                Double sumper=0.0;
                for(int i=0;i<hwPercentage.size();i++){
                    sumper+=hwPercentage.get(i);
                }

                for(int i=0;i<hwScore.size();i++){
                    allscore+=(hwScore.get(i)*hwPercentage.get(i)/sumper);
                }
                allscore=(allscore*grpContrib)/100.0;
            }
        }

        result = String.format("%.2f", allscore);
        return allscore;
    }

    public List<Double> getHwPercentage() {
        return hwPercentage;
    }

    public void setHwPercentage(List<Double> hwPercentage) {
        this.hwPercentage = hwPercentage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
