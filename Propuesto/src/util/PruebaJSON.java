package util;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import entidad.Cliente;

public class PruebaJSON {

	public static void main(String[] args) {
			
		Cliente obj1 = new Cliente(15, "Luis", "Perez@gmail.com");
		Cliente obj2 = new Cliente(12, "Ana", "Quispe@gmail.com");
		Cliente obj3 = new Cliente(13, "Luisa", "Arcos@gmail.com");
		Cliente obj4 = new Cliente(14, "Alberto", "Jacinto@gmail.com");
		
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		data.add(obj1);
		data.add(obj2);
		data.add(obj3);
		data.add(obj4);
		
		
		Conversion.toJSON("F:/cliente.json", data);
		
		//Cliente datos = Conversion.fromJSON("F:/cliente.json", Cliente.class);
		//System.out.println(datos.getNombre());
		
		////De objeto Java----->Archivo JSON
		//---------------------------------------------
		FileWriter file= null;
		try { System.out.println("json creado en f:/datos/clientes901.json");
			file = new FileWriter("F:/datos/cliente901.json");
			Gson gson = new Gson();
		gson.toJson(data, file);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				file.close();
			} catch (Exception e2) {}
		}
		//--------------------------------------------
		//De archivo JSON -----> al objeto java
		//---------------------------------------------
		List<Cliente>lstCli = new ArrayList<Cliente>();
		FileReader reader= null;
		try {
			reader = new FileReader("F:/datos/cliente901.json");
			Gson gson = new Gson();
			 Cliente[] c1 =  gson.fromJson(reader, Cliente[].class);
			
			for (Cliente c : c1){
				lstCli.add(c);
			}
			for(Cliente c: lstCli){
				System.out.println(c.getNombre());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				file.close();
			} catch (Exception e2) {}
		}
	}
	

}
