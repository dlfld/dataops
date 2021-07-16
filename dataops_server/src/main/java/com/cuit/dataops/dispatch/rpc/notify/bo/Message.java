package com.cuit.dataops.dispatch.rpc.notify.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class Message {
    String user_id;  //用户的qq
    String message;//消息体
}
