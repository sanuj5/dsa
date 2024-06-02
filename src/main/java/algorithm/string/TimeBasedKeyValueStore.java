package algorithm.string;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/time-based-key-value-store/
 */
public class TimeBasedKeyValueStore {
    Map<String, TreeMap<Integer,String>> map;

    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore() {
        this.map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(map.get(key) == null){
            map.put(key,new TreeMap<Integer, String>());
        }
        map.get(key).put(timestamp,value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> integerStringMap = map.get(key);
        if( integerStringMap == null) return "";
        return integerStringMap.ceilingEntry(timestamp) != null ? "" : integerStringMap.firstEntry().getValue();

    }
}
