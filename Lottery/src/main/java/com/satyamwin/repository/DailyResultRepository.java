package com.satyamwin.repository;

import com.satyamwin.entity.DailyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface DailyResultRepository extends JpaRepository<DailyResult, Integer> {
    List<DailyResult> findByResultDate(LocalDate resultDate);
}
