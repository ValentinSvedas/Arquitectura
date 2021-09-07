package entities;

public class Cliente implements Comparable<Cliente>{

	private String name;
	private String email;
	private int id;
	private int cantFacturacionTotal;
	
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
	public int getCantFacturacionTotal() {
		return cantFacturacionTotal;
	}
	public void setCantFacturacionTotal(int cantFacturacionTotal) {
		this.cantFacturacionTotal = cantFacturacionTotal;
	}
	@Override
	public int compareTo(Cliente c2) {
		return this.getCantFacturacionTotal() - c2.getCantFacturacionTotal();
	}
	
	@Override
	public String toString() {
		return name +" "+ this.cantFacturacionTotal;
	}
	
	
	
}
