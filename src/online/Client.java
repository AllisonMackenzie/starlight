package online;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client 
{
	private static Socket socket;
	private static PrintWriter printWriter;
	private static int port = 21215;
	private static String IP = "localhost";
	
	public Client()
	{
		try{
			socket = new Socket(IP, port);
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			printWriter.println("Hey");
			printWriter.println("Hoe");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getIP()
	{
		return IP;
	}
	
	public static void changeIP(String newIP)
	{
		IP = newIP;
		try{
			socket = new Socket(IP, port);
			printWriter = new PrintWriter(socket.getOutputStream(), true);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
