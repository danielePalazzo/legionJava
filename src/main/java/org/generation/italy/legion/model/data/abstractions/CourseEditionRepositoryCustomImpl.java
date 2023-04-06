package org.generation.italy.legion.model.data.abstractions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.generation.italy.legion.model.entities.CourseEdition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.generation.italy.legion.model.data.HibernateConstants.*;

public class CourseEditionRepositoryCustomImpl implements CourseEditionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    //TypedQuery
    @Override
    public Iterable<CourseEdition> findMedian() {
        TypedQuery<Long> qCountCe = entityManager.createQuery(HQL_COUNT_COURSE_EDITION,Long.class);
        int countCe = qCountCe.getSingleResult().intValue();
        if (countCe == 0){
            return new ArrayList<>();
        }
        TypedQuery<CourseEdition> qAllCE = entityManager.createQuery(HQL_COURSE_EDITION_LIST_ASC,CourseEdition.class);
        if (countCe%2 != 0){
            return qAllCE.setFirstResult(countCe/2).setMaxResults(1).getResultList();
        }
        return qAllCE.setFirstResult(countCe/2 - 1).setMaxResults(2).getResultList();
    }

    @Override
    public Optional<Double> getCourseEditionCostMode() {
        TypedQuery<Double> q = entityManager.createQuery(HQL_FIND_MODE_COURSE_EDITION_COST,Double.class);
        return q.setMaxResults(1).getResultList().stream().findFirst(); //se trova il primo elemento lo restituisce come optional se non trova nulla ritorna optional vuoto
    }

//    @Override
//    public Iterable<CourseEdition> findMedian() {
//        Query firstQuery = entityManager.createQuery("SELECT count(CourseEdition) FROM CourseEdition");
//        int lunghezza = firstQuery.getFirstResult();
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<CourseEdition> query = cb.createQuery(CourseEdition.class);
//        Root<CourseEdition> ce = query.from(CourseEdition.class); //chiedere cosa fa root
//        Path<String> emailPath = ce.get("cost");
//        if (lunghezza%2 == 0){
//
//        }else {
//            query.select(ce).orderBy(cb.desc(ce.get("cost")));//???????????????????????????
//        }
//        return null;
//
//    }
}
