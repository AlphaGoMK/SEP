package sep.Entity;

import org.hibernate.*;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class CourseDAO {

    public List<Course> getCourseList(){
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            List<Course> courseList = sess.createQuery("FROM Course ").list();
            tx.commit();
            return courseList;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public boolean deleteCourse(Integer courseId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Course course = (Course)sess.get(Course.class, courseId);
            sess.delete(course);
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

    public Course addCourse(String name, String description, Integer teacherid, Integer maxcrew, Integer mincrew){
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Course course = new Course(name, description, teacherid, maxcrew, mincrew);
            Integer courseId = (Integer)sess.save(course); // Auto create id
            course.setCourseId(courseId);
            tx.commit();
            return course;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    // Add Homework
    public boolean addHomework(Integer courseId, String hwName, String content, Date deadline, double percentage) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Course course = (Course)sess.get(Course.class, courseId);
            course.addHome(hwName, content, deadline, percentage);
            sess.update(course);
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

    public List<Course> findByName(String courseName) {
        String hql = "FROM Course C WHERE C.name = " + courseName;
        Session sess = HibernateInit.getSession();
        TypedQuery<Course> query = sess.createQuery(hql);
        List<Course> res = query.getResultList();
        sess.close();
        return res;
    }

}

