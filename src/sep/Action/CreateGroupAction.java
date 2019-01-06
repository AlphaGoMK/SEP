package sep.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sep.Entity.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

public class CreateGroupAction extends ActionSupport {

    private int courseId;
    private CourseInfo courseInfo;
    private String has_group;

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }
    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    private Course c;
    private Student s;
    private Group g;

    private List<String> hwState;
    private List<Double> hwScore;

    private List<String> studentNameInput;
    private List<String> studentIdInput;
    private List<String> validStuName;

    public String getHas_group() {
        return has_group;
    }
    public void setHas_group(String has_group) {
        this.has_group = has_group;
    }

    private List<Integer> validStuId;
    private String contact;
    private Integer leaderId;

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getLeaderId() {
        return leaderId;
    }
    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public List<String> getStudentNameInput() {
        return studentNameInput;
    }
    public void setStudentNameInput(List<String> studentNameInput) {
        this.studentNameInput = studentNameInput;
    }

    public List<String> getStudentIdInput() {
        return studentIdInput;
    }
    public void setStudentIdInput(List<String> studentIdInput) {
        this.studentIdInput = studentIdInput;
    }

    public Course getC() {
        return c;
    }
    public void setC(Course c) {
        this.c = c;
    }

    public Student getS() {
        return s;
    }
    public void setS(Student s) {
        this.s = s;
    }

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<String> getValidStuName() {
        return validStuName;
    }
    public void setValidStuName(List<String> validStuName) {
        this.validStuName = validStuName;
    }

    public List<Integer> getValidStuId() {
        return validStuId;
    }
    public void setValidStuId(List<Integer> validStuId) {
        this.validStuId = validStuId;
    }

    public List<String> getHwState() {
        return hwState;
    }
    public void setHwState(List<String> hwState) {
        this.hwState = hwState;
    }

    public List<Double> getHwScore() {
        return hwScore;
    }
    public void setHwScore(List<Double> hwScore) {
        this.hwScore = hwScore;
    }

    public Group getG() {
        return g;
    }

    public void setG(Group g) {
        this.g = g;
    }

    public void validate() {
        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        s = StudentDAO.getStudentbyId((Integer)session.get("USER_ID"));
        session.put("CREATE_GROUP_STATE", "error");
        if(session.containsKey("COURSE_ID")){
            this.courseId = (Integer)session.get("COURSE_ID");
        }
        c = CourseDAO.getCoursebyId(courseId);

        this.courseInfo = new CourseInfo(CourseDAO.getCoursebyId(courseId));

        int cnt = 1;
        List<Integer> validIdx = new ArrayList<>();
        validStuId = new ArrayList<>();
        validStuName = new ArrayList<>();
        String idPattern = "\\d*";
        has_group = "false";
        // 字段缺失检查
        for (int i = 0; i < studentNameInput.size(); i ++) {
            if (studentNameInput.get(i).equals("") && !studentIdInput.get(i).equals("")) {
                addFieldError("errorFiled","请输入" + studentIdInput.get(i) + "的姓名");
                has_group = "false";
            }
            if (!studentNameInput.get(i).equals("") && studentIdInput.get(i).equals("")) {
                addFieldError("errorFiled","请填写" + studentNameInput.get(i) + "的学号");
                has_group = "false";
            }
            if (!studentNameInput.get(i).equals("") && !studentIdInput.get(i).equals("")) {
                if (Pattern.matches(idPattern, studentIdInput.get(i))) {
                    validStuId.add(Integer.parseInt(studentIdInput.get(i)));
                    validStuName.add(studentNameInput.get(i));
                    validIdx.add(i);
                } else {
                    addFieldError("errorFiled","学号只能由数字构成");
                    has_group = "false";
                }
                cnt ++;
            }
        }
        // 组员人数
        if (cnt < c.getMincrew()) {
            addFieldError("errorFiled","组员人数至少为" + c.getMincrew());
            has_group = "false";
        }
        // 数据库相关
        for (int i = 0; i < validStuId.size(); i ++) {
            Integer stuId = validStuId.get(i);
            Student s = StudentDAO.getStudentbyId(stuId);
            if (s == null) {
                addFieldError("errorFiled","学号" + stuId.toString() + "不存在");
                has_group = "false";
            }else{
                if (!s.getName().equals(validStuName.get(i))) {
                    System.out.println(stuId);
                    System.out.println(s.getName());
                    System.out.println(validStuName.get(i));
                    System.out.println(s.getName().equals(validStuName.get(i)));
                    addFieldError("errorFiled","学号" + validStuId.get(i) + "的组员姓名不符");
                    has_group = "false";
                }
                if (s.getGroupId(courseId) != null){
                    addFieldError("errorFiled","学号" + validStuId.get(i) + "的组员已加入本课程其他小组");
                    has_group = "false";
                }
            }
        }
        // 学号判重
        Set<Integer> stuIdset = new HashSet<>(validStuId);
        if (stuIdset.size() != validStuId.size()) {
            addFieldError("errorFiled","请勿填写重复信息");
            has_group = "false";
        }
        // 通讯方式
        if (contact.equals("")) {
            addFieldError("errorFiled","请填写小组联系方式");
            has_group = "false";
        }
    }

    public String execute() throws Exception{
        System.out.println("CreateGroupAction called.");
        System.out.println("Contact:" + contact);
        System.out.println("LeaderId:" + leaderId);


        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        session.put("CREATE_GROUP_STATE", "success");
        s = StudentDAO.getStudentbyId((Integer)session.get("USER_ID"));

        if(session.containsKey("COURSE_ID")){
            this.courseId = (Integer)session.get("COURSE_ID");
        }
        c = CourseDAO.getCoursebyId(courseId);

        this.courseInfo = new CourseInfo(CourseDAO.getCoursebyId(courseId));

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码

        g = GroupDAO.addGroup("", c.getCourseId(), leaderId, contact);
        for (int i = 0; i < validStuId.size(); i ++) {
            Integer curStuId = validStuId.get(i);
            GroupDAO.addStudent(g.getId(), validStuId.get(i));
        }

        hwState = new ArrayList<>();
        hwScore = new ArrayList<>();

        for (int i = 0; i < c.getHomework().size(); i++) {
            hwState.add("false");
            hwScore.add(-1.0);
        }
        session.put("GROUP_ID", g.getId());

        has_group = "true";
        return "success";
    }

}