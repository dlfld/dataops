package com.cuit.file_manage.factory;

import com.cuit.file_manage.handler.AbstractFileHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author wcw
 * @Date: 2022/1/1/16:33
 * @Version 1.0
 * @Description
 */
public class FileFactory {
    private static Map<String, AbstractFileHandler> strategyMap = new HashMap<>();
    public static AbstractFileHandler getInvokeStrategy(String name){
        return strategyMap.get(name);
    }
    public static void register(String name, AbstractFileHandler handler){
        if (Objects.isNull(name)||"".equals(name)){
            return;
        }
        strategyMap.put(name,handler);
    }

}
