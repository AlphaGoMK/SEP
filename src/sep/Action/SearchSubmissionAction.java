package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class SearchSubmissionAction extends ActionSupport {
    private Integer courseId;
    private List<MySubmit> submitList;
    private List<String> uploaderName;
    private List<List<String>> uploadedFiles;
    private List<Double> scores;
    private String hwName;
    private String startTime;
    private String endTime;
    private Integer grpId;

    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public List<MySubmit> getSubmitList() {
        return submitList;
    }
    public void setSubmitList(List<MySubmit> submitList) {
        this.submitList = submitList;
    }

    public List<String> getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(List<String> uploaderName) {
        this.uploaderName = uploaderName;
    }

    public List<List<String>> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<List<String>> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public List<Double> getScores() {
        return scores;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
    }

    public String getHwName() {
        return hwName;
    }

    public void setHwName(String hwName) {
        this.hwName = hwName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    @Override
    public void validate() {
        super.validate();
        try {
            if (!startTime.equals("") && !endTime.equals("")) {
                SimpleDateFormat ft = new SimpleDateFormat("mm/dd/yyyy");
                Date startDate = ft.parse(startTime);
                Date endDate = ft.parse(endTime);
                if (endDate.compareTo(startDate) == -1.0) {
                    addFieldError("fieldError", "结束日期需晚于开始日期");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String execute() throws Exception{
        System.out.println("ShowSearchedSubmissionAction called");
        System.out.println("hwName: " + hwName);
        System.out.println("Start time: " + startTime.toString());
        System.out.println("End time: " + endTime.toString());
        System.out.println("grpId: " + grpId);
        SimpleDateFormat ft = new SimpleDateFormat("mm/dd/yyyy");
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();

        courseId = (Integer)session.get("COURSE_ID");
        Date startDate = null;
        Date endDate = null;

        try {
            if (!startTime.equals("")) {
                startDate = ft.parse(startTime);
            }
            if (!startTime.equals("")) {
                endDate = ft.parse(startTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        submitList = TeacherDAO.searchSubmission(courseId, hwName, grpId, startDate, endDate);
        submitList.sort(new MySubmitDateComparator());
        uploadedFiles = new ArrayList<List<String>>();
        uploaderName = new ArrayList<>();
        scores = new ArrayList<>();

        for (Iterator it = submitList.iterator(); it.hasNext();) {
            MySubmit m =((MySubmit)it.next());
            Integer uploaderId = m.getUploaderId();
            System.out.println(uploaderId);
            String stuName = StudentDAO.getStudentbyId(uploaderId).getName();
            Group g = GroupDAO.getGroupById(m.getGrpId());
            if (g.getScore().containsKey(m.getHomeworkname())) {
                scores.add(g.getScore().get(m.getHomeworkname()));
            } else {
                scores.add(-1.0);
            }
            uploaderName.add(stuName);
            List<String> rawList = m.getPathList();
            if (rawList.size() > 0) {
                List<String> trimed = new ArrayList<>();
                for (int j = 0; j < rawList.size(); j++) {
                    String[] splitted = rawList.get(j).split("\\/");
                    trimed.add(splitted[splitted.length - 1]);
                }
                uploadedFiles.add(trimed);
            } else {
                uploadedFiles.add(new ArrayList<>());
            }
        }
        return "success";
    }

}


