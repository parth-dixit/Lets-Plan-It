/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Parth Chutiyo
 */
@WebServlet(urlPatterns = {"/signupservlet"})
public class signupservlet extends HttpServlet {

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
            String jdbc_driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:derby://localhost:1527/Let_Plan";
            String user="Parth";
            String pass="parth";
            
            try
            {
                   int id=Integer.parseInt(request.getParameter("id"));
                   String first = request.getParameter("first");
                   String last = request.getParameter("last");
                   String mail = request.getParameter("mail");
                   String phone = request.getParameter("phone");
                   String pass1 = request.getParameter("pass12");
                   String pass2 = request.getParameter("pass2");
                   if(pass1.equals(pass2))
                   {
                    
                    Connection con = DriverManager.getConnection(url,user,pass);
                    Statement stmt = con.createStatement();
                    ServletContext context = getServletContext();
                    int i = (int)context.getAttribute("signup");
                    String query = "insert into SIGN (ID,first_name,last_name,email,phone,password) values(?,?,?,?,?,?)";
                    PreparedStatement prestmt = con.prepareStatement(query);
                
                    prestmt.setInt(1,i);
                    prestmt.setString(2,first);
                    prestmt.setString(3,last);
                    prestmt.setString(4,mail);
                    prestmt.setString(5,phone);
                    prestmt.setString(6,pass1);
                    prestmt.execute();
                    i++;
                    context.setAttribute("signup",i);
                     con.close();
                     out.println("Signed up successfully");
                      RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.forward(request, response);
                   }
                   else
                   {
                       RequestDispatcher rd = request.getRequestDispatcher("signupservlet");
                    rd.forward(request, response);
                   }
            }
            catch(Exception e)
            {
                out.println(e);
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
