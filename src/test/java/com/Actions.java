package com;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.json.JSONObject;

public class Actions {
	
	JSONObject objjson;
	RequestSpecification requestspec;
	Utility utility;
	
	
	public Actions(RequestSpecification requestspec){
		this.requestspec = requestspec;
	}
	
	public JSONObject get(String geturl, String paramkey, String paramvalue, int statuscode){
	   	 try{
	   		 
	   		 ValidatableResponse getres = 
	   				  given(requestspec)
	      	         .param(paramkey, paramvalue)
	      	         .when()
	      	         .get(geturl)
	      	         .then()
	      	         .statusCode(statuscode);
	      	    	 System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	      	    	 System.out.println(getres.extract().body().asString());
	      	    	 objjson = new JSONObject(getres.extract().body().asString());
	      	    	 System.out.println(objjson.get("HTTPStatusCode")+"status code is---------");
	      	    	// System.out.println(getres.extract().body().asString());
	      	    	 
	   	 	}catch(Exception e){
	   	 		e.printStackTrace();
	   	 	}
		return objjson;
		}

}
