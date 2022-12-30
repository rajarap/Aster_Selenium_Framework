package pageObjects.Aster;

import org.apache.hc.core5.http.nio.support.BasicClientExchangeHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpsResponse {

	public int getResponsecode(String endpointURL) throws MalformedURLException, IOException {

		// establish, open connection with URL

		HttpURLConnection cn = (HttpURLConnection) new URL(endpointURL)

				.openConnection();
		// set HEADER request
		cn.setRequestMethod("GET");
		// connection initiate
		cn.connect();
		// get response code
		int res = cn.getResponseCode();
		return res;

	}

	public String getResponsevalue(String endpointURL) throws MalformedURLException, IOException {

		// establish, open connection with URL

		HttpURLConnection cn = (HttpURLConnection) new URL(endpointURL)

				.openConnection();
		// set HEADER request
		cn.setRequestMethod("GET");
		// connection initiate
		cn.connect();
		// get response code
		String res = cn.getResponseMessage();

		return res;

	}

	public Writer getResponsebody(String endpointURL) throws MalformedURLException, IOException {
		Writer responsevalue = null;
		// establish, open connection with URL
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(endpointURL);

		try {
			client.executeMethod(method);

			if (method.getStatusCode() == HttpStatus.SC_OK) {
				InputStream is = method.getResponseBodyAsStream();

				if (is != null) {
					Writer writer = new StringWriter();
					char[] buffer = new char[1024];
					try {
						Reader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
						int length;
						while ((length = reader.read(buffer)) != -1) {
							writer.write(buffer, 0, length);
						}
					} finally {
						is.close();
					}
					responsevalue = writer;
					//String jsonString = responsevalue;
					
					return responsevalue;
				}
			}
		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			method.releaseConnection();
		}

		return responsevalue;

	}
}
