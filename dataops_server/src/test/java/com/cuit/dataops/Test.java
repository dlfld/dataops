package com.cuit.dataops;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
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
    public static List<String> list = new ArrayList<>();
}

public class Test {
    public static void main(String[] args) {
        File file = new File("/Users/dailinfeng/Desktop/dataops/result/e3904304-c78b-4f9e-b8bf-d7874d703cb8.json");
        String filePath = "/Users/dailinfeng/Desktop/dataops/result/e3904304-c78b-4f9e-b8bf-d7874d703cb8.json";
        CsvReader reader = CsvUtil.getReader();
//从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file(filePath));
        System.out.println(data.toString());
    }
}
