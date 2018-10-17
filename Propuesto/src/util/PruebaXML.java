package util;

import entidad.Clientes;
import entidad.Cliente;

import java.util.ArrayList;
import java.util.List;


//-------------------------------------------
// Java Architecture for XML Binding (JAXB)
//-------------------------------------------

public class PruebaXML {

	public static void main(String[] args) {
		Cliente a1 = new Cliente(12, "Jorge Jacinto Gutarra", "Jorge@gmail.com");
		Cliente a2 = new Cliente(13, "Pedro Jacinto Gutarra","Pedro@gmail.com");
		Cliente a3 = new Cliente(14, "Lucia Jacinto Gutarra", "Lucia@gmail.com");
		
		ArrayList<Cliente> clis = new ArrayList<Cliente>();
		clis.add(a1);
		clis.add(a2);
		clis.add(a3);
		
		Clientes cli = new Clientes();
		cli.setClientes(clis);
	
		Conversion.toXML("F:/datos/aula.xml", cli);
		
		Clientes datos = Conversion.fromXML("F:/datos/aula.xml", Clientes.class);
		//System.out.println(datos.getClientes().get(0).getNombre());
		List<Cliente> lstCli = new ArrayList<Cliente>();
		for(Cliente c : datos.getClientes()){
			lstCli.add(c);
		}
		for(Cliente c : lstCli){
			System.out.println(c.getNombre());
			System.out.println(c.getCorreo());
			System.out.println(c.getIdCliente());
		}
	}
}
