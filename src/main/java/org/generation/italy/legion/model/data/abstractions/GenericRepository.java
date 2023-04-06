package org.generation.italy.legion.model.data.abstractions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;    //non implementa i metodi di JpaRepository

@NoRepositoryBean
public interface GenericRepository<T> extends JpaRepository<T,Long> {

}
