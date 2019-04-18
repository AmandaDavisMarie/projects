package csvParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CsvParser {

	public static void main (String [] args) {
        Map<String, List<User>> userMap =readFile();
        userMap=sortMap(userMap);
        createCompanyFiles(userMap);
	}
	
	public static Map<String, List<User>> readFile() {

		 	String csvFile = "src/csvParser/availityTest1.csv";
	        String cvsSplitBy = ",";
	        Map<String, List<User>> userMap = new HashMap<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        	br.readLine(); // this will read the first line
	        	String line=null;
	            while ((line = br.readLine()) != null) {
	                // use comma as separator
	                String[] userRecord = line.split(cvsSplitBy);
	                User userRow = new User();
	                userRow.setUserId(userRecord[0]);
	                userRow.setFullName(userRecord[1]);
	                userRow.setVersion(Integer.parseInt(userRecord[2]));
	                userRow.setCompany(userRecord[3]);

	                if(userMap.isEmpty() || !userMap.containsKey(userRecord[3])) {
	                	List<User> userList = new ArrayList<>();
	                	userList.add(userRow);
	                	userMap.put(userRecord[3], userList);
	                }else {
	                	boolean addRecord = true;
	                	for(int i=0; i< userMap.get(userRecord[3]).size(); i++) {
	                		User u = userMap.get(userRecord[3]).get(i);
	                		if(u.getUserId().equals(userRow.getUserId())) {
                				addRecord=false;
	                			if(u.getVersion()<userRow.getVersion()) {
	                				userMap.get(userRecord[3]).set(i, userRow);
	                			}
	                		}
	                	}
	                	
	                	if(addRecord) {
	                		userMap.get(userRecord[3]).add(userRow);
	                	}
	                }
	                
	            }
	            
	           

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    return userMap;
	}
	
	public static Map<String, List<User>> sortMap(Map<String, List<User>> map) {
		for (List<User> value : map.values()) {
			 Collections.sort(value, new Comparator<User>() {
	                @Override
	                public int compare(final User object1, final User object2) {
	                    return object1.getFullName().compareTo(object2.getFullName());
	                }
	            }); 
		}
		return map;		
	}
	
	public static void printMap(Map<String, List<User>> map) {
		for (Map.Entry<String, List<User>> entry : map.entrySet()) {
		    System.out.println(entry.getKey()+" : "+entry.getValue());
		}
	}
	
	public static void createCompanyFiles(Map<String, List<User>> map) {
	   for (Map.Entry<String, List<User>> entry : map.entrySet()) {
		   try (PrintWriter writer = new PrintWriter(new File("src/csvParser/"+entry.getKey() +".csv"))) {

		      StringBuilder sb = new StringBuilder();
		      sb.append("user id");
		      sb.append(',');
		      sb.append("full name");
		      sb.append(',');
		      sb.append("version");
		      sb.append(',');
		      sb.append("insurance company");
		      sb.append('\n');

		    	  for(User u: entry.getValue()) {
		    		  sb.append(u.getUserId());
				      sb.append(',');
				      sb.append(u.getFullName());
				      sb.append(',');
				      sb.append(u.getVersion());
				      sb.append(',');
				      sb.append(u.getCompany());
				      sb.append('\n');
		    	  }
		    	      
		      writer.write(sb.toString());

		      
		    } catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
		    }

		  }
	}
}
