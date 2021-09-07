package dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

import entities.Cliente;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCliente {

    public void add(Connection conn) throws SQLException, IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("clientes.csv"));
        String insert = "INSERT INTO cliente (id, nombre, email) VALUES(?,?,?)";

        for(CSVRecord  row: parser) {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, NumberUtils.toInt(row.get("idCliente")));
            ps.setString(2, row.get("nombre"));
            ps.setString(3, row.get("email"));
            ps.executeUpdate();
            ps.close();
        }
    }
    public ArrayList<Cliente> getClientes(Connection conn) throws SQLException {
    	ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		String select ="select * from cliente";

		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Cliente c = new Cliente(rs.getString(2),rs.getString(3),rs.getInt(1));
			listaClientes.add(c);
		}
		return listaClientes;
	}
    	 
	
	
	
}

