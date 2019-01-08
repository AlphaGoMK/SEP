package sep.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public class TeacherDAO {
    public static List<Teacher> getTeacherList() {
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

    public static boolean deleteTeacher(int teacherId) {
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

    public static Teacher addTeacher(String teacherName){
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

    public static boolean addCourse(Integer teacherId, Integer courseId) {
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

    public static Teacher getTeacherbyId(Integer teacherId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Teacher teacher = (Teacher)sess.get(Teacher.class, teacherId);
            sess.update(teacher);
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

    public static boolean checkExistence(Integer teacherId, String password) {
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

    public static boolean scoreHomework(Integer submitId, double score) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try{
            tx = sess.beginTransaction();
            MySubmit ms = (MySubmit)sess.get(MySubmit.class, submitId);
            GroupDAO.addScore(ms.getGrpId(), ms.getHomeworkname(), score);
            return false;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sess.close();
        }
    }

    public static List<MySubmit> searchSubmission(Integer courseId, String hwName, Integer grpId, Date startTime, Date endTime) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try{
            tx = sess.beginTransaction();
            String hql = "FROM MySubmit m WHERE m.courseId=" + courseId;

            boolean has_startTime = (startTime != null);
            boolean has_endTime = (endTime != null);
            if (!hwName.equals("") || grpId!=null || has_startTime || has_endTime) {
                if (!hwName.equals("")) {
                    hql += " AND";
                    hql += " m.homeworkname=:hwName";
                }
                if (grpId != null) {
                    hql += " AND";
                    hql += " m.grpId = " + grpId.toString();
                }
                if (has_startTime) {
                    hql += " AND";
                    hql += " m.date >= ?0";
                }
                if (has_endTime) {
                    has_endTime = true;
                    hql += " AND";
                    hql += " m.date <= ?1";
                }
            }
            Query q = sess.createQuery(hql);
            if (!hwName.equals("")) {
                q.setString("hwName", hwName);
            }
            if (has_startTime){
                q.setDate(0, startTime);
            }
            if (has_endTime) {
                q.setDate(1, endTime);
            }
            List<MySubmit> ms = q.list();
            return ms;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public static boolean changePassword(Integer teacherId, String passwd){
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try{
            tx = sess.beginTransaction();
            Teacher t = (Teacher)sess.get(Teacher.class, teacherId);
            t.setPassword(passwd);
            sess.update(t);
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
}

