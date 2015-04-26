package colin.awesome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SendHttpRequest {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://colin.coding.io/");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String str = "";
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
