package entities;

import org.hibernate.HibernateException;
import java.util.List;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {

	private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object: " + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageEmployee ME = new ManageEmployee();

        /* Add employees */
        Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
        Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
        Integer empID3 = ME.addEmployee("John", "Paul", 10000);

        /* List employees */
        ME.listEmployees();

        /* Update employee salary */
        ME.updateEmployee(empID1, 5000);

        /* Delete an employee */
        ME.deleteEmployee(empID2);

        /* List updated employees */
        ME.listEmployees();

        factory.close();
    }

    /* CREATE */
    public Integer addEmployee(String firstName, String lastName, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(firstName, lastName, salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* READ */
    public void listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
            for (Employee emp : employees) {
                System.out.println("ID: " + emp.getId() + 
                                   ", First Name: " + emp.getFirstName() +
                                   ", Last Name: " + emp.getLastName() +
                                   ", Salary: " + emp.getSalary());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* UPDATE */
    public void updateEmployee(Integer employeeID, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeID);
            if (employee != null) {
                employee.setSalary(salary);
                session.update(employee);
                tx.commit();
            } else {
                System.out.println("Employee not found with ID: " + employeeID);
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* DELETE */
    public void deleteEmployee(Integer employeeID) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeID);
            if (employee != null) {
                session.delete(employee);
                tx.commit();
            } else {
                System.out.println("Employee not found with ID: " + employeeID);
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
