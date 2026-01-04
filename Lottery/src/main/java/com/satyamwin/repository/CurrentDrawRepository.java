package com.satyamwin.repository;

import com.satyamwin.entity.CurrentDrawResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentDrawRepository extends JpaRepository<CurrentDrawResult, Integer> {
}
