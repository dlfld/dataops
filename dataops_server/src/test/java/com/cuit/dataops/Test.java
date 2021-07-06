package com.cuit.dataops;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        map.put("1", new ArrayList<String>() {{
            add("1");
        }});
        map.get("1").add("2");
        System.out.println(map.get("2"));
        for(String s:map.get("2")){
            System.out.println(s);
        }
    }
}
