module.exports = {
    lintOnSave: false, //关闭eslint
    publicPath: './',
    devServer: {
        proxy: {
            //一般接口启动
            '/dev-api': {
                // target: 'http://127.0.0.1:18001', //本地不启动gateway的开发方式
                // target: 'http://10.23.71.70:18003/taskhandle',  //服务器
                target: 'http://172.18.130.107:18003/taskhandle',  //服务器
                changeOrigin: true,
                pathRewrite: {
                    '^/dev-api': ''
                }
            },
            //数据上传
            '/dataapi': {
                // target: 'http://127.0.0.1:8000',
                // target: 'http://10.23.71.70:8000',
                target: 'http://172.18.130.107:18010',
                changeOrigin: true,
                pathRewrite: {
                    '^/dataapi': ''
                }
            }
        }

    },
    // chainWebpack: config => {
    //   config.resolve.alias
    //       .set('_c', resolve('src/components')) // key,value自行定义，比如.set('@@', resolve('src/components'))
    // },
}
