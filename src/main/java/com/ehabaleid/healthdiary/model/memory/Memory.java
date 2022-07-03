package com.ehabaleid.healthdiary.model.memory;

import com.ehabaleid.healthdiary.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "memory_log")
public class Memory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String memory;
    @ManyToOne
    private User user;
    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    public Memory() {
    }

    public Memory(String memory) {
        this.memory = memory;
    }

    public int getId() {
        return id;
    }

    public String getMemory() {
        return memory;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
