package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StudentDetails {

	public static void main(String[] args) {
        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class);
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();
        
        Student stu = new Student(103,"Nehal");
        

        session.save(stu);
        
        tx.commit();
        session.close();
        factory.close();

        System.out.println("Record Saved Successfully");
    }
}
