package kr.ac.sogang.serv;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sogang.java.*;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/TodoAddServlet")
public class TodoAddServlet extends HttpServlet { //use tododao to make new todo
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		TodoDto new_dto=new TodoDto();
		TodoDao dao=new TodoDao();
		
		new_dto.setTitle(request.getParameter("title"));
		new_dto.setSequence(Integer.parseInt(request.getParameter("sequence")));
		new_dto.setName(request.getParameter("name"));
		new_dto.setType("Todo");
		
		dao.addTodo(new_dto);
		
		//dto=dao.getTodos();
		
		String path = "/MainServlet";
		/*RequestDispatcher dipatcher = request.getRequestDispatcher(path);
		
		request.setAttribute("todo", dto);
		dipatcher(request, response);*/
		response.sendRedirect(request.getContextPath()+path);
		
	}

}
