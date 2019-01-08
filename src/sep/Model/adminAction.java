package sep.Model;


import com.sun.org.apache.xerces.internal.xs.StringList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import sep.Entity.*;

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
                if(row.getPhysicalNumberOfCells()>1){
                    eInfo.setName(row.getCell(1).getStringCellValue());
                }
                if(row.getPhysicalNumberOfCells()>2){
                    eInfo.setAttr1(row.getCell(2).getStringCellValue());
                }

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

    public boolean importStuList(int courseId, String excelfile) throws IOException{
        List<InitInfo> res=new ArrayList<InitInfo>();
        try{
            res=op.importExcel(excelfile);

            System.out.println("Excel Row Number "+Integer.toString(res.size()));

            for(int i=0;i<res.size();i++){
                StudentDAO.takeCourse(res.get(i).getId(), courseId);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

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
        // 1 for teacher; 2 for student; 0 for neither
        if(TeacherDAO.getTeacherbyId(uid)!=null){
            return 1;
        }
        else if(StudentDAO.getStudentbyId(uid)!=null){
            return 2;
        }
        else{
            return 0;
        }
    }

    public Teacher getTeacherById(int id){

        return TeacherDAO.getTeacherbyId(id);
    }

    public Student getStudentById(int id){
        return StudentDAO.getStudentbyId(id);
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
                if(t!=null){
                    TeacherDAO.deleteTeacher(t.getTeacherId());
                }
                else{
                    System.out.println("Can not find teacher");
                }



            }catch(Exception e){
                System.out.println("Delete error");
            }
        }
        else {
            Student s;
            try{
                s=getStudentById(i.getId());
                if(s!=null){
                    StudentDAO.deleteStudent(s.getStuId());
                }
                else{
                    System.out.println("Can not find student");
                }
            }catch(Exception e){
                System.out.println("Delete error");
            }
        }
    }

    public String addUser(boolean isT, InitInfo i){
        if(isT){
            TeacherDAO.addTeacher(i.getName());
        }
        else{
            StudentDAO.addStudentAndId(i.getId(), i.getName(), i.getAttr1());
        }
        return "success";
    }
}
