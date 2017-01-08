package gmit.sw.client;

import java.util.Scanner;
import gmit.sw.client.config.*;
public class Runner {

	public static void main(String[] args) {
	
		//Creating new Object of UI
		UI ui = new UI();
		String menu = ui.menu();
		int choice=0;
		String msg;
		boolean running = true;
		Scanner in = new Scanner(System.in);
		Connection conn = new Connection();
		do{
			System.out.println(menu);
			choice = in.nextInt();
			
				if(choice == 1){
					
					msg =Integer.toString(choice);
					conn.run(msg);
					running = false;
					
				}
				else if(choice == 2){
					System.out.println("*Print File*");
					
					System.out.println("\n**XML**\n");
					XMLParser xml = new XMLParser();
					xml.file();
					
					msg =Integer.toString(choice);
					conn.run(msg);
					
					running = false;
				}
				else if(choice == 3){
					System.out.println("**Quiting**");
					running = false;
				}
				else{
					
					System.out.println("**Invalid Option**\n");
				}
			
		}while(running);

		System.out.println("\nEND");
	}

}
