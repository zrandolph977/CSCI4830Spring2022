

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertRandolph")
public class InsertRandolph extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertRandolph() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String task = request.getParameter("task");
      String priority = request.getParameter("priority");

      Connection connection = null;
      String insertSql = " INSERT INTO list (id, TASK, PRIORITY) values (default, ?, ?)";

      try {
         DBConnectionRandolph.getDBConnection();
         connection = DBConnectionRandolph.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, task);
         if(!priority.equals("High") || !priority.equals("Low")) {
        	 priority = "Low";
         }
         preparedStmt.setString(2, priority);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Chore Added to list!";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Task</b>: " + task + "\n" + //
            "  <li><b>Priority</b>: " + priority + "\n" + //

            "</ul>\n");

      out.println("<a href=/ToDoList/search_Randolph.html>View Chores</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}