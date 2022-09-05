package ba.fet.rwa.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ba.fet.rwa.model.Quiz;

public class QuizService {
    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    .configure() // configures settings from hibernate.cfg.xml
    .build();
    private static SessionFactory factory = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public static List<Quiz> getAllQuizzes() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            return session.createQuery("SELECT * FROM Quiz", Quiz.class).getResultList();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); 
        } finally {
            session.close();
        }
        return null;
    }
    
    public static void createQuiz(Quiz quiz) {
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx =  session.beginTransaction();
            session.persist(quiz);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close(); 
        }

    }
    
    public static void updateQuiz(Quiz quiz) {

    }
    
    public static void deleteQuiz(Quiz quiz) {

    }
}
