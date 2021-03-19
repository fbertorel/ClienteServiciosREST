import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CambioOutput {

	private Request req = new Request();

	private class GuiOutputStream extends OutputStream {
		JTextArea textArea;

		public GuiOutputStream(JTextArea textArea) {
			this.textArea = textArea;
		}

		@Override
		public void write(int data) throws IOException {
			textArea.append(new String(new byte[] { (byte) data }));
		}
	}

	public void guiResponse(String request, String texto, String body, HashMap<String, String> headerMap)
			throws RequestException {
		System.out.println("Test: Output normal");
		JTextArea textArea = new JTextArea();
		PrintStream stdout = System.out;
		stdout.println("Test: Nuevo output");
		GuiOutputStream rawout = new GuiOutputStream(textArea);
		System.setOut(new PrintStream(rawout, true));
		JFrame responseFrame = new JFrame("RESPONSE");
		responseFrame.add(new JScrollPane(textArea));
		responseFrame.setSize(500, 500);
		responseFrame.setVisible(true);

		switch (request) {
		case "PUT":
			if (body.isEmpty()) {
				try {
					req.put(texto, null, headerMap);
				} catch (ClienteException e) {
					throw new RequestException();
				}
			} else {
				try {
					req.put(texto, body, headerMap);
				} catch (ClienteException e) {
					throw new RequestException();
				}
			}
			break;
		case "POST":
			if (body.isEmpty()) {
				try {
					req.post(texto, null, headerMap);
				} catch (ClienteException e) {
					throw new RequestException();
				}
			} else {
				try {
					req.post(texto, body, headerMap);
				} catch (ClienteException e) {
					throw new RequestException();
				}
			}
			break;
		case "GET":
			try {
				req.get(texto, headerMap);
			} catch (ClienteException e) {
				throw new RequestException();
			}
			break;
		case "DEL":
			try {
				req.delete(texto, headerMap);
			} catch (ClienteException e) {
				throw new RequestException();
			}
		}
	}
}
