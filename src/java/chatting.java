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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/chatting"})
public class chatting extends HttpServlet {

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
            //HttpSession session = request.getSession();
            //if(session.getAttribute("login") == "logged")
            //{
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                String jdbc_driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:derby://localhost:1527/Let_Plan";
                String user="Parth";
                String pass="parth";
                try
                {
                    Connection con = DriverManager.getConnection(url,user,pass);
                    Statement stmt = con.createStatement();
                    String str="select * from chat";
                    ResultSet rs = stmt.executeQuery(str);
                    String str1="select * from sign";
                    ResultSet rs1 = stmt.executeQuery(str1);
                    String name11="";
                    while(rs.next())
                    {
                        if(rs.getString(2) == session.getAttribute("current"))
                        {
                            out.println("You : "+rs.getString(3));
                        }
                        else
                        {
                            while(rs1.next())
                            {
                                if(rs.getString(2) == rs1.getString(2))
                                {
                                    name11 = rs.getString(2);
                                    break;
                                }
                            }
                            out.println("&nbsp;&nbsp;&nbsp;&nbsp;"+name11+" : "+rs.getString(3));
                        }
                    }
                    out.println("<form action='chat1' method='post'>");
                        out.println("Type a message : <input type='text' name='chat'><br/><input type='submit' name='submit' value='submit'>");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                out.println("</body>");
                out.println("</html>");
            }
        
           // else
            //{
              //  RequestDispatcher rd = request.getRequestDispatcher("Home");
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
