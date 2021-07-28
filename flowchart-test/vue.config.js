
module.exports = {
  lintOnSave: false, //关闭eslint
  publicPath: './',
  devServer: {
    proxy: {
      '/dev-api': {
        // target: 'http://150.158.174.169:9999/',
        target: 'http://127.0.0.1:18003/taskhandle',
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
