package sep.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TeacherDAO {
    public List<Teacher> getTeacherList() {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            List<Teacher> teacherList = sess.createQuery("FROM Teacher ").list();
            tx.commit();
            return teacherList;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public boolean deleteTeacher(int teacherId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Teacher teacher = (Teacher)sess.get(Teacher.class, teacherId);
            sess.delete(teacher);
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

    public Teacher addTeacher(String teacherName){
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Teacher teacher = new Teacher(teacherName);
            Integer teacherId = (Integer)sess.save(teacher); // Auto create id
            teacher.setTeacherId(teacherId);
            tx.commit();
            return teacher;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public boolean addCourse(Integer teacherId, Integer courseId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Teacher teacher = (Teacher)sess.get(Teacher.class, teacherId);
            teacher.addCourse(courseId);
            sess.update(teacher);
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

    public boolean checkExistence(Integer teacherId, String password) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Teacher teacher = (Teacher)sess.get(Teacher.class, teacherId);
            if (teacher == null) {
                return false;
            } else {
                System.out.println(teacher.getPassword());
                if (teacher.getPassword().equals(password)) {
                    return true;
                }
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
}

