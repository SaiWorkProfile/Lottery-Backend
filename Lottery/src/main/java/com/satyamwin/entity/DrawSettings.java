package com.satyamwin.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "draw_settings")
public class DrawSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer visitors;
    private String nextDrawTime;

    private LocalDateTime updatedAt = LocalDateTime.now();

    public DrawSettings() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVisitors() {
		return visitors;
	}

	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}

	public String getNextDrawTime() {
		return nextDrawTime;
	}

	public void setNextDrawTime(String nextDrawTime) {
		this.nextDrawTime = nextDrawTime;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}
