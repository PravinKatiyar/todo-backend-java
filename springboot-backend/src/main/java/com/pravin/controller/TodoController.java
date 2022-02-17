package com.pravin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pravin.exception.ResourceNotFoundException;
import com.pravin.model.Todo;
import com.pravin.repository.TodoRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	@GetMapping("/todos")
	public List<Todo> getAllTodos() {
		List<Todo> findAll = todoRepository.findAll();
		Collections.sort(findAll, Comparator.comparing(Todo::getStatus).reversed().thenComparing(Todo::getDueDateTime));

		return findAll;
	}

	@PostMapping("/todos")
	public Todo createTodo(@RequestBody Todo todo) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		todo.setCreatedDateTime(dtf.format(now));
		return todoRepository.save(todo);
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(todo);
	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

		todo.setTopic(todoDetails.getTopic());
		todo.setDescription(todoDetails.getDescription());
		todo.setStatus(todoDetails.getStatus());

		Todo updatedTodo = todoRepository.save(todo);
		return ResponseEntity.ok(updatedTodo);
	}

	@PutMapping("/todos/status/{id}")
	public ResponseEntity<Todo> completeTodo(@PathVariable Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));

		todo.setStatus("DONE");

		Todo updatedTodo = todoRepository.save(todo);
		return ResponseEntity.ok(updatedTodo);
	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTodo(@PathVariable Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

		todoRepository.delete(todo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
