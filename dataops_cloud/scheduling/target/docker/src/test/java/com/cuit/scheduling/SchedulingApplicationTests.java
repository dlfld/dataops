package com.cuit.scheduling;

import cn.hutool.http.HttpUtil;
import com.cuit.common.pojo.notify.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class SchedulingApplicationTests {

    @Test
    void contextLoads() {
        Message message = new Message().setMessage("测试").setUser_id("2441086385");
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id",message.getUser_id());
        paramMap.put("message",message.getMessage());
        String post = HttpUtil.post("http://10.131.18.162:5700/send_private_msg", paramMap);
        System.out.println(post);
    }

}
