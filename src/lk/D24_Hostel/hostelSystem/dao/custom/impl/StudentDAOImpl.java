package lk.D24_Hostel.hostelSystem.dao.custom.impl;

import lk.D24_Hostel.hostelSystem.dao.custom.StudentDAO;
import lk.D24_Hostel.hostelSystem.db.HibernateUtil;
import lk.D24_Hostel.hostelSystem.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student entity) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.load(Student.class, s);
        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student find(String s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String find = "FROM Student WHERE student_id = :id";
        Query query = session.createQuery(find);
        query.setParameter("id", s);
        List<Student> students = query.list();
        for (Student st : students) {
            return new Student(st.getStudent_id(),st.getName(),st.getAddress(),st.getContact_no(),st.getDob(),st.getGender());
        }
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public List<Student> findAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String getAll = "FROM Student";
        Query query = session.createQuery(getAll);
        List<Student> students = query.list();

        transaction.commit();
        session.close();
        return students;
    }

    @Override
    public String generateNewId() throws Exception {
        return null;
    }
}
