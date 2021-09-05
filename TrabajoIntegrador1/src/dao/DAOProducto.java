package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;


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

}
