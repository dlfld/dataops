package com.cuit.dataops.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class ParamsBody {
    List<String> items;
}
