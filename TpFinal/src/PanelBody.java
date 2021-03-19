import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.swing.*;

public class PanelBody {

	private JTextField bodyRequest;

	public PanelBody(String requestType, String url, HashMap<String, String> headerMap) {

		JFrame cuadro = new JFrame("URL: " + url);
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

		JPanel aux = new JPanel();
		aux.setLayout(new FlowLayout());
		JLabel labelNombre = new JLabel("BODY: ");
		aux.add(labelNombre);
		bodyRequest = new JTextField(50);
		bodyRequest.setPreferredSize(new Dimension(200, 200));
		aux.add(bodyRequest);

		JButton enviarRequest = new JButton("Enviar Request");
		aux.add(enviarRequest);
		panelPrincipal.add(aux);

		JPanel aux3 = new JPanel();

		enviarRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambioOutput request = new CambioOutput();

				try {
					request.guiResponse(requestType, url, bodyRequest.getText(), headerMap);
				} catch (RequestException e1) {
				}

			}
		});

		panelPrincipal.add(aux, BorderLayout.NORTH);
		panelPrincipal.add(aux3, BorderLayout.SOUTH);
		cuadro.getContentPane().add(panelPrincipal);
		cuadro.pack();
		cuadro.setVisible(true);
		cuadro.setSize(800, 800);
		cuadro.setLocationRelativeTo(null);
		cuadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
