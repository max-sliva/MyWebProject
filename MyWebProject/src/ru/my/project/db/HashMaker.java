package ru.my.project.db;

import java.util.Scanner;

public class HashMaker {

	public static void  main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.print("Input pass: "); 
		String pass=in.nextLine();		  
		String passHash=Authen.getHash(pass);
		System.out.println("Hash for pass "+pass+ " is: "+passHash);
	}

}
