package com.cuit.common.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/28 10:21 下午
 * @Version 1.0
 */

public class SerializableUtil {
    /**
     * 对象转为json
     *
     * @param object
     * @return
     */
    public static String objectToJson(Object object) {
        JSONObject json = JSONUtil.parseObj(object, false, true);
        return json.toStringPretty();
    }

    /**
     * json字符串转为对象
     *
     * @param jsonStr
     * @return
     */
    public static <T> Object stringToObject(String jsonStr, Class<T> beanClass) {
        return JSONUtil.toBean(jsonStr, beanClass);
    }
}
