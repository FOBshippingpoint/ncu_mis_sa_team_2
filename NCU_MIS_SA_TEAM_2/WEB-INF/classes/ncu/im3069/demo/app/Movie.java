package ncu.im3069.demo.app;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Movie {
	private int id;
	private String title;
	private String introduction;
	private int rating;
	private String version;
	private int price;
	private int length;
	private LocalDate onDate;
	private LocalDate offDate;
	
	public Movie(String title, String introduction, int rating, String version, int price, int length,
			LocalDate onDate, LocalDate offDate) {
		super();
		this.title = title;
		this.introduction = introduction;
		this.rating = rating;
		this.version = version;
		this.price = price;
		this.length = length;
		this.onDate = onDate;
		this.offDate = offDate;
	}
	
	public Movie(int id, String title, String introduction, int rating, String version, int price, int length,
			LocalDate onDate, LocalDate offDate) {
		super();
		this.id = id;
		this.title = title;
		this.introduction = introduction;
		this.rating = rating;
		this.version = version;
		this.price = price;
		this.length = length;
		this.onDate = onDate;
		this.offDate = offDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public LocalDate getOnDate() {
		return onDate;
	}
	public String getOnDateString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return this.getOnDate().format(formatter);
	}
	public void setOnDate(LocalDate onDate) {
		this.onDate = onDate;
	}
	public LocalDate getOffDate() {
		return offDate;
	}
	public String getOffDateString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return this.getOffDate().format(formatter);
	}
	public void setOffDate(LocalDate offDate) {
		this.offDate = offDate;
	}
	
	public ArrayList<String> getDatesString(){
		ArrayList<String> dates = new ArrayList<String>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate date = this.getOnDate();
		while(date.isBefore(this.getOffDate().plusDays(1))) {
			dates.add(date.format(formatter));
			date = date.plusDays(1);
		}
		
		return dates;
	}
}
