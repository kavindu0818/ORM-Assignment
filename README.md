1).  Session session = FactoryConfigaration.getInstance().getSession();
String hql =  "FROM Book b WHERE b.publicationYear > 2010";
Query query = session.createQuery(hql);
return query.getResultList();

2). Session session = FactoryConfigaration.getInstance().getSession();
Transaction transaction = session.beginTransaction();
String hql = "UPDATE Book SET price = price + :priceIncrement WHERE author.id = :authorID";
Query query = session.createQuery(hql);
query.setParameter("priceIncrement", priceIncrement);
query.setParameter("authorId", authorID);
query.executeUpdate();
transaction.commit();

4). Session session = FactoryConfigaration.getInstance().getSession();
String hql = "SELECT AVG(b.price) FROM Book b";
Query query = session.createQuery(hql);
return (Double) query.getSingleResult();

5). Session session = FactoryConfigaration.getInstance().getSession();
String hql = "SELECT a, COUNT(b) FROM Author a JOIN a.bookList b GROUP BY a";
Query query = session.createQuery(hql);
return query.getResultList();

6).  Session session =FactoryConfigaration.getInstance().getSession();
String hql ="SELECT b FROM Book b JOIN b.author a WHERE a.country = :authorCountry";
Query query =session.createQuery(hql);
query.setParameter("authorCountry", "Srilanka");
return query.getResultList();

7).7)In a bidirectional one-to-many relationship between Author and Book entities using @JoinColumn:

In the Author entity, use @OneToMany(mappedBy = "author") to establish the relationship, indicating that the mapping is owned by the author field in the Book entity.

In the Book entity, use @ManyToOne to represent the many-to-one side of the relationship, and @JoinColumn(name = "author_id") to specify the foreign key column (author_id) that links to the Author entity's primary key (id).


8).  Session session = FactoryConfigaration.getInstance().getSession();
String hql = "SELECT a FROM Author a WHERE (SELECT COUNT(b) FROM a.bookList b) > " +
"(SELECT AVG(COUNT(b)) FROM Author a JOIN a.bookList b GROUP BY a)";
Query query = session.createQuery(hql);
return query.getResultList();







