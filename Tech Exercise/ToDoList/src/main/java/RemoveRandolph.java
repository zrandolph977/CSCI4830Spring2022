

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveRandolph
 */
@WebServlet("/RemoveRandolph")
public class RemoveRandolph extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveRandolph() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Connection connection = null;
		
		PrintWriter out = response.getWriter();
		String task = null;
		String priority = null;
		String removeSql = " DELETE FROM list WHERE id = ?";
		try {
			DBConnectionRandolph.getDBConnection();
			connection = DBConnectionRandolph.connection;
			PreparedStatement preparedStmt = connection.prepareStatement(removeSql);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
		String title = "Chore removed from list! Here's what's left.";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + //
			"<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n"//
            );
		PreparedStatement preparedStatement = null;
		try {
			DBConnectionRandolph.getDBConnection();
			connection = DBConnectionRandolph.connection;
			String selectSQL = "SELECT * FROM list";
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("id");
				task = rs.getString("task").trim();
				priority = rs.getString("priority").trim();
				out.println("ID: " + ID + ", ");
				out.println("Task: " + task + ", ");
				out.println("Priority: " + priority + "<br>");
			}
			connection.close();
         }catch (SQLException se) {
             se.printStackTrace();
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {
               if (preparedStatement != null)
                  preparedStatement.close();
            } catch (SQLException se2) {
            }
            try {
               if (connection != null)
                  connection.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
         }
		out.println("<a href=/ToDoList/search_Randolph.html>Search Chores</a> <br>");
		out.println("</body></html>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
