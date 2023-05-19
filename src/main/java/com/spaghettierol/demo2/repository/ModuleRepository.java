package com.spaghettierol.demo2.repository;

import com.spaghettierol.demo2.model.Module;
import com.spaghettierol.demo2.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long> {
    Module findModuleByCode(String code);
    boolean existsModuleByCode(String code);
}
