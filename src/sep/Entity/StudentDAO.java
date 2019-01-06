package sep.Entity;


import javafx.util.Pair;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentDAO {

    public static List<Student> getStudentList() {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            List<Student> studentList = sess.createQuery("FROM Student ").list();
            tx.commit();
            return studentList;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public static boolean deleteStudent(int stuId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Student student = (Student)sess.get(Student.class, stuId);
            sess.delete(student);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sess.close();
        }
    }

    public static Student addStudent(String name, String classid){
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Student student = new Student(name, classid);
            Integer stuId = (Integer)sess.save(student); // Auto create id
            student.setStuId(stuId);
            tx.commit();
            return student;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public static Student addStudentAndId(Integer stuId, String name, String classid){
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Student student = new Student(stuId, name, classid);
            student.setStuId(stuId);
            tx.commit();
            return student;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public static boolean takeCourse(Integer stuId, Integer courseId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Student student = (Student)sess.get(Student.class, stuId);
            student.addCourse(courseId);
            sess.update(student);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sess.close();
        }
    }

    public static boolean checkExistence(Integer stuId, String password) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Student stu = getStudentbyId(stuId);
            if (stu == null) {
                return false;
            } else {
                if (stu.getPassword().equals(password))
                    return true;
            }
            return false;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sess.close();
        }
    }

    public static boolean joinGroup(Integer stuId, Integer courseId, Integer grpId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Student stu = (Student)sess.get(Student.class, stuId);
            stu.setGroupId(courseId, grpId);
            sess.update(stu);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sess.close();
        }
    }

    public static Student getStudentbyId(Integer stuId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Student stu = (Student)sess.get(Student.class, stuId);
            return stu;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public static List<String> getNameSuggest(String name, Integer courseId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            //TODO:
            String sql = String.format("SELECT DISTINCT s.stuName FROM student s WHERE s.stuName like \"%s%%\"" +
                     "AND s.stuId NOT IN (SELECT stuId FROM student_groupmap sg WHERE sg.courseId = %s)", name, courseId.toString());
            SQLQuery query = sess.createSQLQuery(sql);
            List<String> nameList = query.list();
            return nameList;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public static List<Student> getStudentByCourseId(Integer courseId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            String hql = "FROM Student s WHERE " + courseId.toString() + " in elements(s.courseset)";
            Query query = sess.createQuery(hql);
            List<Student> stuLists = query.list();
            tx.commit();
            return stuLists;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

}

