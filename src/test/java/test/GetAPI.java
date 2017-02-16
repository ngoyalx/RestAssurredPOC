package test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.Actions;
import com.Property;
import com.Utility;

import dataprovider.DataProviderClass;



public class GetAPI {
	
	RequestSpecification requestspec;
	Properties objProp;
	Property property;
	Utility utility;
	List<String[]> testdata;
	JSONObject objJson;
	Actions action;
	DataProviderClass dataprovider;
	
	public GetAPI(){
		property = new Property();
		utility = new Utility(property);
		objProp = new Properties();
		dataprovider = new DataProviderClass(property);
	}
	
	@BeforeTest
	public void setup() throws FileNotFoundException, IOException{
		
		//load config.properties file in Properties obj
		objProp.load(new FileInputStream(property.configfilelocation));
		
		//loading config file values in globalhashmap that can be accessed throughout the project
		utility.populatePropertiesinHashMap(objProp);
		
		property.Appkey = utility.getkeyvaluefromHashMap("Appkey");
		property.password = utility.getkeyvaluefromHashMap("Password");
		property.apiURL = utility.getkeyvaluefromHashMap("URL");
		
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.addHeader("Appkey",property.Appkey);
		builder.addHeader("Password", property.password);
		requestspec = builder.build();
		requestspec.baseUri(property.apiURL);
		
	}
	
	
	
	@Test(dataProviderClass=DataProviderClass.class,dataProvider="testdata")
	public void validateapimethod(String testcaseid, String teststep, String stepaction, String data) throws IOException{
		action = new Actions(this.requestspec);
		
		if(stepaction.equalsIgnoreCase("getRequest") && !data.equals("")){
			String[] datacomponents = data.split(property.testdataseperator);
			
			String apitype = datacomponents[0];
			String geturl = datacomponents[1];
			String getparamkey = datacomponents[2];
			String getparamvalue = datacomponents[3];
			String filename = datacomponents[4];
			int getstatuscode = Integer.parseInt(datacomponents[5]);
			
			String getresponsefilepath = "src" + property.fileseperator.get() + "test" +
			property.fileseperator.get() + "resources" + property.fileseperator.get()
			+ apitype + property.fileseperator.get() + filename;
			
			
			this.objJson = this.action.get(geturl, getparamkey, getparamvalue, getstatuscode);
			if(!this.objJson.equals("")){
				utility.writeFile(getresponsefilepath, this.objJson.toString());
			}
		}
	}
	
}
	
	
   


