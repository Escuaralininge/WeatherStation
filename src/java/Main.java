package main;

public class Main {
  
	public static void main (String [] args) {

		startServer();

	}

	public static void startServer () {

		Server server = new Server(4040);
		server.start();

	}

}
