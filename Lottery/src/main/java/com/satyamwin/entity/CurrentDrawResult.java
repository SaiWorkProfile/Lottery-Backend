package com.satyamwin.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "current_draw_result")
public class CurrentDrawResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String drawTime;
    private Integer satyamA;
    private Integer satyamB;
    private Integer satyamC;
    private LocalDate drawDate;
    public CurrentDrawResult() {
	// TODO Auto-generated constructor stub
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDrawTime() {
		return drawTime;
	}
	public void setDrawTime(String drawTime) {
		this.drawTime = drawTime;
	}
	public Integer getSatyamA() {
		return satyamA;
	}
	public void setSatyamA(Integer satyamA) {
		this.satyamA = satyamA;
	}
	public Integer getSatyamB() {
		return satyamB;
	}
	public void setSatyamB(Integer satyamB) {
		this.satyamB = satyamB;
	}
	public Integer getSatyamC() {
		return satyamC;
	}
	public void setSatyamC(Integer satyamC) {
		this.satyamC = satyamC;
	}
	public LocalDate getDrawDate() {
		return drawDate;
	}
	public void setDrawDate(LocalDate drawDate) {
		this.drawDate = drawDate;
	}
    
}
