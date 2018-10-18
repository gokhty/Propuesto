package util;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.DocumentException;
import java.io.*;

import entidad.Cliente;

public abstract class PDF {
	public  static void crear(List<Cliente> data, String guardar_en){
		try {
		    Document documento = new Document();
		    try {
		        PdfWriter.getInstance(documento, new FileOutputStream("F:/aula/hola.pdf"));
		       
		    } catch (FileNotFoundException fileNotFoundException) {
		        System.out.println("No such file was found to generate the PDF "
		                + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
		    }
		    documento.open();
		 
		    // AQUÍ COMPLETAREMOS NUESTRO CÓDIGO PARA GENERAR EL PDF
		 	        
	        PdfPTable tabla = new PdfPTable(3);
	      /*  for (int i = 0; i < 15; i++)
	        {
	        	tabla.addCell("celda " + i);
	        }
	        tabla.addCell(data.get(0).getIdCliente()+"");
	        tabla.addCell(data.get(0).getNombre());
	        tabla.addCell(data.get(0).getCorreo());*/
	        tabla.addCell("Código");
	        tabla.addCell("Nombre");
	        tabla.addCell("Correo");
	        for(Cliente c : data){
	        	tabla.addCell(c.getIdCliente()+"");
	        	tabla.addCell(c.getNombre());
	        	tabla.addCell(c.getCorreo());
	        }
	        documento.add(tabla);
	        
		    documento.close();
		    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
		} catch (DocumentException documentException) {
		    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
		}
	}
}
