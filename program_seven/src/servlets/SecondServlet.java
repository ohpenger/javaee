package servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.demo.Student;

@WebServlet(name = "SecondServlet")
public class SecondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        ServletContext servletContext =this.getServletContext();
        Student student =(Student)servletContext.getAttribute("student");
        String sno = student.getSno();
        String name = student.getName();
        PrintWriter out =response.getWriter();
        out.println("学号: "+sno+"<br/>"+"姓名: "+name+"<br/>"+"<a href='http://localhost:8080/input.html'>跳转</a>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
