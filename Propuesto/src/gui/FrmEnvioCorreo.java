package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.ModelCliente;
import net.atlanticbb.tantlinger.shef.HTMLEditorPane;
import entidad.Cliente;
import mail.ModelMail;
import util.PDF;
@SuppressWarnings("serial")
public class FrmEnvioCorreo extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTable table;
	private JButton btnFiltrar;
	private JButton btnCargaClienteXml;
	private JButton btnCargaClienteJson;
	private JButton btnEnviarMail;
	private JButton button;
	private JLabel lblFile;
	private JButton button_1;
	private JButton button_2;
	private JButton btnExp;
	HTMLEditorPane editorPane;
	/**
	 * Launch the application.
	 */
	private String json,xml;
	private List<Cliente>nlst;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEnvioCorreo frame = new FrmEnvioCorreo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public FrmEnvioCorreo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConsultaCampeonatoPort = new JLabel(
				"Publicidad Masiva de correos");
		lblConsultaCampeonatoPort.setOpaque(true);
		lblConsultaCampeonatoPort.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaCampeonatoPort
				.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConsultaCampeonatoPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaCampeonatoPort.setForeground(Color.WHITE);
		lblConsultaCampeonatoPort.setBackground(Color.RED);
		lblConsultaCampeonatoPort.setBounds(10, 11, 907, 44);
		contentPane.add(lblConsultaCampeonatoPort);

		btnFiltrar = new JButton("Carga Cliente BD");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(663, 84, 254, 23);
		contentPane.add(btnFiltrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 610, 167);
		contentPane.add(scrollPane);
		
		btnExp = new JButton("Crear PDF");
		btnExp.addActionListener(this);
		btnExp.setBounds(10, 269, 254, 23);
		contentPane.add(btnExp);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {

			},
			new String[] {
				"estado", "Id", "Nombre", "Correo"
			}
		) {
			
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class
			};
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		scrollPane.setViewportView(table);
		
		btnCargaClienteXml = new JButton("Carga Cliente XML");
		btnCargaClienteXml.addActionListener(this);
		btnCargaClienteXml.setBounds(709, 118, 208, 23);
		contentPane.add(btnCargaClienteXml);
		
		btnCargaClienteJson = new JButton("Carga Cliente JSON");
		btnCargaClienteJson.addActionListener(this);
		btnCargaClienteJson.setBounds(709, 152, 208, 23);
		contentPane.add(btnCargaClienteJson);
		
		btnEnviarMail = new JButton("Enviar Mail");
		btnEnviarMail.addActionListener(this);
		btnEnviarMail.setBounds(663, 296, 254, 23);
		contentPane.add(btnEnviarMail);
		
		lblFile = new JLabel("");
		lblFile.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFile.setBounds(10, 299, 562, 20);
		contentPane.add(lblFile);
		
		button = new JButton("...");
		button.addActionListener(this);
		button.setBounds(593, 296, 35, 23);
		contentPane.add(button);
		
		editorPane = new HTMLEditorPane();
		editorPane.setBackground(Color.WHITE);
		editorPane.setBounds(10, 331, 907, 195);
		contentPane.add(editorPane);
		
		button_1 = new JButton("...");
		button_1.addActionListener(this);
		button_1.setBounds(663, 118, 35, 23);
		contentPane.add(button_1);
		
		button_2 = new JButton("...");
		button_2.addActionListener(this);
		button_2.setBounds(663, 152, 35, 23);
		contentPane.add(button_2);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == button_2) {
			do_button_2_actionPerformed(arg0);
		}
		if (arg0.getSource() == button_1) {
			do_button_1_actionPerformed(arg0);
		}
		if (arg0.getSource() == button) {
			do_button_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnEnviarMail) {
			do_btnEnviarMail_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnCargaClienteJson) {
			do_btnCargaClienteJson_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnCargaClienteXml) {
			do_btnCargaClienteXml_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnFiltrar) {
			do_btnFiltrar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnExp) {
			do_btnExp_actionPerformed(arg0);
		}
	}
	void lista(List<Cliente> data){
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		for (Cliente x : data) {
			Object[] fila = {true,x.getIdCliente(),
							 x.getNombre(),
							 x.getCorreo()};
			
			dtm.addRow(fila);
		}
	}
	protected void do_btnExp_actionPerformed(ActionEvent arg0) {
		String s =s().replace("\\", "/");
		PDF.crear(nlst, s);
	}
	protected void do_btnFiltrar_actionPerformed(ActionEvent arg0) {
		ModelCliente m = new ModelCliente();
		nlst = m.listaCliente();
		lista(nlst);
	}
	protected void do_btnCargaClienteXml_actionPerformed(ActionEvent arg0) {
		
		String url = xml;
		ModelCliente m = new ModelCliente();
		nlst = m.listaCliente2(url);
		lista(nlst);
	}
	protected void do_btnCargaClienteJson_actionPerformed(ActionEvent arg0) {
		String url = json;
		ModelCliente m = new ModelCliente();
		nlst = m.listaCliente1(url);
		lista(nlst);
	}
	protected void do_btnEnviarMail_actionPerformed(ActionEvent arg0) {
		ModelMail modelMail = new ModelMail();
		
		String to = "cibertron12345@gmail.com";
		String filePath = lblFile.getText();
		String subject = "nada";
	
		String username = "cibertron12345@gmail.com";
		String password = new String("Wum2zxm5");

		String texto = editorPane.getText().trim();
		
		String strResultado = modelMail.pruebaMail(username, password, subject, to, texto, filePath);
		
		
		System.out.println(strResultado);
	}
	protected void do_button_actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);
		int returnVal = fc.showDialog(this, "Seleccione file");
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			  File[] file = fc.getSelectedFiles();
			  String rutas = "";
			  for (int i = 0; i < file.length; i++) {
				  rutas += file[i].getAbsolutePath();
				  if(i != file.length-1) rutas+=",";
			  }
			  lblFile.setText(rutas);
		}
	}
	protected void do_button_1_actionPerformed(ActionEvent arg0) {
		xml = s();
	}
	protected void do_button_2_actionPerformed(ActionEvent arg0) {
		json = s();
	}
	String s(){
		String u = null;
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showDialog(this, "Seleccione file");
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			  File file = fc.getSelectedFile();
			  //lblFile.setText(file.getAbsolutePath());
			  u = file.getAbsolutePath();
		}
		return u;
	}
	public void selectFilas(){
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		
		int cod = Integer.parseInt(table.getValueAt(r, 1).toString());
		String nom = table.getValueAt(r, 2).toString();
		String email = table.getValueAt(r, 3).toString();

		boolean booc = (boolean) table.getValueAt(r, 0);
		System.out.println(booc+" "+cod);
	
			if(booc){
				nlst.add(new Cliente(cod, nom, email));
				
			}else{
				for(int i = 0; i < nlst.size(); i++){
					if(nlst.get(i).getIdCliente() == cod){
						nlst.remove(i);
					}
				}
			}
		}	
}
