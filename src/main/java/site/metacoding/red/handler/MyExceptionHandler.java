package site.metacoding.red.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import site.metacoding.red.handler.ex.MyApiException;
import site.metacoding.red.handler.ex.MyException;
import site.metacoding.red.util.Script;
import site.metacoding.red.web.dto.response.CMRespDto;


@ControllerAdvice
public class MyExceptionHandler {

		@ExceptionHandler(MyApiException.class) // 컴파일시 오류
		public @ResponseBody CMRespDto<?> apiError(Exception e)  { //런타임시 오류 
			return new CMRespDto<>(-1, e.getMessage(), null);
		}
		
		@ExceptionHandler(MyException.class) 
		public @ResponseBody String  m1(Exception e)  { 
			return Script.back("잘못된 요청을 해서 "); //만들어놓은 Script 
		}
}
