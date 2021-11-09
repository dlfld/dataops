package com.cuit.common.model.notify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author dailinfeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Message {
    /**
     * 用户的QQ
     */
    String user_id;

    /**
     * 消息体
     */
    String message;
}
