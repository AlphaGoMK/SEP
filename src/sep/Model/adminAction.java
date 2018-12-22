package sep.Model;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import sep.Entity.Student;
import sep.Entity.Teacher;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class InitInfo{
    private int index;
    private int id;
    private String name;
    private String attr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    @Override
    public String toString(){
        return "id:"+id+" name:"+name+" attr:"+attr;
    }
}

class InitExcel{


    public static List<InitInfo> importExcel(String file, int sheetIndex) throws IOException {
        FileInputStream in = null;
        List<InitInfo> result = null;
        try {
            in = new FileInputStream(file);
            result = new ArrayList<InitInfo>();
            Workbook wb = new HSSFWorkbook(in);
            Sheet sheet = wb.getSheetAt(sheetIndex);
            for (Row row : sheet) {
                if (row.getRowNum() < 1) {
                    continue;
                }
                InitInfo eInfo = new InitInfo();
                eInfo.setIndex(row.getRowNum());
                eInfo.setId((int)row.getCell(0).getNumericCellValue());
                eInfo.setName(row.getCell(1).getStringCellValue());
                eInfo.setAttr(row.getCell(2).getStringCellValue());
                System.out.println(eInfo);
                result.add(eInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return result;
    }

    public static List<InitInfo> importExcel(String file) throws IOException {
        return importExcel(file, 0);
    }
}

public class adminAction {

    InitExcel op=new InitExcel();

    public void importTeacher(String excelfile) throws IOException{
        List<InitInfo> res=new ArrayList<InitInfo>();
        try{
            res=op.importExcel(excelfile);
        } catch(Exception e){
            e.printStackTrace();
        }

        if(res!=null){
            //TODO: teacher DAO
        }
    }

    public void importStudent(String excelfile) throws IOException{
        List<InitInfo> res=new ArrayList<InitInfo>();
        try{
            res=op.importExcel(excelfile);
        } catch(Exception e){
            e.printStackTrace();
        }

        if(res!=null){
            //TODO: stu DAO
        }
    }

    public int getUserType(int uid){

        // TODO: 1 for taecher, 2 for student

        String s=Integer.toString(uid);
        if(s.charAt(0)=='1'){
            return 1;
        }
        else return 2;
    }

    public Teacher getTeacherById(int id){
        // TODO: DAO
        return null;
    }

    public Student getStudentById(int id){
        // TODO: DAO
        return null;
    }

}
