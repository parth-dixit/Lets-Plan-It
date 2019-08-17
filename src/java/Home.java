

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Parth Chutiyo
 */
@WebServlet(urlPatterns = {"/Home"})
public class Home extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        if(session.getAttribute("login") == "logged")
        {
             try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("Search Groups");
           out.println(" <form action='search' method='post'>");
           out.println("<input type='text' name='search' value='search'><br/>");
           out.println("<input type='submit' name='submit' value='submit'>");
           out.println("</form>");
           out.println("<br/><br/><br/>");
           out.println("<a href='newgroup.jsp'>Create New Group</a>");
           out.println("<br/><br/><br/>");

           String jdbc_driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:derby://localhost:1527/Let_Plan";
            String user="Parth";
            String pass="parth";
            ServletContext context = getServletContext();
            context.setAttribute("signup","3");

            
            try
            {
                   String user1 = request.getParameter("user1");
                   String pass1 = request.getParameter("pass");
                Connection con = DriverManager.getConnection(url,user,pass);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from homegroup");
                int i=1;
                out.println(" <form action='groupdescript' method='post'>");
                while(rs.next())
                {
                    out.println(rs.getString(1)+" : "+rs.getString(2)+"  Admin - "+rs.getString(6)+"  &nbsp;&nbsp;&nbsp;<input type='submit' name='select"+i+"' value='select'><br/>");
                    //session.setAttribute("group",rs.getString(1));
                    i++;
                }
                out.println("</form>");
                session.setAttribute("group",rs.getString(1));
                con.close();
            
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            out.println("</body>");
            out.println("</html>");
          }
        }
        else
        {
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
