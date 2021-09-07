package dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

import entities.Cliente;
import entities.Factura;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOFactura {

    public void add(Connection conn) throws SQLException, IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("facturas.csv"));
        String insert = "INSERT INTO factura (id, clienteid) VALUES(?,?)";

        for(CSVRecord row: parser) {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, NumberUtils.toInt(row.get("idFactura")));
            ps.setInt(2, NumberUtils.toInt(row.get("idCliente")));
            ps.executeUpdate();
            ps.close();
        }
    }
    
    public ArrayList<Factura> getFacturas(Connection conn) throws SQLException {
	ArrayList<Factura> listaFacturas = new ArrayList<>();
	String select = "select * from factura order by clienteid";
	PreparedStatement ps = conn.prepareStatement(select);
	ResultSet rs = ps.executeQuery();
	while(rs.next()) {
		Factura f= new Factura(rs.getInt(1), rs.getInt(2));
		listaFacturas.add(f);
}
	return listaFacturas;
	 
}
    
    public int cantidadProductoPorFactura(Connection conn, int clienteid) throws SQLException {
    	int cantT=0;
		String select = "select sum(fp.cantidad*p.value) from factura_producto fp join factura f on f.id = fp.facturaid join producto p on fp.productoid = p.id where clienteid =" + clienteid;
		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			cantT = rs.getInt(1);
	}
		return cantT;
    }
}
