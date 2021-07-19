import request from '../utils/request'

//获取操作功能列表
export function getOPtionsList(){
    // return request({
    //     url: '/options',
    //     method: 'get'
    // })
    return {
        data:[{optUrl:"/add/",optName:"加法",optDesc:"执行加法操作"}]
    }
}



//进行options的提交与执行
export function submitOptions(submitOptionsRequest){
    return request({
        url: '/options/topo2',
        method: 'post',
        data:submitOptionsRequest
    })
}
