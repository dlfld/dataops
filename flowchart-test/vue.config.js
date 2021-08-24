module.exports = {
    lintOnSave: false, //关闭eslint
    publicPath: './',
    devServer: {
        proxy: {
            '/dev-api': {
                target: 'http://127.0.0.1:18001', //本地不启动gateway的开发方式
                // target: 'http://10.131.18.162:18003/taskhandle',  //服务器
                changeOrigin: true,
                pathRewrite: {
                    '^/dev-api': ''
                }
            },
            '/dataapi': {
                target: 'http://127.0.0.1:8000',
                // target: 'http://10.131.18.162:8000',
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
