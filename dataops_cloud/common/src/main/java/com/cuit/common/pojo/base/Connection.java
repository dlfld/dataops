package com.cuit.common.pojo.base;

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
public class Connection {
    /**
     * 出发点
     */
    private NodeMsg source;
    /**
     * 指向点
     */
    private NodeMsg destination;
}
