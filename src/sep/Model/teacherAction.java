package sep.Model;
import javafx.util.Pair;
// latest version
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import sep.Entity.Course;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

class ExcelInfo {
    private int index;
    private int id;

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExcelInfo{" +
            "index=" + index +
            ", id='" + id +
            '}';
    }
}

class ExcelDemo{

    /**
     * 导入excel文件，使用绝对路径
     *
     * @param file
     * @param sheetIndex
     * @return
     * @throws IOException
     */
    public static List<ExcelInfo> importExcel(String file, int sheetIndex) throws IOException {
        FileInputStream in = null;
        List<ExcelInfo> result = null;
        try {
            in = new FileInputStream(file);
            result = new ArrayList<ExcelInfo>();
            Workbook wb = new HSSFWorkbook(in);
            Sheet sheet = wb.getSheetAt(sheetIndex);
            for (Row row : sheet) {
                if (row.getRowNum() < 1) {
                    continue;
                }
                ExcelInfo eInfo = new ExcelInfo();
                eInfo.setIndex(row.getRowNum());
                eInfo.setId(Integer.parseInt(row.getCell(0).getStringCellValue()));
                //eInfo.setCommunity(row.getCell(1).getStringCellValue());
                result.add(eInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return result;
    }

    public static List<ExcelInfo> importExcel(String file) throws IOException {
        return importExcel(file, 0);
    }
}

public class teacherAction {

    private courseAction courseaction=new courseAction();

    public void setCourse(int name, int id, String desc, int teacherId){
        courseaction.setCourse(name, id, desc, teacherId);
    }

    public void setStuList(int courseId, String path){
        List<ExcelInfo> res=new ArrayList<ExcelInfo>();
        try{
            res=ExcelDemo.importExcel(path);
        }catch(Exception e){
            e.printStackTrace();
        }
        Iterator<ExcelInfo> it=res.iterator();
        while(it.hasNext()){
            ExcelInfo tmp=it.next();
            studentAction.getStuById(tmp.getId()).addCourse(courseId);
        }
    }

    public void setGrpCfg(int courseId, String prefix, int maxcrew, int mincrew){
        courseaction.setGrpCfg(courseId, prefix, maxcrew, mincrew);
    }

    public void addHomework(int courseId, String name, String content, Date deadline, double percentage){
        Course c=courseaction.getCourseById(courseId);
        c.addHome(name, content, deadline, percentage);
    }

//    public Pair<Date, String> getSubmit(int courseId, Integer grpId, String homework){
//        return new Pair<Date, String>(courseaction.getCourseById(courseId).getGrpById(grpId).getSubmitTimeByName(homework),
//                                    courseaction.getCourseById(courseId).getGrpById(grpId).getSubmitPathByName(homework));
//
//    }
    public void rankHomework(int courseId, Integer grpId, String homework, double grade){

        courseaction.getCourseById(courseId).getGrpById(grpId).addScore(homework, grade);
    }

    public void getStuList(){
        // TODO: DAO
    }
}
