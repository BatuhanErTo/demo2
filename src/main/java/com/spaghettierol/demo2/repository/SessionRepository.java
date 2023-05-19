package com.spaghettierol.demo2.repository;

import com.spaghettierol.demo2.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    boolean existsSessionById(Long id);

    List<Session> findSessionByModule_Code(String code);
}
