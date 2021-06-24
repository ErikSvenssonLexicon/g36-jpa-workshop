package se.lexicon.erik.g36jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.erik.g36jpaworkshop.model.AppUser;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class AppUserDAORepository implements AppUserDAO{

    private final EntityManager em;

    @Autowired
    public AppUserDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        em.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<AppUser> findAll() {
        return em.createQuery("SELECT u FROM AppUser u", AppUser.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(Integer integer) {
        return em.find(AppUser.class, integer);
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        return em.merge(appUser);
    }

    @Override
    @Transactional
    public void delete(Integer integer) {
        AppUser user = findById(integer);
        if(user != null){
            em.remove(user);
        }
    }
}
