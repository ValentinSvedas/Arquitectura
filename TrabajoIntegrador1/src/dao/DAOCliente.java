package dao;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOCliente {

    public void add(Connection conn) throws SQLException, IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("clientes.csv"));
        String insert = "INSERT INTO cliente (id, nombre, email) VALUES(?,?,?)";

        for(CSVRecord row: parser) {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, NumberUtils.toInt(row.get("idCliente")));
            ps.setString(2, row.get("nombre"));
            ps.setString(3, row.get("email"));
            ps.executeUpdate();
            ps.close();
        }
    }
	
	
	
}

