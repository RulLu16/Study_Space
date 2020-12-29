package kr.ac.sogang.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sogang.java.TodoDao;
import kr.ac.sogang.java.TodoDto;

/**
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/TodoTypeServlet")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		TodoDao dao_n=new TodoDao();
		String type = request.getParameter("type");
		//System.out.println(type);
		String temp = request.getParameter("id");
		PrintWriter out = response.getWriter();
		//System.out.println(temp);
		int id=Integer.parseInt(temp);
		
		TodoDto dto_n=new TodoDto();
		if("Todo".equals(type)) {
			type="Doing";
		}
		else if("Doing".equals(type)) {
			type="Done";
		}
		dto_n.setId(id);
		dto_n.setType(type);
		
		dao_n.updateTodo(dto_n);
				
		/*List<TodoDto> dto=new ArrayList<>();
		TodoDao dao=new TodoDao();
		dto=dao.getTodos();*/
		
		/*String path = "/main.jsp";
		RequestDispatcher dipatcher = request.getRequestDispatcher(path);
		*/
		//request.setAttribute("todo", dto);
		//System.out.println("Dd");
		/*if("Doing".equals(type)) {
		out.println("<%list=(List<TodoDto>)request.getAttribute(\"todo\");");
		out.println("if(list!=null){	");	
		out.println("	for(int i=0;i<list.size();i++){");
		out.println("		dto=list.get(i);");
		out.println("		if(\"Doing\".equals(dto.getType())){ %>");
		out.println("			<li class=\"item_doing\" id=<%=dto.getId() %>> <h3><%=dto.getTitle()%></h3>");
		out.println("			Date : <%=dto.getRegDate()%> <%=dto.getName()%> Sequence : <%=dto.getSequence()%>");
		out.println("			<button class=\"moveList\" onclick=\"move('<%=dto.getType()%>', <%=dto.getId()%>)\"> -> </button>");
		out.println("			</li>");
		out.println("		<%}");
		out.println("	}");	
		out.println("}%>"); }
		else if("Done".equals(type)) {
			out.println("<%list=(List<TodoDto>)request.getAttribute(\"todo\");");
			out.println("if(list!=null){	");	
			out.println("	for(int i=0;i<list.size();i++){");
			out.println("		dto=list.get(i);");
			out.println("		if(\"Done\".equals(dto.getType())){ %>");
			out.println("			<li class=\"item_done\" id=<%=dto.getId() %>> <h3><%=dto.getTitle()%></h3>");
			out.println("			Date : <%=dto.getRegDate()%> <%=dto.getName()%> Sequence : <%=dto.getSequence()%>");
			out.println("			<button class=\"moveList\" onclick=\"move('<%=dto.getType()%>', <%=dto.getId()%>)\"> -> </button>");
			out.println("			</li>");
			out.println("		<%}");
			out.println("	}");	
			out.println("}%>"); 
			}*/
		//dipatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
