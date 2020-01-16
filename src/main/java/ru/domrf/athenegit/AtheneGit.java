/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.domrf.athenegit;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.domrf.athenegit.model.DDLEvent;

/**
 *
 * @author al.neupokoev
 */
public class AtheneGit {
    public static final String DB_URL_PARAM = "hibernate.connection.url";
    public static final String DB_USER_NAME_PARAM = "hibernate.connection.username";
    public static final String DB_USER_PWD_PARAM = "hibernate.connection.password";
    
    private static SessionFactory sessionFactory;
    
    private static String dbUrl;
    private static String dbUserName;
    private static String dbPassword;
            
    static void readConfig() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("athenegit.properties"));
        dbUrl = properties.getProperty(DB_URL_PARAM);
        dbUserName = properties.getProperty(DB_USER_NAME_PARAM);
        dbPassword = properties.getProperty(DB_USER_PWD_PARAM);        
    }
    
    private static void createSessionFactory() {
        sessionFactory = new Configuration().setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver")
                    .setProperty(DB_URL_PARAM, dbUrl)
                    .setProperty(DB_USER_NAME_PARAM, dbUserName)
                    .setProperty(DB_USER_PWD_PARAM, dbPassword)
                    .addAnnotatedClass(DDLEvent.class)
                    .buildSessionFactory();
    }
    
    public static void main(String[] args) {
        try {
            // Чтение конфигурации
            readConfig();
            // Создание фабрики соединений
            createSessionFactory();

            //

            Session session = sessionFactory.openSession();
            Query<DDLEvent> q = session.createQuery("from DDLEvent where id = :id", DDLEvent.class);
            q.setParameter("id", 1L);
            List<DDLEvent> recs = q.list();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
