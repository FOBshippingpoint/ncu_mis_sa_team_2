package ncu.im3069.demo.app;

public class Ticket {
	private int id;
	private int seatId;
	private int showingId;
	private int orderId;
	/**
	 * @param id
	 * @param seatId
	 * @param showingId
	 * @param orderId
	 */
	public Ticket(int id, int seatId, int showingId, int orderId) {
		super();
		this.id = id;
		this.seatId = seatId;
		this.showingId = showingId;
		this.orderId = orderId;
	}
	/**
	 * @param seatId
	 * @param showingId
	 * @param orderId
	 */
	public Ticket(int seatId, int showingId, int orderId) {
		super();
		this.seatId = seatId;
		this.showingId = showingId;
		this.orderId = orderId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getShowingId() {
		return showingId;
	}
	public void setShowingId(int showingId) {
		this.showingId = showingId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
