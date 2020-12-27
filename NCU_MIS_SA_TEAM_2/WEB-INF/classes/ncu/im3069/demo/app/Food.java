package ncu.im3069.demo.app;

public class Food {
	private int id;
	private int foodTypesId;
	private int num;
	/**
	 * @param id
	 * @param foodTypesId
	 * @param num
	 */
	public Food(int id, int foodTypesId, int num) {
		super();
		this.id = id;
		this.foodTypesId = foodTypesId;
		this.num = num;
	}
	/**
	 * @param foodTypesId
	 * @param num
	 */
	public Food(int foodTypesId, int num) {
		super();
		this.foodTypesId = foodTypesId;
		this.num = num;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFoodTypesId() {
		return foodTypesId;
	}
	public void setFoodTypesId(int foodTypesId) {
		this.foodTypesId = foodTypesId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
