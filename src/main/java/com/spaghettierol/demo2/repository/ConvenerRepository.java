package com.spaghettierol.demo2.repository;

import com.spaghettierol.demo2.model.Convener;
import com.spaghettierol.demo2.model.Module;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ConvenerRepository extends JpaRepository<Convener,Long> {
}
