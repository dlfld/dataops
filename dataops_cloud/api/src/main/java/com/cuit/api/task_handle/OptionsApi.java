package com.cuit.api.task_handle;


import com.cuit.common.pojo.request.SubmitOptionsRequest;
import com.cuit.common.pojo.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author dailinfeng
 */
@Api(tags = "Options 相关接口")
public interface OptionsApi {
    /**
     * 获取options列表
     * （获取所有方法列表）
     *
     * @return
     */
    @ApiOperation("获取所有options方法")
    ResponseData getOptionsList();

    /**
     * 获取前端解释完成之后的数据接口
     * @param submitOptionsRequest 请求参数
     * @return
     */
    @ApiOperation("提交操作结果3")
    ResponseData submitOptions(SubmitOptionsRequest submitOptionsRequest);
}
