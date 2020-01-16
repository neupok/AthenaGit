/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.domrf.athenegit;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
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
    public static final String DB_URL_PARAM = "hibernate.connection.url";
    public static final String DB_USER_NAME_PARAM = "hibernate.connection.username";
    public static final String DB_USER_PWD_PARAM = "hibernate.connection.password";
    
    private static SessionFactory sessionFactory;
    
    private static String dbUrl;
    private static String dbUserName;
    private static String dbPassword;
            
    static void readConfig() throws ConfigurationException
    {
        Configurations configs = new Configurations();
        PropertiesConfiguration config = configs.properties(new File("athenegit.properties"));
        
        dbUrl = config.getString(DB_URL_PARAM);
        dbUserName = config.getString(DB_USER_NAME_PARAM);
        dbPassword = config.getString(DB_USER_PWD_PARAM);        
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
            
            
            Session session = sessionFactory.openSession();
            Query q = session.createQuery("from DDLEvent where id = :id");
            q.setParameter("id", new Long(1));
            List<DDLEvent> recs = q.list();
        } catch (ConfigurationException ex) {
            Logger.getLogger(AtheneGit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
