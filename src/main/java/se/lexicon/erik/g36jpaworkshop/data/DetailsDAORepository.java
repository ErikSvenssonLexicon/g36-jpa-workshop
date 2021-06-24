package se.lexicon.erik.g36jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.erik.g36jpaworkshop.model.Details;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class DetailsDAORepository implements DetailsDAO{

    private final EntityManager em;

    @Autowired
    public DetailsDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Details create(Details details) {
        em.persist(details);
        return details;
    }

    @Override
    public Collection<Details> findAll() {
        return em.createQuery("SELECT d FROM Details d", Details.class)
                .getResultList();
    }

    @Override
    public Details findById(Integer integer) {
        return em.find(Details.class, integer);
    }

    @Override
    public Details update(Details details) {
        return em.merge(details);
    }

    @Override
    public void delete(Integer integer) {
        Details details = findById(integer);
        if(details != null){
            em.remove(details);
        }
    }
}
