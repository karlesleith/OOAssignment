package gmit.sw.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String inMsg;
 	Scanner sc = new Scanner(System.in);
 	
 	Connection(){
 		//Default
 	}
 	
 	void run(String message){
	 	try{
	 		requestSocket = new Socket("127.0.0.1",2004);
			System.out.println("Connected to Local host in port 2004");
			
			//Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			do{
				try
				{
						inMsg = (String)in.readObject();
						System.out.println(inMsg);
						sendMessage(message);
	
				}
				catch(ClassNotFoundException classNot)
				{
					System.err.println("data received in unknown format");
				}
			}while(!message.equals("4"));
			
	 	}catch(Exception e)
	 	{
	 		
	 		System.out.println(e.getMessage());
	 	}
 	}//End of run
 	
 	//when a choice is made you send the choice to the server
 	void sendMessage(String option)
 	{
 		try{
 			out.writeObject("Client >"+option);
 			out.flush();
 			System.out.println("Client >"+ option);
 		}
 		catch(IOException ioException){
 			ioException.printStackTrace();
 		}
 	}
 	private void closeConnection()throws Exception{
 		  System.out.println("**Closing Connection...**");
		  try{
			  in.close();
			  out.close();
		  }catch(IOException ioe){
			  ioe.printStackTrace();
		  }
		  System.out.println("**Connection Closed**");
 	}
}

