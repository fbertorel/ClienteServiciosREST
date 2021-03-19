import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class PanelMenuFinal {
	JTextField url = new JTextField(20);
	JTextField body;

	public PanelMenuFinal() {

	}

	public void menu() {
		JFrame menu = new JFrame("Menu");
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());

		JPanel aux = new JPanel();
		aux.setLayout(new FlowLayout());
		JButton get = new JButton("GET");
		JButton put = new JButton("PUT");
		JButton post = new JButton("POST");
		JButton delete = new JButton("DELETE");
		aux.add(get);
		aux.add(put);
		aux.add(post);
		aux.add(delete);

		JPanel aux2 = new JPanel();
		aux2.setLayout(new FlowLayout());
		JLabel labelNombre = new JLabel("URL: ");
		aux2.add(labelNombre);
		aux2.add(url);
		panelPrincipal.add(aux2);

		JPanel headerPanel = new JPanel();
		JLabel labelHeader = new JLabel("HEADERS");
		JButton favorito = new JButton("FAVORITOS");
		headerPanel.add(favorito, BorderLayout.NORTH);

		favorito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelFavorito favorito = new PanelFavorito();
			}
		});

		headerPanel.add(labelHeader);

		JPanel headerInputPanel = new JPanel();

		JTextField header1 = new JTextField(40);
		JTextField header2 = new JTextField(40);
		JTextField header3 = new JTextField(40);
		JTextField header4 = new JTextField(40);
		JTextField header5 = new JTextField(40);
		JTextField header6 = new JTextField(40);
		JTextField header7 = new JTextField(40);
		JTextField header8 = new JTextField(40);

		headerInputPanel.setLayout(new GridLayout(0, 2));
		headerInputPanel.add(header1);
		headerInputPanel.add(header2);
		headerInputPanel.add(header3);
		headerInputPanel.add(header4);
		headerInputPanel.add(header5);
		headerInputPanel.add(header6);
		headerInputPanel.add(header7);
		headerInputPanel.add(header8);

		get.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambioOutput method = new CambioOutput();

				HashMap<String, String> headerMapGet = new HashMap<String, String>();

				headerMapGet.put(header1.getText(), header2.getText());
				headerMapGet.put(header3.getText(), header4.getText());
				headerMapGet.put(header5.getText(), header6.getText());
				headerMapGet.put(header7.getText(), header8.getText());

				try {
					method.guiResponse("GET", url.getText(), null, headerMapGet);
				} catch (RequestException e1) {
					e1.printStackTrace();
				}

			}
		});

		post.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				HashMap<String, String> headerMapPost = new HashMap<String, String>();

				headerMapPost.put(header1.getText(), header2.getText());
				headerMapPost.put(header3.getText(), header4.getText());
				headerMapPost.put(header5.getText(), header6.getText());
				headerMapPost.put(header7.getText(), header8.getText());

				if (url.getText().contains("&")) {

					CambioOutput method = new CambioOutput();

					try {
						method.guiResponse("POST", url.getText(), "", headerMapPost);
					} catch (RequestException e1) {
						e1.printStackTrace();
					}
				} else {
					PanelBody p = new PanelBody("POST", url.getText(), headerMapPost);
				}

			}
		});

		put.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> headerMapPut = new HashMap<String, String>();

				headerMapPut.put(header1.getText(), header2.getText());
				headerMapPut.put(header3.getText(), header4.getText());
				headerMapPut.put(header5.getText(), header6.getText());
				headerMapPut.put(header7.getText(), header8.getText());

				if (url.getText().contains("&")) {

					CambioOutput method = new CambioOutput();

					try {
						method.guiResponse("PUT", url.getText(), "", headerMapPut);
					} catch (RequestException e1) {
						e1.printStackTrace();
					}
				} else {
					PanelBody p = new PanelBody("PUT", url.getText(), headerMapPut);
				}
			}

		});

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambioOutput method = new CambioOutput();

				HashMap<String, String> headerMapDel = new HashMap<String, String>();

				headerMapDel.put(header1.getText(), header2.getText());
				headerMapDel.put(header3.getText(), header4.getText());
				headerMapDel.put(header5.getText(), header6.getText());
				headerMapDel.put(header7.getText(), header8.getText());

				try {
					method.guiResponse("DEL", url.getText(), null, headerMapDel);
				} catch (RequestException e1) {
					e1.printStackTrace();
				}

			}
		});

		panelPrincipal.add(aux, BorderLayout.NORTH);
		aux.add(aux2, BorderLayout.SOUTH);
		headerPanel.add(headerInputPanel, BorderLayout.SOUTH);
		panelPrincipal.add(headerPanel, BorderLayout.CENTER);
		menu.getContentPane().add(panelPrincipal);
		menu.pack();
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		menu.setSize(900, 300);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/* SOBRECARGA EN CASO DE USAR FAVORITOS*/

	public void menu(String url) {
		JFrame menu = new JFrame("Menu");
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());

		JPanel aux = new JPanel();
		aux.setLayout(new FlowLayout());
		JButton get = new JButton("GET");
		JButton put = new JButton("PUT");
		JButton post = new JButton("POST");
		JButton delete = new JButton("DELETE");
		aux.add(get);
		aux.add(put);
		aux.add(post);
		aux.add(delete);

		JPanel aux2 = new JPanel();
		aux2.setLayout(new FlowLayout());
		JLabel labelNombre = new JLabel("URL: " + url);
		aux2.add(labelNombre);
		panelPrincipal.add(aux2);

		JPanel headerPanel = new JPanel();
		JLabel labelHeader = new JLabel("HEADERS");
		headerPanel.add(labelHeader);

		JPanel headerInputPanel = new JPanel();
		
		JTextField header1 = new JTextField(40);
		JTextField header2 = new JTextField(40);
		JTextField header3 = new JTextField(40);
		JTextField header4 = new JTextField(40);
		JTextField header5 = new JTextField(40);
		JTextField header6 = new JTextField(40);
		JTextField header7 = new JTextField(40);
		JTextField header8 = new JTextField(40);

		headerInputPanel.setLayout(new GridLayout(0, 2));
		headerInputPanel.add(header1);
		headerInputPanel.add(header2);
		headerInputPanel.add(header3);
		headerInputPanel.add(header4);
		headerInputPanel.add(header5);
		headerInputPanel.add(header6);
		headerInputPanel.add(header7);
		headerInputPanel.add(header8);

		get.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambioOutput method = new CambioOutput();

				HashMap<String, String> headerMapGet = new HashMap<String, String>();

				headerMapGet.put(header1.getText(), header2.getText());
				headerMapGet.put(header3.getText(), header4.getText());
				headerMapGet.put(header5.getText(), header6.getText());
				headerMapGet.put(header7.getText(), header8.getText());

				try {
					method.guiResponse("GET", url, null, headerMapGet);
				} catch (RequestException e1) {
					e1.printStackTrace();
				}

			}
		});

		post.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				HashMap<String, String> headerMapPost = new HashMap<String, String>();

				headerMapPost.put(header1.getText(), header2.getText());
				headerMapPost.put(header3.getText(), header4.getText());
				headerMapPost.put(header5.getText(), header6.getText());
				headerMapPost.put(header7.getText(), header8.getText());

				if (url.contains("?")) {

					CambioOutput method = new CambioOutput();

					try {
						method.guiResponse("POST", url, "", headerMapPost);
					} catch (RequestException e1) {
							e1.printStackTrace();
					}
				} else {
					PanelBody p = new PanelBody("PUT", url, headerMapPost);
				}

			}
		});

		put.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> headerMapPut = new HashMap<String, String>();

				headerMapPut.put(header1.getText(), header2.getText());
				headerMapPut.put(header3.getText(), header4.getText());
				headerMapPut.put(header5.getText(), header6.getText());
				headerMapPut.put(header7.getText(), header8.getText());

				if (url.contains("?")) {

					CambioOutput method = new CambioOutput();

					try {
						method.guiResponse("PUT", url, "", headerMapPut);
					} catch (RequestException e1) {
						e1.printStackTrace();
					}
				} else {
					PanelBody p = new PanelBody("PUT", url, headerMapPut);
				}
			}

		});

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambioOutput method = new CambioOutput();

				HashMap<String, String> headerMapDel = new HashMap<String, String>();

				headerMapDel.put(header1.getText(), header2.getText());
				headerMapDel.put(header3.getText(), header4.getText());
				headerMapDel.put(header5.getText(), header6.getText());
				headerMapDel.put(header7.getText(), header8.getText());

				try {
					method.guiResponse("DEL", url, null, headerMapDel);
				} catch (RequestException e1) {
						e1.printStackTrace();
				}

			}
		});

		panelPrincipal.add(aux, BorderLayout.NORTH);
		aux.add(aux2, BorderLayout.SOUTH);
		headerPanel.add(headerInputPanel, BorderLayout.SOUTH);
		panelPrincipal.add(headerPanel, BorderLayout.CENTER);
		menu.getContentPane().add(panelPrincipal);
		menu.pack();
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
		menu.setSize(900, 300);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
