package ru.my.project;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.my.project.db.Account;
import ru.my.project.db.AccountDAO;
import ru.my.project.db.UserFioDAO;
import ru.my.project.db.UserGroupDAO;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8"); //для правильной кодировки полученных с формы данных в кириллице
		response.setCharacterEncoding("UTF-8");
		RequestDispatcher adminView = request.getRequestDispatcher("/admin.jsp");
		String login=request.getParameter("login");
		String pass=request.getParameter("password");
		String fio=request.getParameter("FIO");
		String group=request.getParameter("group");
		String email=request.getParameter("email");
		System.out.println("AddUser! Новый"+" "+login+" "+pass+" "+fio+" "+group+" "+email);
		Account newAccount=new Account(login, pass);
		long accountID=0;
		long groupID=0;
		long studentID=0;
		try {
			accountID =AccountDAO.addNewAccount(newAccount);
			groupID = UserGroupDAO.getId(group);
			studentID = UserFioDAO.addNewUserFio(fio, accountID, groupID, email);
			System.out.println("from servlet new studID = "+studentID);
			
			if (studentID>0) {
				SendEmail.sendEmail(email, fio, login, pass); //отправка письма новому пользователю
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(accountID);
		adminView.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
