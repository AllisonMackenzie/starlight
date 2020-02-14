package online;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread
{
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static BufferedReader br;
	private static String inputLine;
	private int port = 21215;
	
	public Server()
	{
		try{
			serverSocket = new ServerSocket(port);
			clientSocket = serverSocket.accept();
			br = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			
			while((inputLine = br.readLine()) != null){
				System.out.println(inputLine);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
