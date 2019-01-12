package kapitel2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapVokabel {

	public static void main(String[] args) {
		HashMap myMap = new HashMap();
		myMap.put("blue", "blau");
		myMap.put("red", "rot");
		myMap.put("green", "grün");

		//System.out.println(myMap.get("green"));

		myMap.remove("blue");

		Set mySet = myMap.keySet();

		Iterator iter = mySet.iterator();

		while (iter.hasNext()) {
			String key = (String)iter.next();
			String wert = (String) myMap.get(key);
			System.out.println(key + " / " + wert);
		}
	}
}
