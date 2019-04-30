package main;

import java.util.ArrayList;

public class Main {
  
	public static void main (String [] args) {

		System.out.println("WeatherStation!");
		URLManager urlManager = new URLManager("https://www.archlinux.org/");
		System.out.println(urlManager.read());

	}

}
