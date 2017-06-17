package UI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Controllers.Observer;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;


public class LoginWindow extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCourrielUtilisateur;
	private JTextField txtMotDePasseUtilisateur;
	private JButton btnConnexion;
	private JLabel lblValiditeDeConnexion;

	public void addController (ActionListener controller){
		btnConnexion.addActionListener(controller);
	}	
	
	public void update(String str) {
		Color couleurValiditeConnexion = null;
		String messageValiditeConnexion = "";
		
		if(str == null){
			couleurValiditeConnexion = new Color(204, 0, 0);
			messageValiditeConnexion = "Erreur de connexion.";
			lblValiditeDeConnexion.setText(messageValiditeConnexion);
			lblValiditeDeConnexion.setForeground(couleurValiditeConnexion);
		}else if(str != null){
			couleurValiditeConnexion = new Color(0, 102, 51);
			messageValiditeConnexion = "Bonjour "+ str + " !";
			SearchWindow frame = new SearchWindow();
			frame.setTitle("LOG660 - Connected As: " + str);
			frame.setVisible(true);
			this.dispose();
		}		
	} 


	/**
	 * Create the login frame.
	 */
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnConnexion = new JButton("Connexion");
				
		panel.add(btnConnexion);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNomDutilisateur = new JLabel("Adresse courriel:");
		panel_1.add(lblNomDutilisateur, "2, 2, right, default");
		
		txtCourrielUtilisateur = new JTextField();
		txtCourrielUtilisateur.setText("WilmaSHuff27@hotmail.com");
		panel_1.add(txtCourrielUtilisateur, "4, 2, fill, default");
		txtCourrielUtilisateur.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe:");
		panel_1.add(lblMotDePasse, "2, 4, right, default");
		
		txtMotDePasseUtilisateur = new JTextField();
		txtMotDePasseUtilisateur.setText("Thi0ruimie");
		panel_1.add(txtMotDePasseUtilisateur, "4, 4, fill, default");
		txtMotDePasseUtilisateur.setColumns(10);
		
		lblValiditeDeConnexion = new JLabel("Connexion");
		lblValiditeDeConnexion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValiditeDeConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblValiditeDeConnexion, BorderLayout.NORTH);

	}
	 
	public JTextField getTxtCourrielUtilisateur(){
    	return txtCourrielUtilisateur;
	}

	public JTextField getTxtMotDePasseUtilisateur() {
		return txtMotDePasseUtilisateur;
	}

}
