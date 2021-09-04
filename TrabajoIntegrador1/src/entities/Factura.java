package entities;

public class Factura {

	private int id;
	private int clienteid;
	
	public Factura(int id, int clienteid) {
		super();
		this.id = id;
		this.clienteid = clienteid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCliente() {
		return clienteid;
	}
	public void setIdCliente(int idCliente) {
		this.clienteid = idCliente;
	}
	
	
}
