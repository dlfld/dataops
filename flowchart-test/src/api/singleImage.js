import request from '../utils/request';
import requestFile from "@/utils/requestFile";

export function uploadpic(param) {
    return request({
        url: '/taskhandle/file/upload',
        method: 'post',
        data: param
    });
}


export function uploadpicToPyserver(param) {
    return requestFile({
        url: '/data_upload',
        method: 'post',
        data: param
    });
    // return request({
    //     url: '/file/upload',
    //     method: 'post',
    //     data: param
    // });
}
