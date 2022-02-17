package com.pravin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pravin.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
