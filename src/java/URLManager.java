package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;
import java.util.ArrayList;

public class URLManager {

	private URL url;

	public URLManager () {}
	
	public URLManager (String path) {
		setURL(path);
	}	

	public void setURL (String path) {
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> read () {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(
											new InputStreamReader(
											urlConnection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				arrayList.add(line + '\n');
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

}
