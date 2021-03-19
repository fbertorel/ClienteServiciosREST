import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

public class Request {
	public Request() {

	}

	public void get(String url, HashMap<String, String> headerMap) throws ClienteException {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);

		for (String key : headerMap.keySet()) {
			httpGet.setHeader(key, headerMap.get(key));
		}

		System.out.println("MOSTRANDO HEADERS REQUEST\n");
		for (Header header : httpGet.getAllHeaders()) {
			if (!header.getName().isEmpty()) {
				System.out.println(header.getName() + " = " + header.getValue()); // muestro headers de la request
			}
		}

		HttpResponse response;
		try {
			response = client.execute(httpGet);
		} catch (IOException e) {
			throw new ClienteException();
		}
		System.out.println("Request type: " + httpGet.getMethod() + "\nResponse Code: "
				+ response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() == 404) {
			System.out.println("ERROR 404");
		}
		Scanner sc;
		try {
			sc = new Scanner(response.getEntity().getContent());
		} catch (UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			throw new ClienteException();
		}
		while (sc.hasNext()) {
			System.out.println(sc.nextLine());
		}
		System.out.println("Response Headers: \n"); // imprimo headers de la response
		for (Header header : response.getAllHeaders()) {
			System.out.println(header.getName() + " = " + header.getValue());
		}
	}

	public void post(String url, String body, HashMap<String, String> headerMap) throws ClienteException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);

		for (String key : headerMap.keySet()) {
			httpPost.setHeader(key, headerMap.get(key));
		}

		System.out.println("MOSTRANDO HEADERS REQUEST\n");
		for (Header header : httpPost.getAllHeaders()) {
			if (!header.getName().isEmpty()) {
				System.out.println(header.getName() + " = " + header.getValue()); // muestro headers de la request
			}
		}

		if (body == null) { // esto es para cuando paso los parametros por querystring en vez de por body
			List<NameValuePair> params;
			try {
				params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));
			} catch (URISyntaxException e) {
				throw new ClienteException();
			}
			for (NameValuePair param : params) { // PARSEO LOS PARAMETROS PASADOS EN LA URL.
				try {
					httpPost.setEntity(new UrlEncodedFormEntity(params));
				} catch (UnsupportedEncodingException e) {
					throw new ClienteException();
				}
			}
		} else { // esto es para cuando paso el body
			StringEntity stringEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
			httpPost.setEntity(stringEntity);
		}

		HttpResponse response;
		try {
			response = client.execute(httpPost);
		} catch (IOException e) {
			throw new ClienteException();
		}
		System.out.println("Request type: " + httpPost.getMethod() + "\nResponse Code: "
				+ response.getStatusLine().getStatusCode());
		BufferedReader rd;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (UnsupportedOperationException | IOException e) {
			throw new ClienteException();
		}
		String line = "";
		System.out.println("BODY DE LA REQUEST: \n");
		try {
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			throw new ClienteException();
		}
		System.out.println("Response Headers: \n"); // imprimo headers de la response
		for (Header header : response.getAllHeaders()) {
			System.out.println(header.getName() + " = " + header.getValue());
		}
	}

	public void delete(String url, HashMap<String, String> headerMap) throws ClienteException {
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpDelete httpDelete = new HttpDelete(url);

		for (String key : headerMap.keySet()) {
			httpDelete.setHeader(key, headerMap.get(key));
		}

		System.out.println("MOSTRANDO HEADERS REQUEST\n");
		for (Header header : httpDelete.getAllHeaders()) {
			if (!header.getName().isEmpty()) {
				System.out.println(header.getName() + " = " + header.getValue()); // muestro headers de la request
			}
		}

		String urlNueva = "";
		List<NameValuePair> params;
		try {
			params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));
		} catch (URISyntaxException e1) {
			throw new ClienteException();
		}

		for (NameValuePair param : params) {
			if (param.getName().equals("id")) {
				urlNueva = urlParseo(url);
				urlNueva = urlNueva + "/" + param.getValue();
				try {
					httpDelete.setURI(new URI(urlNueva));
				} catch (URISyntaxException e) {
					throw new ClienteException();
				}
			}
		}
		HttpResponse response;
		try {
			response = httpclient.execute(httpDelete);
		} catch (IOException e) {
			throw new ClienteException();
		}
		System.out.println("\nRequest type: " + httpDelete.getMethod() + "\nResponse Code: "
				+ response.getStatusLine().getStatusCode());

		System.out.println("Response Headers: \n"); // imprimo headers de la response
		for (Header header : response.getAllHeaders()) {
			System.out.println(header.getName() + " = " + header.getValue());
		}
	}

	public void put(String url, String body, HashMap<String, String> headerMap) throws ClienteException {
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPut httpPut = new HttpPut(url);
		
		for (String key : headerMap.keySet()) {
			httpPut.setHeader(key, headerMap.get(key));
		}

		System.out.println("MOSTRANDO HEADERS REQUEST\n");
		for (Header header : httpPut.getAllHeaders()) {
			if (!header.getName().isEmpty()) {
				System.out.println(header.getName() + " = " + header.getValue()); // muestro headers de la request
			}
		}

		String urlNueva = "";
		String idUrl = "";
		JSONObject jsonObject = new JSONObject();

		// esto es para cuando paso los parametros por querystring en vez de por body
		List<NameValuePair> params;
		try {
			params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));
		} catch (URISyntaxException e) {
			throw new ClienteException();
		}
		for (NameValuePair param : params) {
			jsonObject.put(param.getName(), param.getValue());
			if (param.getName().equals("id")) {
				idUrl = param.getValue();
				urlNueva = urlParseo(url);
				urlNueva = urlNueva + "/" + param.getValue();
				try {
					httpPut.setURI(new URI(urlNueva));
				} catch (URISyntaxException e) {
					throw new ClienteException();
				}
			}
		}
		if (body == null) {

			System.out.println("\nBODY DE LA REQUEST: \n");
			System.out.println(jsonObject.toJSONString());
			StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), ContentType.APPLICATION_JSON);
			httpPut.setEntity(stringEntity);
		} else {
			System.out.println("BODY DE LA REQUEST: \n" + body);
			StringEntity stringEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
			httpPut.setEntity(stringEntity);
		}

		ResponseHandler<String> responseHandler = response -> {
			int status = response.getStatusLine().getStatusCode();
			System.out.println("\nRequest type: " + httpPut.getMethod() + "\nResponse Code: "
					+ status);
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		};

		String responseBody;
		try {
			responseBody = httpclient.execute(httpPut, responseHandler);
		} catch (IOException e) {
			throw new ClienteException();
		}
		System.out.println("\nResponse: ");
		System.out.println(responseBody);
	}

	public String urlParseo(String url) {
		String[] urlSola = url.split("\\?");
		return urlSola[0];
	}

} // cierre clase
