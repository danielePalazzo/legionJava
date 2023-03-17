package org.generation.italy.legion.model.data.implementations;

import jakarta.persistence.EntityManager;
import org.generation.italy.legion.model.data.abstractions.AbstractCrudRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GenericCrudRepository<T> implements AbstractCrudRepository<T> {
   //@Autowired
   protected Session session;
   protected EntityManager entityManager;
   protected Class<?> entityClass;

   public GenericCrudRepository(EntityManager em, Class<?> entityClass) {
      this.entityManager = em;
      this.session = em.unwrap(Session.class);     // questo tira fuori la Session dall'EntityManager, perché Spring lavora nativamente con JPA non con Hibernate
      this.entityClass = entityClass;
   }

   @Override
   public List<T> findAll() throws DataException {
      Query<T> q = (Query<T>) session.createQuery("from " + entityClass.getSimpleName(), entityClass);
      return q.list();
   }

   @Override
   public Optional<T> findById(long id) throws DataException {
      T found = (T) session.get(entityClass, id);
      return found != null ? Optional.of(found) : Optional.empty();
   }

   @Override
   public T create(T entity) throws DataException {
      session.persist(entity);
      return entity;
   }

   @Override
   public void update(T entity) throws EntityNotFoundException, DataException {
      session.merge(entity);
   }

   @Override
   public void deleteById(long id) throws EntityNotFoundException, DataException {
      T entity = (T) session.getReference(entityClass, id);
      session.remove(entity);
   }
}