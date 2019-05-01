package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	private Thread thread;
	private int port;
	private boolean active;

	public Server (int port) {
		thread = new Thread(this, "ServerThread");
		this.port = port;
	}

	@Override
	public void run () {
		while (true){
			try (
				ServerSocket serverSocket = new ServerSocket(port);
				Socket socket = serverSocket.accept();
				BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(
							socket.getInputStream()));
				PrintWriter printWriter = new PrintWriter(
							socket.getOutputStream()); 
			) {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
					if (line.isEmpty()) break;
				}

				printWriter.write("HTTP/1.0 200 OK\r\n");
        		printWriter.write("<TITLE>WeatherStation</TITLE>");
        		printWriter.write("<P>HolaMundo.</P>");

			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void start () {
		thread.start();
		active = true;
	}

	public void stop () {
		active = false;
	}

	public boolean isRunning () {
		return active;
	}
	
}
