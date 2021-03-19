import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import com.google.gson.Gson;

/**
 * Franco Bertorello 
 * Date: 22-12-2020
 * Final Laboratorio I - UP
 */
public class MainProgram {
	
	public static void main(String[] args) {
		
		PanelMenuFinal panel = new PanelMenuFinal();
		panel.menu();
	}

}
