import request from '../utils/request';

export function uploadpic(param) {
    return request({
        url: '/file/upload',
        method: 'post',
        data: param
    });
}
