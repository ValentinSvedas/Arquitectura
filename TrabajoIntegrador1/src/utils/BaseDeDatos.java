package utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.DAOCliente;
import dao.DAOFactura;
import dao.DAOProducto;

public class BaseDeDatos {
	private DAOCliente dc = new DAOCliente();
	private DAOFactura df = new DAOFactura();
	private DAOProducto dp = new DAOProducto();
	public void conectarBase(){
		// TODO Auto-generated method stub
		String driver = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		

        String uri = "jdbc:mysql://localhost:3306/exampleDB";
        
        try {
			Connection conn = DriverManager.getConnection(uri, "root", "password");
			conn.setAutoCommit(false);
			dp.addProductos(conn);
			conn.commit();
			conn.close();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
	}
	private void createTables(Connection conn) throws SQLException {
		String table = "CREATE TABLE cliente("+
				"id INT NOT NULL," +
				"nombre VARCHAR(500),"+
				"email varchar(150)," +
				"CONSTRAINT cliente_pk PRIMARY KEY(id));";
		String table2 = "CREATE TABLE producto ("
				+ "id INT NOT NULL,"
				+ "name VARCHAR(45) NOT NULL,"
				+ "value FLOAT NOT NULL,"
				+ "CONSTRAINT producto_pk PRIMARY KEY (id));";
		String table3 = "CREATE TABLE factura ("
				+ "id int NOT NULL,"
				+ "clienteid int NOT NULL,"
				+ "CONSTRAINT factura_pk PRIMARY KEY (id));";
		String table4 = "CREATE TABLE factura_producto ("
				+ " facturaid int NOT NULL,"
				+ "productoid int NOT NULL,"
				+ "cantidad int NOT NULL,"
				+ "CONSTRAINT factura_producto_pk PRIMARY KEY (facturaid,productoid));";
		String alterTable = "ALTER TABLE factura ADD CONSTRAINT factura_cliente FOREIGN KEY factura_cliente (clienteid)" + 
				"REFERENCES cliente (id);"; 
		String alterTable1 = "ALTER TABLE factura_producto ADD CONSTRAINT factura_producto FOREIGN KEY factura_producto (facturaid)" + 
				"    REFERENCES factura (id);";
		String alterTable2 = "ALTER TABLE factura_producto ADD CONSTRAINT producto_factura FOREIGN KEY producto_factura (productoid)" + 
		"    REFERENCES producto (id);";
		
		conn.prepareStatement(table).execute();
		conn.prepareStatement(table2).execute();
		conn.prepareStatement(table3).execute();
		conn.prepareStatement(table4).execute();
		conn.prepareStatement(alterTable).execute();
		conn.prepareStatement(alterTable1).execute();
		conn.prepareStatement(alterTable2).execute();

		
		
	}
}
