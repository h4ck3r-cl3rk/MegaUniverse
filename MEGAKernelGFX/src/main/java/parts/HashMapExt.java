package parts;

import java.util.HashMap;

public class HashMapExt<K, V> extends HashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7142494166398824439L;
	
	public V put(K key, V value, PropMan propMan) {
		V ret = super.put(key, value);
		@SuppressWarnings("unchecked")
		HashMap<String, String> getVar = (HashMap<String, String>)this;
		propMan.save(getVar);
		return ret;
	}
}
