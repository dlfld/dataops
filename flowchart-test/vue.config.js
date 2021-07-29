
module.exports = {
  lintOnSave: false, //关闭eslint
  publicPath: './',
  devServer: {
    proxy: {
      '/dev-api': {
        target: 'http://127.0.0.1:18003/taskhandle',
        // target: 'http://10.131.18.162:18003/taskhandle',
        changeOrigin: true,
        pathRewrite: {
          '^/dev-api': ''
        }
      }

    }

  },
  // chainWebpack: config => {
  //   config.resolve.alias
  //       .set('_c', resolve('src/components')) // key,value自行定义，比如.set('@@', resolve('src/components'))
  // },
}
