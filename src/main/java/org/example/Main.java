package org.example;


import org.example.Configaration.FactoryConfigaration;
import org.example.Entity.Author;
import org.example.Entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        GetValues getValues = new GetValues();


        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Author author = new Author();
        author.setName("Kavindu");

        Author author1 = new Author();
        author1.setName("Madhuranga");

        List<Book> bookList1 = new ArrayList<>();

        Book book = new Book();
        book.setTitle("sample1");
        book.setPublicationYear(2024);
        book.setPrice(500.00);
        book.setAuthor(author);
        bookList1.add(book);

        Book book1 = new Book();
        book1.setTitle("sample3");
        book1.setPublicationYear(2005);
        book1.setPrice(700.00);
        book1.setAuthor(author);
        bookList1.add(book1);

        author.setBookList(new ArrayList<>(bookList1));

        List<Book> bookList2 = new ArrayList<>();

        Book book2 = new Book();
        book2.setTitle("sample2");
        book2.setPublicationYear(2010);
        book2.setPrice(1000.00);
        book2.setAuthor(author1);
        bookList2.add(book2);

        Book book3 = new Book();
        book3.setTitle("sample3");
        book3.setPublicationYear(2009);
        book3.setPrice(600.00);
        book3.setAuthor(author1);
        bookList2.add(book3);

        author1.setBookList(new ArrayList<>(bookList2));

        session.save(author);
        session.save(author1);

        for (Book b : bookList1) {
            session.save(b);
        }

        for (Book b : bookList2) {
            session.save(b);
        }

        transaction.commit();
        session.close();


//
           // ==========  01 =================================
           System.out.println(getValues.bookPublishAfter2010());
           List<Book> authorsBook = getValues.bookPublishAfter2010();

        for (Book books : authorsBook) {
            Long id = books.getId();
            String title = books.getTitle();

            System.out.println("Books published after 2010: " + "Book ID; " + id + "Book Title;" + title);
        }




         //=====================   02 =================================
            getValues.updatePrice(8l,0.10);



           // ====================03=================================================
           deleteAuthorAndBooks(author.getId());





         // ==================  04====================
            System.out.println("Avarage" + getValues.bookAvarage());





            //===========   05  ============================
           List<Object[]> authorsBookCountList = getValues.getAuthorsBookCount();

            for (Object[] result : authorsBookCountList) {
            Author authors = (Author) result[0];
            Long bookCount = (Long) result[1];

             System.out.println("Author: " + authors.getName() + ", Book Count: " + bookCount);
          }




        // ======================= 06===========================

        List<Book> authorsBookContry = getValues.getBookAuthoursCountry();

        for (Book books : authorsBook){
            Author authors = books.getAuthor();
            String name = author.getName();
            Long bookId = book.getId();

            System.out.println("Authour Name;" + authors + " Book ID: " + bookId);
        }

        //=====================10==========================

        List<Author> authorList = getValues.getAuthorsAboveAverageBookCount();
        for (Author authors : authorList){
            Long id = author.getId();
            String name = author.getName();

            System.out.println("Author ID; " + id + "Author Name; " + name);
        }

    }


    private static void deleteAuthorAndBooks(long authorID) {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Author author=session.get(Author.class,authorID);

        if(author!=null){
            session.delete(author);
        }

        transaction.commit();
        session.close();
    }




}