package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import sep.Entity.*;

import java.lang.management.MemoryUsage;
import java.util.*;


public class ListSubmitAction extends ActionSupport {
    private Integer courseId;
    private List<MySubmit> submitList;
    private List<String> uploaderName;
    private List<List<String>> uploadedFiles;
    private List<Double> scores;

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

    public String execute() {
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();

        if (session.containsKey("COURSE_ID")) {
            courseId = (Integer)session.get("COURSE_ID");
            Course c = CourseDAO.getCoursebyId(courseId);
            List<Homework> hwList = c.getHomework();
            submitList = new ArrayList<>();

            //获取全部提交
            Set<Group> gSet = c.getGrp();
            Map<Integer, Group> idToGroup = new HashMap<>();
            for (Iterator it = gSet.iterator(); ((Iterator) it).hasNext();) {
                Group g = (Group) it.next();
                Map<String, MySubmit> hwToSubmit = g.getSubmit();
                for (Iterator hwIt = hwList.iterator(); hwIt.hasNext();) {
                    String hwName = ((Homework)hwIt.next()).getName();
                    if (hwToSubmit.containsKey(hwName)) {
                        submitList.add(hwToSubmit.get(hwName));
                    }
                }
                idToGroup.put(g.getId(), g);
            }
            submitList.sort(new MySubmitDateComparator());
            uploadedFiles = new ArrayList<List<String>>();
            uploaderName = new ArrayList<>();
            scores = new ArrayList<>();
            for (Iterator it=submitList.iterator();it.hasNext();) {
                MySubmit m =((MySubmit)it.next());
                Integer uploaderId = m.getUploaderId();
                System.out.println(uploaderId);
                String stuName = StudentDAO.getStudentbyId(uploaderId).getName();
                Group g = idToGroup.get(m.getGrpId());
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
        } else {
            return "error";
        }

    }
}

class MySubmitDateComparator implements Comparator<MySubmit> {

    @Override
    public int compare(MySubmit m1, MySubmit m2) {
        return  - (m1.getDate().compareTo(m2.getDate()));
    }

}




