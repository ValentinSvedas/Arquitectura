package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

import entities.Factura;
import entities.Producto;


public class DAOProducto {


	public void add(Connection conn) throws SQLException, IOException {
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("productos.csv"));
			String insert = "INSERT INTO producto (id, name, value) VALUES(?,?,?)";

		for(CSVRecord row: parser) {
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, NumberUtils.toInt(row.get("idProducto")));
			ps.setString(2, row.get("nombre"));
			ps.setFloat(3, NumberUtils.toFloat(row.get("valor")));
			ps.executeUpdate();
			ps.close();	
			}
	}

	public void addProducto_facturas(Connection conn) throws SQLException, IOException {
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("facturas-productos.csv"));
		String insert = "INSERT INTO factura_producto (facturaid, productoid, cantidad) VALUES(?,?,?)";

		for(CSVRecord row: parser) {
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, NumberUtils.toInt(row.get("idFactura")));
			ps.setInt(2, NumberUtils.toInt(row.get("idProducto")));
			ps.setInt(3, NumberUtils.toInt(row.get("cantidad")));
			ps.executeUpdate();
			ps.close();
		}
	}
	
	
	public void cantidadProductos(Connection conn) throws SQLException {
		String select ="select count(*), fp.productoid,p.value, p.value*count(*), name from factura_producto fp  join producto p on fp.productoid = p.id group by productoid order by p.value*count(*) desc LIMIT 1";

		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println("Cantidad: "+ rs.getInt(1)+" producto id: "+rs.getInt(2)+" valor: "+rs.getInt(3)+" total recaudado: "+rs.getInt(4)+" nombre producto: "+rs.getString(5));
		}
	}
	public ArrayList<Producto> getProductos(Connection conn) throws SQLException {
		ArrayList<Producto> listaProductos = new ArrayList<>();
		String select = "select * from factura";
		//
		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Producto p= new Producto(rs.getString(2), rs.getFloat(3),rs.getInt(1));
			listaProductos.add(p);
	}
		return listaProductos;
		 
	}

	
	
	
	  /*
	 *    String uri = "jdbc:derby:MyDerbyDb;create=true";
        
        try {
			Connection conn = DriverManager.getConnection(uri);
			String select ="SELECT * FROM persona";
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getInt(3));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
	}
	 */

}

