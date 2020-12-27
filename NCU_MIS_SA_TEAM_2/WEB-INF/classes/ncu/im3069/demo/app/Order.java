package ncu.im3069.demo.app;

import java.time.LocalDateTime;

public class Order {
	private int id;
	private int memberId;
	private LocalDateTime purchased;
	private LocalDateTime canceled;
	
	public Order(int memberId, LocalDateTime purchased) {
		super();
		this.memberId = memberId;
		this.purchased = purchased;
	}
	public Order(int memberId, LocalDateTime purchased, LocalDateTime canceled) {
		super();
		this.memberId = memberId;
		this.purchased = purchased;
		this.canceled = canceled;
	}
	public Order(int id, int memberId, LocalDateTime purchased, LocalDateTime canceled) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.purchased = purchased;
		this.canceled = canceled;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public LocalDateTime getPurchased() {
		return purchased;
	}
	public void setPurchased(LocalDateTime purchased) {
		this.purchased = purchased;
	}
	public LocalDateTime getCanceled() {
		return canceled;
	}
	public void setCanceled(LocalDateTime canceled) {
		this.canceled = canceled;
	}
	
	
}
