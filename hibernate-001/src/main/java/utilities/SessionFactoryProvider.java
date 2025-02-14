package utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {

	 private static SessionFactory sessionFactory;

	    public static SessionFactory provideSessionFactory() {
	        if (sessionFactory == null) {
	            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	        }
	        return sessionFactory;
	    }
}
