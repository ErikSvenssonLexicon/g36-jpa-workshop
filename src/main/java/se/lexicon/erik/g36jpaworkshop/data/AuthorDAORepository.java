package se.lexicon.erik.g36jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.erik.g36jpaworkshop.model.Author;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class AuthorDAORepository implements AuthorDAO{

    private final EntityManager em;

    @Autowired
    public AuthorDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Author create(Author author) {
        em.persist(author);
        return author;
    }

    @Override
    public Collection<Author> findAll() {
        return em.createQuery("SELECT a FROM Author a", Author.class)
                .getResultList();
    }

    @Override
    public Author findById(Integer integer) {
        return em.find(Author.class, integer);
    }

    @Override
    public Author update(Author author) {
        return em.merge(author);
    }

    @Override
    public void delete(Integer integer) {
        Author author = findById(integer);
        if(author != null) {
            em.remove(author);
        }
    }
}
