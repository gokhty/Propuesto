package model;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import util.ConexionDB;
import util.Conversion;
import entidad.Cliente;
import entidad.Clientes;

public class ModelCliente {
	
	public List<Cliente> listaCliente() {
		
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = new ConexionDB().getConexion();
			String sql ="select * from cliente";  
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			Cliente c = null;
			while(rs.next()){
				c = new Cliente();
				c.setIdCliente(rs.getInt("idCliente"));
				c.setNombre(rs.getString("nombre"));
				c.setCorreo(rs.getString("correo"));
				data.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public List<Cliente> listaCliente1(String url) {
		
		List<Cliente>lstCli = new ArrayList<Cliente>();
		FileReader reader= null;
		try {
			reader = new FileReader(url);
			Gson gson = new Gson();
			 Cliente[] c1 =  gson.fromJson(reader, Cliente[].class);
			
			for (Cliente c : c1){
				lstCli.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				
			} catch (Exception e2) {}
		}
		return lstCli;
	}
	
public List<Cliente> listaCliente2(String url) {
		
		List<Cliente>lstCli = new ArrayList<Cliente>();
		
		try {
			Clientes datos = Conversion.fromXML(url, Clientes.class);
			for(Cliente c : datos.getClientes()){
				lstCli.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				
			} catch (Exception e2) {}
		}
		return lstCli;
	}

}
