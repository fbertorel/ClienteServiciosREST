import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.UnsupportedEncodingException;
	import java.net.CookieHandler;
	import java.net.CookieManager;
	import java.net.HttpURLConnection;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;

	import org.apache.http.HttpResponse;
	import org.apache.http.NameValuePair;
	import org.apache.http.client.ClientProtocolException;
	import org.apache.http.client.HttpClient;
	import org.apache.http.client.entity.UrlEncodedFormEntity;
	import org.apache.http.client.methods.HttpGet;
	import org.apache.http.client.methods.HttpPost;
	import org.apache.http.impl.client.HttpClientBuilder;
	import org.apache.http.message.BasicNameValuePair;
	
	public class ClienteException extends Exception{
		
		public ClienteException() {
	    }

	    public ClienteException(String message) {
	        super(message);
	    }

	    public ClienteException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public ClienteException(Throwable cause) {
	        super(cause);
	    }
}
