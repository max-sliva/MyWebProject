package ru.my.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.my.project.db.Authen;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8"); //для правильной кодировки 
		response.setCharacterEncoding("UTF-8"); //полученных с формы данных в кириллице
		RequestDispatcher view1 = request.getRequestDispatcher("/index.jsp");
		String login=request.getParameter("login");
		String pass=request.getParameter("pass");
		Authen authObject = new Authen();
        if (authObject.isLogin(login) && authObject.checkPassForLogin(login, pass)){
    		RequestDispatcher adminView = request.getRequestDispatcher("/admin.jsp");
			HttpSession session = request.getSession();
            session.setAttribute("user", login);
        	if (login.equals("Admin")) adminView.forward(request, response);
        	else {
              PrintWriter out= response.getWriter();
              out.println("<font color=green>Hello, "+login+"!</font>");
        	}
        } 
        else {
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            view1.include(request, response);
            System.out.println("Wrong!5!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
