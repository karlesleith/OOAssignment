package gmit.sw.client;

import java.io.*;
import java.util.Scanner;

//UI class that displays the menus to the user
public class UI {

	public String menu(){
		String menu = "Please Select Your Option "
				+ "1,2,3\n1.Connect To Server\n2.Print File\n3.Quit\n";
		return menu;
	}//End UI
}