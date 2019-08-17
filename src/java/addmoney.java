/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/addmoney"})
public class addmoney extends HttpServlet {

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
           // if(session.getAttribute("login") == "logged")
            //{
                String jdbc_driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:derby://localhost:1527/Let_Plan";
                String user="Parth";
                String pass="parth";
                try
                {
                    Connection con = DriverManager.getConnection(url,user,pass);
                    Statement stmt = con.createStatement();
                
            /* TODO output your page here. You may use following sample code. */
                    out.println("<form action='addmoneyservlet' method='post'>");
                    out.println("<select>");
                    out.println("<option value='divide'>Divide</option>");
                    out.println("<option value='indi'>Individual</opiton>");
                    out.println("</select><br/>");
                    String str="select * from sign";
                    ResultSet rs = stmt.executeQuery(str);
                    while(rs.next())
                    {
                        out.println(rs.getString(2)+" "+rs.getString(3)+" <input type='checkbox' name='id' value='"+rs.getString(1)+"'><br/>");
                    }
                    out.println("<input type='submit' name='submit' value='Add'>");
                    out.println("</form>");
                }
                catch(Exception e)
                {
                    
                }
            //}
              //else
              //{
                   //RequestDispatcher rd = request.getRequestDispatcher("Home");
                    //rd.forward(request, response);
              //}
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
