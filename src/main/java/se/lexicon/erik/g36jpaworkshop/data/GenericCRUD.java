package se.lexicon.erik.g36jpaworkshop.data;

import java.util.Collection;

public interface GenericCRUD<T, ID> {
    T create(T t);
    Collection<T> findAll();
    T findById(ID id);
    T update(T t);
    void delete(ID id);
}
