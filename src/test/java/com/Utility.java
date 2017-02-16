package com;
import com.Property;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Set;


public class Utility {
	
	Property property;
	
	public Utility(Property property){
		this.property = property;
	}
	
	public List<String[]> getRequiredRows(String FilePath) throws Exception {

		File inFile = new File(FilePath);
		List<String[]> completeData = new ArrayList<String[]>();
		BufferedReader reader = null;
		try {
		    String indRow = "";
		    reader = new BufferedReader(new FileReader(inFile));
		    // Skip reading the header
		    // reader.readLine();
		    while ((indRow = reader.readLine()) != null) {
			String[] indRowAsArray = indRow.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			for (int i = 0; i < indRowAsArray.length; i++) {
			    indRowAsArray[i] = indRowAsArray[i].trim();
			    if (indRowAsArray[i].startsWith("\"") && indRowAsArray[i].endsWith("\"")) {
				indRowAsArray[i] = indRowAsArray[i].substring(1, indRowAsArray[i].length() - 1);
				indRowAsArray[i] = indRowAsArray[i].trim();
			    }
			    if (indRowAsArray[i].contains("\"\"")) {
				indRowAsArray[i] = indRowAsArray[i].replaceAll("\"\"", "\"");

			    }
			}
			completeData.add(indRowAsArray);
		    }
		} catch (Exception e) {
		    // TODO: handle exception
		}
		return completeData;
	    }
	
	
	public String getCellValue(List<String[]> sheet, int row, String ColHeader) {
			int colNum = 0;

			String[] header = sheet.get(0);
			try {
			    for (int i = 0; i < header.length; i++) {
				if (header[i].equalsIgnoreCase(ColHeader)) {
				    colNum = i;
				    break;
				}
			    }
			} catch (Exception e) {
			    e.printStackTrace();
			}
			String[] specificRow = sheet.get(row);
			return specificRow[colNum];
		    }
	
	public void populatePropertiesinHashMap(Properties prop){
		Enumeration em = prop.keys();
		Set keyset = prop.keySet();
		Object[] keys = keyset.toArray();
		int i=0;
		while(em.hasMoreElements()){
			String element = em.nextElement().toString();
			property.getglobalhashmap().put(keys[i].toString().toLowerCase(),prop.getProperty(element));
			i++;
		}
	}
	
	public String getkeyvaluefromHashMap(String key){
		try{
			key = key.toLowerCase();
			return property.getglobalhashmap().get(key);
		}catch(Exception e){
			return key;
		}
	}
	
	public void writeFile(String path, String content) throws IOException {
		try {

			File file = new File(path);
			file.getParentFile().mkdirs();
			file.createNewFile();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));
			// JSONObject newObj = new JSONObject(content);
			// newObj.write(expectedJSON_Writer);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getCurrentDateAndTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
