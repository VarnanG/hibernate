package app;

import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utilities.SessionFactoryProvider;

public class Create {

	 public static void main(String[] args) {
	        // Get SessionFactory instance
	        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
	        
	        // Open session and handle transactions
	        try (Session session = sessionFactory.openSession()) {
	            Transaction t = session.beginTransaction();

	            Employee emp = new Employee(101, "John Doe");
	            session.save(emp);

	            t.commit();
	            System.out.println("Employee saved successfully!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            sessionFactory.close();
	        }
	    }
}
