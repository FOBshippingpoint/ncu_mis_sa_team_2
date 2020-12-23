package ncu.im3069.demo.app;

public class Hall {
	private int id = -1;
	private String name;

	public Hall(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Hall(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		if(this.id == -1) {
			this.id = HallHelper.getHelper().getHallByName(this.name).getId();
		}
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}