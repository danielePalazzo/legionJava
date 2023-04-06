package org.generation.italy.legion.model.data.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static org.generation.italy.legion.model.data.HibernateConstants.*;

public interface CourseRepository extends GenericRepository<Course> {
    List<Course> findByTitleContains(String part) throws DataException;
    @Query("select count(c) from Course c where c.active = true")
    int countActiveCourses() throws DataException;
    @Query("from Course c")
    void deactivateOldest(int n) throws DataException;
    @Query("from Course c")
    boolean adjustActiveCourses(int NumActive) throws DataException;
    @Query(HQL_FIND_COURSE_ACTIVE_BY_TITLE_LIKE_AND_MIN_EDITION)
    Iterable<Course> findByTitleAndIsActiveAndMinEdition(String part,boolean status,int minEditions) throws DataException;

    Iterable<Course> findByTitleContainingAndActiveTrue(String part) throws DataException;
    @Query(HQL_FIND_COURSE_BY_TITLE_LIKE_AND_MIN_EDITION)
    Iterable<Course> findByTitleAndMinEdition(String part,int minEditions) throws DataException;
}

/*
    Utilizziamo le exceptions che abbiamo creato noi (nel package "exceptions")
    così da riuscire a poter utilizzare questa interfaccia con tutte le classi che vogliamo
    e gestire l'errore in maniera SPECIFICA!

    Creiamo un altro repository chiamato CourseEditionRepository con Interfaccia
    con una sola implementazione InMemory, ma senza usare nemmeno un ciclo for

    Terrà i dati in una mappa di CourseEdition.

    La CourseEditionRepository dovrà avere i seguenti metodi:
    1. Restituisce la somma di tutti i costi per tutte le CourseEdition
    2. restituisce la CourseEdition che ha il costo maggiore
    3. Restituisce il valor medio dei costi delle CourseEdition
    4. Restituisce una lista delle durate di tutte le CourseEdition
    5. Restituisce tutte le CourseEdition per un certo Course (usando l'id)
    6. Restituisce tutte le CourseEdition relative a un Course con una certa parola nel titolo
        e che sono partite entro un range di date che inseriremo come input
        Es.: tutte le CourseEdition relative ai Corsi che contengono la parola "Java" e che sono partiti tra
        il 2007 e il 2012
    7. Restituisce la CourseEdition ha il costo mediano
        Se sono pari, restituiremo la media fra i due che sono nel mezzo
        Es.: [1, 2, 3, 4] -> (2 + 3) / 2 = 2.5
    8. Restituisce il Course che ha come costo la moda della distribuzione dei corsi (quello che appare più votle)
 */
