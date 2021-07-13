package com.cuit.dataops;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
class Param123 {
    private String desc; //参数的描述  计算端根据参数的描述来进行参数的检索
    private Integer version = 0;
}

public class Test {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>() {{
            put("1", "1");
        }};
        System.out.println(map.get("2"));
    }
}
