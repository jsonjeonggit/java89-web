package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.ContactMysqlDao;
import bitcamp.java89.ems.vo.Contact;

// 톰캣 서버가 실행할 수 있는 클래스는 반드시 Servlet 규격에 맞추어 제작해야 한다.
// 그러나 Servlet 인터페이스의 메서드가 많아서 구현하기 번거롭다.
// 그래서 AbstractServlet이라는 추상 클래스를 만들어서,
// 이 클래스를 상속 받아 간접적으로 Servlet인터페이스를 구현하는 방식을 취한다.
// 이 클래스를 상속받게 되면 오직 service() 메서드만 만들면 되기 때문에 코드가 편리하다.
@WebServlet("/contact/list")
public class ContactListServlet extends AbstractServlet {
  @Override
  public void service(ServletRequest request, ServletResponse response) 
      throws ServletException, IOException {
    try {
      ContactMysqlDao contactDao = ContactMysqlDao.getInstance();
      ArrayList<Contact> list = contactDao.getList();
    
      // 웹브라우저 쪽으로 출력할 수 있도록 출력 스트림 객체를 얻는다.
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      for (Contact contact : list) {
        out.printf("%s,%s,%s,%s\n",
          contact.getName(),
          contact.getPosition(),
          contact.getTel(),
          contact.getEmail());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    
  }
}








