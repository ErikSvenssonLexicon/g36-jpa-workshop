package se.lexicon.erik.g36jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.erik.g36jpaworkshop.model.BookLoan;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class BookLoanDAORepository implements BookLoanDAO{

    private final EntityManager em;

    @Autowired
    public BookLoanDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public BookLoan create(BookLoan bookLoan) {
        em.persist(bookLoan);
        return bookLoan;
    }

    @Override
    public Collection<BookLoan> findAll() {
        return em.createQuery("SELECT b FROM BookLoan b", BookLoan.class)
                .getResultList();
    }

    @Override
    public BookLoan findById(Integer integer) {
        return em.find(BookLoan.class, integer);
    }

    @Override
    public BookLoan update(BookLoan bookLoan) {
        return em.merge(bookLoan);
    }

    @Override
    public void delete(Integer integer) {
        BookLoan bookLoan = findById(integer);
        if(bookLoan != null){
            em.remove(bookLoan);
        }
    }
}
