package ru.my.project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserGroupDAO {

    public List<UserGroup> list() throws SQLException {
        List<UserGroup> studentsGroups = new ArrayList<UserGroup>();
       	try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//           Connection connection = DriverManager.getConnection("jdbc:h2:file://d:/javaEEprojects2/GIS_site/gisPortal","sa","");
       	Connection connection=DBconnect.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT id, groupname FROM usergroup");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
        	UserGroup group = new UserGroup();
        	group.setId(resultSet.getLong("id"));
        	group.setGroupName(resultSet.getString("groupname"));
           	studentsGroups.add(group);
        }
        connection.close();    
        return studentsGroups;
    }
    
    public static long getId(String groupName) throws SQLException{
    	long id=0;
    	Connection connection=DBconnect.getConnection();
    	PreparedStatement statement = connection.prepareStatement("SELECT id FROM usergroup where groupname='"+groupName+"'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        id=resultSet.getLong("id");
        System.out.println("group id="+id);
        connection.close();
    	return id; 
    }
}