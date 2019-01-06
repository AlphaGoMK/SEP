package sep.Entity;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}

