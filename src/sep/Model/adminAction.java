package sep.Model;


import com.sun.org.apache.xerces.internal.xs.StringList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import sep.Entity.Student;
import sep.Entity.Teacher;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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
                eInfo.setAttr1(row.getCell(2).getStringCellValue());
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

    public void editUser(InitInfo i) throws Exception{
        if(getUserType(i.getId())==1){
            Teacher t;
            try{
                t=getTeacherById(i.getId());
                t.setName(i.getName());
                t.setPassword(i.getPassword());
                String attr=i.getAttr1();
                String[] ll=attr.split(",");
                Set<Integer> tset=new HashSet<Integer>();
                for(int idx=0;idx<ll.length;idx++){
                    tset.add(Integer.parseInt(ll[idx]));
                }
                t.setCourseset(tset);
            }catch(Exception e){

                System.out.println("Edit: Can not find teacher");
            }

        }
        else{
            Student s;
            try{
                s=getStudentById(i.getId());
                s.setName(i.getName());
                s.setPassword(i.getPassword());
                s.setClassid(i.getAttr1());
                String attr=i.getAttr2();
                String[] ll=attr.split(",");
                Set<Integer> sset=new HashSet<Integer>();
                for(int idx=0;idx<ll.length;idx++){
                    sset.add(Integer.parseInt(ll[idx]));
                }
                s.setCourseset(sset);
            }catch(Exception e){
                System.out.println("Edit: Can not find student");
            }
        }
    }

    public void removeUser(InitInfo i) throws Exception{
        if(getUserType(i.getId())==1){
            Teacher t;
            try{
                t=getTeacherById(i.getId());

                // TODO: DAO

            }catch(Exception e){
                System.out.println("Can not find teacher");
            }
        }
        else {
            Student s;
            try{
                s=getStudentById(i.getId());

                // TODO: DAO
            }catch(Exception e){
                System.out.println("Can not find student");
            }
        }
    }

}
