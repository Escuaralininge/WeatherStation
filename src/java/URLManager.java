package main;

import java.io.*;
import java.net.*;
import java.util.*;

public class URLManager {

	private URL url;

	public URLManager () {}
	
	public URLManager (String path) {
		setURL(path);
	}	

	public void setURL (String path) {
		try {
			this.url = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> read () {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String string;
			while ((string = bufferedReader.readLine()) != null) {
				arrayList.add(string + '\n');
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

}
