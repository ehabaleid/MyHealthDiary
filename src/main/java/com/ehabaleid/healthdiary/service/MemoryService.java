package com.ehabaleid.healthdiary.service;

import com.ehabaleid.healthdiary.data.MemoryRepository;
import com.ehabaleid.healthdiary.model.memory.Memory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemoryService {

    @Autowired
    MemoryRepository memoryRepository;

    public void saveUserMemoryToDatabase(Memory userMemoryLog) {
        memoryRepository.save(userMemoryLog);
    }

    public List<Memory> loadMemoryFromDatabaseByUserId(int userId) throws NoSuchElementException {
        List<Memory> memories = memoryRepository.findByUserId(userId);
        if (memories == null) {
            throw new NoSuchElementException("No memory with user id: " + userId);
        }
        return memories;
    }

    public List<Memory> loadMemoryFromDatabaseByUserIdForDate(LocalDate localDate, int id) throws NoSuchElementException {
        List<Memory> memories = memoryRepository.findByDateAndUserId(localDate, id);
        if (memories == null) {
            throw new NoSuchElementException("No memories found with user id: " + id + "for date: " + localDate);
        }
        return memories;
    }

}
