package com;

import java.util.HashMap;

public class Property {
	
	public HashMap<String,String> globalhashmap;
	public ThreadLocal<String> fileseperator;
	public String configfilelocation;
	public String testcasefilelocation;
	public String testdataseperator;
	public String Appkey;
	public String password;
	public String apiURL;
	
	
	public Property(){
			fileseperator = new ThreadLocal<String>() {
		    @Override
		    protected String initialValue() {
			return System.getProperty("file.separator");
		    }
		};
		
		globalhashmap = new HashMap<String, String>();
		testdataseperator = "#";
		
		configfilelocation = "src" + fileseperator.get() + "main" + fileseperator.get()
		+ "resources" + fileseperator.get() + "config.properties";
		
		testcasefilelocation = "src" + fileseperator.get() + "main" + fileseperator.get()
				+ "resources" + fileseperator.get() + fileseperator.get()
				+ "testcase" + fileseperator.get() + "REST_Services_Automation.csv";
		
		
		
	}
	
	
	public HashMap<String, String>  getglobalhashmap(){
		return globalhashmap;
	}

}
