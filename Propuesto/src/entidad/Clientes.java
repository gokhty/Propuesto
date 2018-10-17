package entidad;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Clientes {
		
		private List<Cliente> clientes;
		
		
		
		public Clientes() {
			super();
		}

		public Clientes( List<Cliente> clientes) {
	
			this.clientes = clientes;
		}

		public List<Cliente> getClientes() {
			return clientes;
		}

		public void setClientes(List<Cliente> clientes) {
			this.clientes = clientes;
		}
		
		
}
