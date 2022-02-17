package com.pravin.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "todos")
public class Todo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "task")
    private String task;

    @Column(name="dueDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private java.sql.Timestamp dueDateTime;
    
    @Column(name="CreatedDateTime")
    private String CreatedDateTime;
    
    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    public Todo() {
    }

    public Todo(String task, String description, String status) {
        super();
        this.task = task;
        this.description = description;
        this.status = status;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return task;
    }

    public void setTopic(String topic) {
        this.task = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public java.sql.Timestamp getDueDateTime() {
		return dueDateTime;
	}

	public void setDueDateTime(java.sql.Timestamp dueDateTime) {
		this.dueDateTime = dueDateTime;
	}

	public String getCreatedDateTime() {
		return CreatedDateTime;
	}

	public void setCreatedDateTime(String localDateTime) {
		CreatedDateTime = localDateTime;
	}
    
}
