package com.cuit.file_manage.operation.factory;

import com.cuit.file_manage.operation.handler.AbstractFileHandler;
import org.apache.commons.lang3.StringUtils;

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
        if (StringUtils.isEmpty(name)){
            return;
        }
        strategyMap.put(name,handler);
    }

}
