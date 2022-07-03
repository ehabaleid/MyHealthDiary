package com.ehabaleid.healthdiary.data;

import com.ehabaleid.healthdiary.model.memory.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Integer> {

    List<Memory> findByUserId(int id);
    List<Memory> findByDateAndUserId(LocalDate date, int id);
}
