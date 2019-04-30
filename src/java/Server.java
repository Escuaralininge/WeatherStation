package main;

import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Server implements Runnable {

	private Thread thread;
	private int port;
	private boolean active;

	public Server (int port) {
		thread = new Thread(this, "ServerThread");
		this.port = port;
	}

	public void start () {
		thread.start();
		active = true;
	}

	@Override
	public void run () {
		while (true) {
			try (
				ServerSocket serverSocket = new ServerSocket(port);
				Socket socket = serverSocket.accept();
				BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(
							socket.getInputStream()));
				PrintWriter printWriter = new PrintWriter(
							socket.getOutputStream());
			) {
				String string;
				while ((string = bufferedReader.readLine()) != null) {
					System.out.println(string);
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop () {
		active = false;
	}

	public boolean isRunning () {
		return active;
	}
	
}
