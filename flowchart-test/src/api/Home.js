import request from '../utils/request'

//获取操作功能列表
export function getOPtionsList(){
    return request({
        url: '/options',
        method: 'get'
    })
}


//进行options的提交与执行
export function submitOptions(submitOptionsRequest){
    return request({
        url: '/options',
        method: 'post',
        data:submitOptionsRequest
    })
}
