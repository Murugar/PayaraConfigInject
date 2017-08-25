
package com.iqmsoft.config.injection;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    
    // Example showing injection of a default property
    @Inject
    @ConfigProperty(name = "default.property", defaultValue = "Default Value")
    String defaultProperty;
    
    // Example showing reading a config property from the META-INF/microprofile-config.properties file
    @Inject
    @ConfigProperty(name = "file.property")
    String fileProperty;
    
    // Example config property that uses a default converter to LocalDate
    @Inject
    @ConfigProperty(name = "date.property")
    LocalDate date;

    // Example injection the standard environment variable home
    @Inject
    @ConfigProperty(name = "HOME", defaultValue = "HOME environment variable not set")
    String home;
    
    // Example injection of the standard System Property java.home
    @Inject
    @ConfigProperty(name = "java.home", defaultValue = "java.home environment variable not set")
    String javaHome;
    

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Injection of Config Values</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Injection of Config Values</h1>");
            out.println("<p>Configuration</p>");
            out.println("<p>You can also override values by setting system properties or environment variables</p>");
            out.println("<table><tr><th>Property Name</th><th>Property Value</th></tr>");
            out.format("<tr><td>%s</td><td>%s</td></tr>", "default.property",defaultProperty);
            out.format("<tr><td>%s</td><td>%s</td></tr>", "file.property",fileProperty);
            out.format("<tr><td>%s</td><td>%s</td></tr>", "date.property",date);
            out.format("<tr><td>%s</td><td>%s</td></tr>", "HOME",home);
            out.format("<tr><td>%s</td><td>%s</td></tr>", "java.home",javaHome);
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
