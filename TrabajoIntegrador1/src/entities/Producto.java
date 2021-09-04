package entities;

public class Producto {
	private String name;
	private float value;
	private int id;

	public Producto(String name, float value, int id) {
		super();
		this.name = name;
		this.value = value;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	} 

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
