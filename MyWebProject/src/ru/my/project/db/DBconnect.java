package ru.my.project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBconnect {

	public static Connection getConnection() {
//		String dbPath="jdbc:h2:file://F:\\JavaEEprojects\\MyWebProject\\portal";
		String dbPath="jdbc:h2:file://G:\\javaEEprojects8\\MyWebProject\\portal";
//		String dbPath="jdbc:h2:file://D:\\javaEEprojects8\\MyWebProject\\portal";
//		String dbPath="jdbc:h2:file://D:\\javaEEprojects2\\MyWebProject\\portal";
    	try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Trouble with connection!");
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbPath,"sa","");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

}
