package org.db.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.db.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * The base DAO for doing Hibernate actions.
 *
 * @param <T>
 *            The domain object that is managed by this DAO.
 * @param <KeyType>
 *            The key type implementation.
 */
@Repository
public abstract class AbstractHibernateBaseDao<T, KeyType extends Serializable> implements
        BaseDao<T, KeyType> {

    @PersistenceContext
    private EntityManager entityManager;

    /** The type of class the DAO is managing. */
    protected Class<T> domainClass = getDomainClass();

    /**
     * Method to return the class of the domain object.
     *
     * @return Returns a new domain object of the specified class type.
     */
    protected abstract Class<T> getDomainClass();

    protected CriteriaBuilder getDefaultCriteria() {
        return entityManager.getCriteriaBuilder();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public T save(final T object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public T update(final T object) {
        return entityManager.merge(object);
    }

    @Override
    public void delete(final T object) {
        entityManager.remove(object);
    }

    @Override
    public T findById(final KeyType id) {
        return entityManager.find(domainClass, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("from " + domainClass.getSimpleName(), domainClass).getResultList();
    }

    @Override
    public Long getObjectCount() {

        CriteriaBuilder criteria = getDefaultCriteria();
        CriteriaQuery<Long> criteriaQuery = criteria.createQuery(Long.class);
        criteriaQuery.select(criteria.count(criteriaQuery.from(domainClass)));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public void refresh(final T object) {
        entityManager.refresh(object);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    @Override
    public void evict(final T object) {
        entityManager.detach(object);
    }

}
