package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class StudentDetailsWithoutXML {

	public static void main(String[] args) {
        // Hibernate configuration without XML
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/demo2?createDatabaseIfNotExist=true");
        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.connection.password", "12345");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "update");
        config.setProperty("hibernate.show_sql", "true");
        
        config.addAnnotatedClass(Student.class);
        
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        SessionFactory factory = config.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();
        
        Transaction tx = session.beginTransaction();
        
        Student stu = new Student(11,"ben");
        
        session.save(stu);
        
        tx.commit();
        session.close();
        factory.close();

        System.out.println("Record Saved Successfully");
    }
}
