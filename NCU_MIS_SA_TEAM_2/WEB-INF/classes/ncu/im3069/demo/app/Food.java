package ncu.im3069.demo.app;

public class Food {
	private int id;
	private int orderId;
	private int foodTypeId;
	private int num;
	/**
	 * @param id
	 * @param orderId
	 * @param foodTypesId
	 * @param num
	 */
	public Food(int id, int orderId, int foodTypeId, int num) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.foodTypeId = foodTypeId;
		this.num = num;
	}
	/**
	 * @param orderId
	 * @param foodTypesId
	 * @param num
	 */
	public Food(int orderId, int foodTypeId, int num) {
		super();
		this.orderId = orderId;
		this.foodTypeId = foodTypeId;
		this.num = num;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(int foodTypesId) {
		this.foodTypeId = foodTypesId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
