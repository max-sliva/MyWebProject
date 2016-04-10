package ru.my.project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.my.project.db.Account;
import ru.my.project.db.AccountDAO;
import ru.my.project.db.UserGroup;
import ru.my.project.db.UserGroupDAO;


/**
 * Servlet implementation class UsersList
 */
@WebServlet("/UsersList")
public class UsersList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		AccFIOgroupDAO accFIOgroupDAO=new AccFIOgroupDAO();
//        List<AccFIOgroup> accFIOgroupList = null;
        List<UserGroup> groups= null;
        List<Account> accounts= null;
        UserGroupDAO userGroupDAO= new UserGroupDAO();
        AccountDAO accDao = new AccountDAO();
		try {
//	        accFIOgroupList = accFIOgroupDAO.list();
//	        request.setAttribute("accFIOgroupList", accFIOgroupList); // Will be available as ${accFIOgroupList} in JSP
//	        System.out.println("acountsServlet="+accFIOgroupList.size());
//	        stGroups=studentsGroupDAO.list();
//	        request.setAttribute("groupList", stGroups); // Will be available as ${groupList} in JSP
	        accounts=accDao.list();
	        ArrayList<String> logins =new ArrayList<String>();
	        for (Account account : accounts) {
				logins.add(account.getLogin());
			} 
	        System.out.println("Accounts: "+accounts);
	        request.setAttribute("accounts", accounts); // Will be available as ${logins} in JSP
	        request.setAttribute("logins", logins); 
	        groups=userGroupDAO.list();
	        request.setAttribute("groupList", groups); // Will be available as ${groupList} in JSP

//	        request.getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);
	    } catch (SQLException e) {
	        throw new ServletException("Cannot obtain products from DB", e);
	    }
//		System.out.println(accFIOgroupList.get(0).getLogin()+" "+accFIOgroupList.get(1).getLogin());	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
