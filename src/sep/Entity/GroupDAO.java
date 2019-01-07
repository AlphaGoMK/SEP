package sep.Entity;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
public class GroupDAO {

    public static Group addGroup(String groupId, int courseID, int leaderId, String contact) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            groupId = groupId + CourseDAO.getCoursebyId(courseID).getGrpPrefix();
            Group g = new Group(groupId, courseID, leaderId, contact);
            Integer grpId = (Integer)sess.save(g); // Auto create id
            StudentDAO.joinGroup(leaderId, g.getCourseID(), g.getId());
            CourseDAO.addGroup(courseID, g.getId());
            g.setId(grpId);
            tx.commit();
            return g;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public static boolean deleteGroup(int grpId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Group group = (Group)sess.get(Group.class, grpId);
            sess.delete(group);
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

    public static List<Group> getGroupListByCourseId(Integer courseId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            String q = "SELECT g FROM Group g WHERE g.courseID=" + courseId.toString();
            Query query = sess.createQuery(q);
            List<Group> grpList = query.list();
            tx.commit();
            return grpList;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public static Group getGroupById(Integer grpId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            System.out.println("GroupId: " + grpId.toString());
            Group grp = (Group)sess.get(Group.class, grpId);
            tx.commit();
            return grp;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

    public static boolean addStudent(Integer grpId, Integer studentId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Group grp = (Group)sess.get(Group.class, grpId);
            grp.addStu(studentId);
            StudentDAO.joinGroup(studentId, grp.getCourseID(), grp.getId());
            sess.update(grp);
            tx.commit();
            //TODO: no check up for maximum group member.
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sess.close();
        }
    }

    public static boolean changeLeader(Integer grpId, Integer studentId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Group grp = (Group)sess.get(Group.class, grpId);
            grp.setLeaderId(studentId);
            sess.update(grp);
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

    public static  boolean submitHome(Integer grpId, String hwName, String hwFilePath) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            Group grp = (Group)sess.get(Group.class, grpId);
            grp.addSubmit(hwName, hwFilePath);
            sess.update(grp);
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

    public static MySubmit getSubmitById(Integer subId) {
        Session sess = HibernateInit.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            MySubmit ms = (MySubmit) sess.get(MySubmit.class, subId);
            tx.commit();
            return ms;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            sess.close();
        }
    }

}
