package ba.fet.rwa.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

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
            Query<Quiz> query = session.createQuery("SELECT q FROM Quiz q", Quiz.class);
            return query.getResultList();
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
    
    public static Quiz createNewQuiz(String title) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            session.persist(quiz);
            tx.commit();
            return quiz;
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
    
    public static void deleteQuiz(Long id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Quiz quiz = session.get(Quiz.class, id);
            session.remove(quiz);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace(); 
        } finally {
            session.close();
        }
    } 
    
    public static Quiz updateQuiz(Long id, String title) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Quiz quiz = session.get(Quiz.class, id);
            quiz.setTitle(title);
            session.merge(quiz);
            tx.commit();
            return quiz;
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
}
