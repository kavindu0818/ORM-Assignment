package org.example;

import jakarta.persistence.Query;
import org.example.Configaration.FactoryConfigaration;
import org.example.Entity.Author;
import org.example.Entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GetValues {
    public List<Book> bookPublishAfter2010(){
        Session session = FactoryConfigaration.getInstance().getSession();
        String hql =  "FROM Book b WHERE b.publicationYear > 2010";
        Query query = session.createQuery(hql);
        return query.getResultList();

    }

    public void updatePrice(Long authorID, double priceIncrement){
            Session session = FactoryConfigaration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE Book SET price = price + :priceIncrement WHERE author.id = :authorID";
            Query query = session.createQuery(hql);
            query.setParameter("priceIncrement", priceIncrement);
            query.setParameter("authorId", authorID);
            query.executeUpdate();
            transaction.commit();

        }

        public double bookAvarage(){
        Session session = FactoryConfigaration.getInstance().getSession();
        String hql = "SELECT AVG(b.price) FROM Book b";
        Query query = session.createQuery(hql);
        return (Double) query.getSingleResult();
        }

    public List<Object[]> getAuthorsBookCount() {
        Session session = FactoryConfigaration.getInstance().getSession();
            String hql = "SELECT a, COUNT(b) FROM Author a JOIN a.bookList b GROUP BY a";
            Query query = session.createQuery(hql);
            return query.getResultList();
        }

    public List<Book> getBookAuthoursCountry(){
        Session session =FactoryConfigaration.getInstance().getSession();
        String hql ="SELECT b FROM Book b JOIN b.author a WHERE a.country = :authorCountry";
        Query query =session.createQuery(hql);
         query.setParameter("authorCountry", "Srilanka");
        return query.getResultList();
    }

    public List<Author> getAuthorsAboveAverageBookCount() {
            Session session = FactoryConfigaration.getInstance().getSession();
            String hql = "SELECT a FROM Author a WHERE (SELECT COUNT(b) FROM a.bookList b) > " +
                    "(SELECT AVG(COUNT(b)) FROM Author a JOIN a.bookList b GROUP BY a)";
            Query query = session.createQuery(hql);
            return query.getResultList();
        }
    }


