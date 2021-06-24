package se.lexicon.erik.g36jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.erik.g36jpaworkshop.model.Book;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class BookDAORepository implements BookDAO{

    private final EntityManager em;

    @Autowired
    public BookDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book create(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Collection<Book> findAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    @Override
    public Book findById(Integer integer) {
        return em.find(Book.class, integer);
    }

    @Override
    public Book update(Book book) {
        return em.merge(book);
    }

    @Override
    public void delete(Integer integer) {
        Book book = findById(integer);
        if(book != null){
            em.remove(book);
        }
    }
}
