package dataprovider;

import java.util.List;
import com.Property;
import com.Utility;


public class DataProviderClass {
	
	private static Property property;
	private static Utility utility;
	public static List<String[]> testdata;
	
	public DataProviderClass(Property property){
		this.property = property;
		utility = new Utility(property);
	}
	
	
	@org.testng.annotations.DataProvider(name = "testdata")
    public static Object[][] getdata() throws Exception{
    	
    	testdata = utility.getRequiredRows(property.testcasefilelocation);
    	System.out.println(testdata.size());
    	Object[][] data = new Object[testdata.size()-1][4];
    	for(int i=1,j=0; i<testdata.size(); i++,j++){
    		String [] line = testdata.get(i);
    			for(int k=0;k<line.length;k++){
    				data[j][k] = line[k];	
    			}
    			System.out.println();
    		}
    	return data;
    }

}
