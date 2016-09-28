package com.example.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.view.ErrorInfo;

@ControllerAdvice
public class GlobalExceptionHandle {
	
	private Log logger =LogFactory.getLog(getClass());
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		
		//打印 异常信息
		logger.error(e);
		
		StringBuffer sb = new StringBuffer();
		for(int i= 0 ; i<e.getStackTrace().length;i++){
			sb.append(e.getStackTrace()[i]+"\n");
		}
		
		logger.error(sb.toString());
		
		ErrorInfo<String> r = new ErrorInfo<>();
		r.setMessage(e.getMessage());
		r.setCode(ErrorInfo.ERROR);
		r.setData("Some Data");
		r.setUrl(req.getRequestURL().toString());
		return r;
	}

}
