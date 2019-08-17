/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/groupdescript"})
public class groupdescript extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            if(session.getAttribute("login") == "logged")
            {
                String jdbc_driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:derby://localhost:1527/Let_Plan";
        String user="Parth";
        String pass="parth";
 
        try
        {
               String user1 = request.getParameter("user1");
               String pass1 = request.getParameter("pass");
            Connection con = DriverManager.getConnection(url,user,pass);
            Statement stmt = con.createStatement();
            String query1 = "insert into groupfriend (group_id,friend_id) values (?,?)";
            PreparedStatement prestmt1 = con.prepareStatement(query1);
            prestmt1.setString(1,(String) session.getAttribute("group"));
            prestmt1.setString(2, (String) session.getAttribute("current"));
            prestmt1.execute();
            String str = "select * from homegroup where group_id = "+session.getAttribute("group");
            out.println("<br/>group : "+session.getAttribute("group")+"<br/>");
            ResultSet rs = stmt.executeQuery(str);
            while(rs.next())
            {
                out.println("Name : "+rs.getString(2)+"<br/>Party Name : "+rs.getString(3)+"<br/>Description : "+rs.getString(5)+"<br/>Location : "+rs.getString(4)+"<br/>Admin : "+rs.getString(6));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href='friendlist'>See Friend List</a><br/>");
                out.println("<a href='search.jsp'>Add Friend to the Group</a><br/>");
                out.println("<a href='addmoney'>Money Management</a><br/>");
                out.println("<a href='chatting'>Chatting</a><br/>");

                out.println("</body>");
                out.println("</html>");
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.forward(request, response);
            }
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
