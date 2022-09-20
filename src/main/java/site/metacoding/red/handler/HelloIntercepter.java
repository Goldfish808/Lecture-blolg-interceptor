package site.metacoding.red.handler;
import java.awt.PageAttributes.MediaType;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.response.CMRespDto;

public class HelloIntercepter implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		3. http body 에 "최주호바보"
//		BufferedReader br = request.getReader();
//		String s = "";
//		while(true)
//		    s = s + br.readLine();
//
//		if(s.contains("바보")){
//		   
//		}
		
		BufferedReader br = request.getReader();
		StringBuilder st = new StringBuilder();
		String s = "";
		while(true) {
			s= br.readLine();
			if(s ==null)break;
			st.append(s);
		}

		if (st.toString().contains("바보")) { // api 라는 문자가 포함되어있는지 체크
			System.out.println("===========");
			System.out.println("내용 : " + st);

			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			CMRespDto<?> cmRespDto = new CMRespDto<>(-1, "욕하지마", null);
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(cmRespDto);
			out.println(json);
			return false;
		}
		return true;

	}
}
