import request from '../utils/request';

export function uploadpic(param) {
    return request({
        url: '/image/upload',
        method: 'post',
        data: param
    });
}
