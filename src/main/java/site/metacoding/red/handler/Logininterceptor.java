package site.metacoding.red.handler;
import java.awt.PageAttributes.MediaType;
import java.io.PrintWriter;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.response.CMRespDto;

public class Logininterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("================");
	      System.out.println(request.getRequestURI());
	      System.out.println("================");
	      
	      String uri = request.getRequestURI(); // 요청되는 주소를 문자열로 받아본다
	      
	      HttpSession session = request.getSession();
	      Users principal = (Users) session.getAttribute("principal");
	      if(principal == null) {
	         if(uri.contains("api")) { // api 라는 문자가 포함되어있는지 체크
	            System.out.println("===========");
	            System.out.println("API 가 주소에 있음"); 
	            
	            //response.setHeader("Content-Type", "application/json; charset=utf-8");
	            //위와 아래는 똑같은거임
	            response.setContentType("application/json; charset=utf-8");
	            PrintWriter out = response.getWriter();
	            CMRespDto<?> cmRespDto = new CMRespDto<>(-1, "인증이 필요합니다", null);
	            ObjectMapper om = new ObjectMapper();
	            String json = om.writeValueAsString(cmRespDto);
	            out.println(json);
	         }else {
	            System.out.println("===========");
	            System.out.println("API 가 주소에 없음");
	            response.sendRedirect("/loginForm");
	         }
	         return false;
	      }
	      return true;
	}
}
