package lk.D24_Hostel.hostelSystem.db;

import lk.D24_Hostel.hostelSystem.entity.Reservation;
import lk.D24_Hostel.hostelSystem.entity.Room;
import lk.D24_Hostel.hostelSystem.entity.Student;
import lk.D24_Hostel.hostelSystem.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try {
                Configuration configuration = new Configuration();

                Properties dbSetting = new Properties();
                dbSetting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                dbSetting.put(Environment.URL, "jdbc:mysql://localhost:3306/d24hostel");
                dbSetting.put(Environment.USER, "root");
                dbSetting.put(Environment.PASS, "1234");
                dbSetting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                dbSetting.put(Environment.SHOW_SQL, "true");
                dbSetting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                dbSetting.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(dbSetting);
                configuration
                        .addAnnotatedClass(Student.class)
                        .addAnnotatedClass(Room.class)
                        .addAnnotatedClass(Reservation.class)
                        .addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
