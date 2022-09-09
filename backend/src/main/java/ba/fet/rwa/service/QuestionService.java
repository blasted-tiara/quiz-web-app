package ba.fet.rwa.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ba.fet.rwa.model.Question;
import ba.fet.rwa.model.Quiz;

public class QuestionService {
    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    .configure() // configures settings from hibernate.cfg.xml
    .build();
    private static SessionFactory factory = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    
    public static Question createNewQuestion(Long quizId, String text, Integer time, Integer points) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Quiz quiz = session.get(Quiz.class, quizId);
            Question question = new Question();
            question.setText(text);
            question.setTime(time);
            question.setPoints(points);
            question.setQuiz(quiz);
            
            // check how many questions exist for this quiz
            int questionCount = quiz.getQuestions().size();
            question.setOrder(questionCount + 1);
            
            session.persist(question);
            tx.commit();
            return question;
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
