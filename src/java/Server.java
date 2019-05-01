package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
       			printWriter.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
       			printWriter.write("Server: Apache/0.8.4\r\n");
       			printWriter.write("Content-Type: text/html\r\n");
       			printWriter.write("Content-Length: 59\r\n");
       			printWriter.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
        		printWriter.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
        		printWriter.write("\r\n");

				FileManager fileManager = new FileManager(
					"/home/miguel/Git/WeatherStation/html-header.txt");
				ArrayList<String> arrayList = fileManager.readFile();
				for (String requestLine: arrayList) {
					System.out.println(requestLine);
					printWriter.write(requestLine);
				}

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
