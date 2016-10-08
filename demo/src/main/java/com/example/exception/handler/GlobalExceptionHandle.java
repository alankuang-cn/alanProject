package com.example.exception.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.view.ErrorInfo;

@ControllerAdvice
public class GlobalExceptionHandle {
	
	Log logger = LogFactory.getLog(this.getClass());
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		
		//打印 异常信息
		//e.printStackTrace();
		printLog(e);
		
		ErrorInfo<String> r = new ErrorInfo<>();
		r.setMessage(e.getMessage());
		r.setCode(ErrorInfo.ERROR);
		r.setData(" ");
		return r;
	}
	
	
	private void printLog(Exception e){
	
		StringWriter sw = null;
		PrintWriter pw = null;
		
		try{
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			sw.flush();
			pw.flush();
		}finally{
			if(sw != null){
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(pw != null){
				pw.close();
			}
		}
		
		logger.error(sw.toString());
		
	}

}
