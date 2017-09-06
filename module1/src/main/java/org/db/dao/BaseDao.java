package org.db.dao;

import java.util.List;

/**
 * Interface representing the basic functionalities of a DAO.
 *
 * @param <T>
 *            The domain object that will be managed.
 * @param <K>
 *            The key implementation.
 */
public interface BaseDao<T, K> {

    /**
     * Stores the passed object into a data store.
     *
     * @param object
     *            The object that will be stored.
     * @return Returns the saved object.
     */
    T save(final T object);

    /**
     * Removes the passed object from the data store.
     *
     * @param object
     *            The object that will be removed.
     */
    void delete(final T object);

    /**
     * Finds an object with the specified identifier.
     *
     * @param id
     *            The unique identifier of the object.
     * @return Returns the object with the corresponding unique identifier.
     */
    T findById(final K id);

    /**
     * Retrieves all objects.
     *
     * @return Returns a {@link List} containing all available objects.
     */
    List<T> findAll();

    /**
     * Refreshed the object with the data store.
     *
     * @param object
     *            The object that will be refreshed.
     */
    void refresh(final T object);

    /**
     * Flushes the session.
     */
    void flush();

    /**
     * Clears the session.
     */
    void clear();

    /**
     * Retrieves the number of objects that are available.
     *
     * @return Returns the number of available objects.
     */
    Long getObjectCount();

    /**
     * Evicts the specified object from the session.
     * 
     * @param object
     *            The object that will be evicted.
     */
    void evict(T object);

    /**
     * Updates a persistent object.
     * 
     * @param object
     *            The persistent object that will be updated.
     * @return Returns the updated object.
     */
    T update(T object);

}
