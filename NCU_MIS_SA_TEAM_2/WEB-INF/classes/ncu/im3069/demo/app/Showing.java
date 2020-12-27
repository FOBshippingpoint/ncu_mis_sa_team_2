package ncu.im3069.demo.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Showing {
	private int id;
	private int movieId;
	private int hallId;
	private LocalDateTime start;
	private LocalDateTime end;
	
	public Showing(int id, int movieId, int hallId, LocalDateTime start, LocalDateTime end) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.hallId = hallId;
		this.start = start;
		this.end = end;
	}
	
	public Showing(int movieId, int hallId, LocalDateTime start, LocalDateTime end) {
		super();
		this.movieId = movieId;
		this.hallId = hallId;
		this.start = start;
		this.end = end;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	public LocalDateTime getStart() {
		return start;
	}
	public String getStartString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return this.getStart().format(formatter);
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public String getEndString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return this.getEnd().format(formatter);
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public LocalDate getDate() {
		return this.getStart().toLocalDate();
	}
	
	public String getDateString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return this.getDate().format(formatter);
	}
}
