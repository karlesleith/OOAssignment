package gmit.sw.client.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.Scanner;

public class Server {
	
	//Declaring Sockets
	private ServerSocket server;
	private Socket conn;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	Scanner sc = new Scanner(System.in);

	//default Constructor 
	public Server(){}
	
	//Set up and run the server
	public void startRunning() throws Exception{
		Boolean running = true;
		try{
			 server = new ServerSocket(2004,10);
			while(running){
				try{
					waitForConnection();
					setupStream();
					communication();
				}catch(EOFException eof){
					System.out.println("\n**ENDED THE CONNECTION**");
				}finally {
					closeConnection();
				}
				
			}
		}catch(IOException e){
			e.printStackTrace();
 		}
	}

	//Waiting for a connection, Show Connection IP
	private void waitForConnection() throws IOException{
		System.out.println("\n**Waiting for Connection...**");
		conn = server.accept();
		
		//Print Address of the Connected Socket and converted to String
		System.out.println("**Connected to "+conn.getInetAddress().getHostName()+"**");	
	}
	
	//Set up paths of communication between the computers
	private void setupStream() throws Exception{
		out = new ObjectOutputStream(conn.getOutputStream());
		//Flush the leftovers
		out.flush();
		in = new ObjectInputStream(conn.getInputStream());
	
		System.out.println("\n**Communication is now possible**");
	}
	//During the communication between computers
	private void communication()throws Exception{
		String message = "**You are Connected to the Server**";
		sendMessage(message);
		
		do{
			try{
				message = (String)in.readObject();
				System.out.println(message);
				
				if(message.equals("Client >2")){
					XMLParser xml = new XMLParser();
					
					xml.file();
					
				}
				
				
			}catch(ClassNotFoundException c){
				System.out.println("**Invalid format recieved from client**");
			}
		}while(!message.equals("Client >3"));
		
		System.out.println("**Programe has Shut Down**");
	
	}
	//Send message to client
	 private void sendMessage(String msg)
		{
			try{
				out.writeObject("Server > "+msg);
				out.flush();
				System.out.println("\nServer > " + msg);
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	  private void closeConnection() throws Exception{
		  System.out.println("**Closing Connection...**");
		  try{
			  in.close();
			  out.close();
			  conn.close();
		  }catch(IOException ioe){
			  ioe.printStackTrace();
		  }
		  System.out.println("**Connection Closed**");
	  }
	  
		public String menu(){
			String menu = "Please Select Your Option "
					+ "1,2,3,\n1.Print File\n2.Download File**NOT WORKING**\n3.Quit\n";
			return menu;
		}//End UI
}
