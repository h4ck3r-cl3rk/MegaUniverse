package parts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropMan {

	private Map<String, String> varMaps;	
	private static String KERNEL_PATH="kernel.prop";	
	private Properties prop;
	
	public PropMan() {
		try {
			
			prop = new Properties();
			FileInputStream in = new FileInputStream(KERNEL_PATH);
			prop.load(in);
			in.close();
			
			varMaps = new HashMap<String, String>();
			
			varMaps.put("FILEPATH", prop.getProperty("FILEPATH"));
			varMaps.put("MEDIA", prop.getProperty("MEDIA"));
			varMaps.put("MEDIALISTPART", prop.getProperty("MEDIALISTPART"));
			varMaps.put("THEMES", prop.getProperty("THEMES"));			
			varMaps.put("DECORATION", prop.getProperty("DECORATION"));
			varMaps.put("MODEL", prop.getProperty("MODEL"));
			varMaps.put("HANDLERS", prop.getProperty("HANDLERS"));
			
		} catch (Exception e) {
			System.err.println("Something went wrong " + e.getMessage());
		}
	}
	
	public void put(String key, String value) {
		varMaps.put(key, value);
		this.save(getVar());
	}
	
	public HashMap<String, String> getVar() {
		return (HashMap<String, String>) varMaps;
	}
	
	public void save(HashMap<String, String> getVar) {
		FileOutputStream out;
		
		try {
		    prop.putAll(varMaps);			
			out = new FileOutputStream("kernel.prop");
			prop.store(out, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
