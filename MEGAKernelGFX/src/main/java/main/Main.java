package main;

import java.util.Map;

import parts.HashMapExt;
import parts.PropMan;

public class Main {

	public static void main(String[] args) {
		PropMan propMan = new PropMan();		
		HashMapExt<String, String> settings = (HashMapExt<String, String>) propMan.getVar();
		
		for (Map.Entry<String, String> entry : settings.entrySet()) {
			System.out.println("MapElement ID: " + entry.hashCode() 
					+ "\t Key: " + entry.getKey() + "\t Value: " + entry.getValue());
		}
		
		settings.put("MEDIA", "Digga");
		settings.put("MODEL", "ModelDigga");
		
		Object test = new Object(){
			@SuppressWarnings("unused")
			public void OwnClass() {
				System.out.println("Echo");
			}
			
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return super.toString();
			}
		};
		
		System.out.println(test);
		
	}

}
