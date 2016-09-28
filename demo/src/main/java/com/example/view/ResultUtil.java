package com.example.view;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {

	private final static String SUCCESS_CODE = "0000";
	
	public Map<String, Object> getResult(Object obj){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("errorCode", SUCCESS_CODE);
		map.put("errorMsg", "");
		map.put("result", obj);
		
		return map;
		
	}
}
