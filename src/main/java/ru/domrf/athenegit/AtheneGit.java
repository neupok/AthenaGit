/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.domrf.athenegit;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.domrf.athenegit.model.DDLEvent;

/**
 *
 * @author al.neupokoev
 */
public class AtheneGit {
    private static SessionFactory sessionFactory;
        
    public static void main(String[] args) {
        sessionFactory = new Configuration().setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver")
                                            .setProperty("hibernate.connection.url", "jdbc:oracle:thin:@rctst1.base.roscap.com:1521:RCTST")
                                            .setProperty("hibernate.connection.username", "hoarder")
                                            .setProperty("hibernate.connection.password", "atlant")
                                            .addAnnotatedClass(DDLEvent.class)
                                            .buildSessionFactory();
        
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from DDLEvent where id = :id");
        q.setParameter("id", new Long(1));
        List<DDLEvent> recs = q.list();
    }
}
