package com.cuit.user_manage.service.impl;

import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.model.base.user_manage.User;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.user_manage.service.intf.UserInfoService;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.cuit.common.utils.MetaFileUtil.isMateFile;

/**
 * @Author wcw
 * @Description TODO
 * @Date 2021/11/12
 * @Version 1.0
 */
public class UserInfoServiceImpl implements UserInfoService {

    /**
     * 文件路径 指的是文件树的根节点
     */
    @Value(value = "${path.home}")
    protected String fileHomePath;

    /**
     * 获取指定目录下的用户信息。
     * @return 用户信息
     */
    @Override
    public ResponseData getUserList(){
        Map<String, String> userMap = new HashMap<String, String>();
        // 建立当前目录中文件的File对象
        File file = new File(fileHomePath);
        // 取得代表目录中所有文件的File对象数组
        File[] list = file.listFiles();
        // 遍历当前目录的用户
        for (int i = 0; i < list.length; i++) {
            // 判断当前文件是否为mate文件
            if (isMateFile(list[i].getName())){
                //获取用户对应mate文件中的信息
                User user = MetaFileUtil.metaRead(list[i].getPath(),User.class);
                if (Objects.isNull(user)){
                    ExceptionCast.cast(ResponseDataUtil.buildError("文件中的用户为空"));
                }else {
                    userMap.put(user.getRealName(),user.getUserName());
                }
            }
        }
        return ResponseDataUtil.buildSuccess(userMap);
    }
}
