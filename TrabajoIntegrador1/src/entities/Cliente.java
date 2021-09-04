package entities;

public class Cliente {

	private String name;
	private String email;
	private int id;
	
	public Cliente(String name, String email, int id) {
		super();
		this.name = name;
		this.email = email;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
