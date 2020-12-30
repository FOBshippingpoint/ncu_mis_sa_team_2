package ncu.im3069.demo.app;

public class Member {
	private int id;
	private String name;
	private String email;
	private String password;
	private boolean isAdmin;
	public Member(int id, String name, String email, String password, boolean isAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public Member(String name, String email, String password, boolean isAdmin) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public int getId() {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
