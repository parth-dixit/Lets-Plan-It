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
@WebServlet(urlPatterns = {"/newgroupservlet"})
public class newgroupservlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         HttpSession session = request.getSession();

            if(session.getAttribute("login") == "logged"){
            String jdbc_driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:derby://localhost:1527/Let_Plan";
            String user="Parth";
            String pass="parth";
            
            try
            {

                   String gname = request.getParameter("gname");
                   String event = request.getParameter("event");
                   String descript = request.getParameter("descript");
                   String locate = request.getParameter("locate");
                 
                    Connection con = DriverManager.getConnection(url,user,pass);
                    Statement stmt = con.createStatement();
                    String query = "insert into homegroup (group_id,group_name,party_name,location,description,admin_name) values(?,?,?,?,?,?)";

                    PreparedStatement prestmt = con.prepareStatement(query);
                    ServletContext context = getServletContext();
                    int id = (int)context.getAttribute("groupid");

                    //prestmt.setString(1,(String)id);

                
                
                    prestmt.setString(2,gname);
                    prestmt.setString(3,event);
                    prestmt.setString(4,descript);
                    prestmt.setString(5,locate);
                    prestmt.setString(6, (String) session.getAttribute("current"));

                    prestmt.execute();
                     con.close();
                     RequestDispatcher rd = request.getRequestDispatcher("Home");
                    rd.forward(request, response);
                  
                
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
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
