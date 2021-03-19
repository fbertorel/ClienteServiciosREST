import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PanelFavorito {
	private PanelMenuFinal preselec;
	
	public PanelFavorito() {
		favorito();
	}

	public void favorito() {
		JFrame cuadro = new JFrame("Favoritos");
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

		JPanel aux = new JPanel();
		aux.setLayout(new FlowLayout());

		JRadioButton fav1 = new JRadioButton();
		fav1.setText("http://localhost:3000/users/25");

		JRadioButton fav2 = new JRadioButton();
		fav2.setText("http://localhost:3000/futbol?dorsal=10");

		JRadioButton fav3 = new JRadioButton();
		fav3.setText("http://localhost:3000/users");

		JRadioButton fav4 = new JRadioButton();
		fav4.setText("http://localhost:3000/futbol?id=3");

		JRadioButton fav5 = new JRadioButton();
		fav5.setText("http://localhost:3000/futbol");

		ButtonGroup bg = new ButtonGroup();
		bg.add(fav1);
		bg.add(fav2);
		bg.add(fav3);
		bg.add(fav4);
		bg.add(fav5);

		aux.add(fav1);
		aux.add(fav2);
		aux.add(fav3);
		aux.add(fav4);
		aux.add(fav5);

		JPanel aux2 = new JPanel();
		JButton enviarRequest = new JButton("Aceptar");
		aux2.add(enviarRequest, BorderLayout.SOUTH);

		enviarRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fav1.isSelected()) {
					preselec = new PanelMenuFinal();
					preselec.menu(fav1.getText());
				}
				if (fav2.isSelected()) {
					preselec = new PanelMenuFinal();
					preselec.menu(fav2.getText());
				}
				if (fav3.isSelected()) {
					preselec = new PanelMenuFinal();
					preselec.menu(fav3.getText());
				}
				if (fav4.isSelected()) {
					preselec = new PanelMenuFinal();
					preselec.menu(fav4.getText());
				}
				if (fav5.isSelected()) {
					preselec = new PanelMenuFinal();
					preselec.menu(fav5.getText());
				}
			}
		});

		panelPrincipal.add(aux);
		panelPrincipal.add(aux2);
		cuadro.getContentPane().add(panelPrincipal);
		cuadro.pack();
		cuadro.setVisible(true);
		cuadro.setSize(400, 400);
		cuadro.setLocationRelativeTo(null);
		cuadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
