<template>
  <div id="left-app">
    <div class="wrap">
      <el-card class="title-container">
        <span class="up-title">数据上传</span>
      </el-card>
    </div>
    <div class="wrap">
      <el-card class="content-container">
        <file-upload />
      </el-card>
    </div>
  </div>
</template>

<script>
import FileUpload from '@/components/FileUpload'

export default {
  name: "Left",
  components: {
    FileUpload
  }
}
</script>

<style scoped lang="less">
#left-app {
  height: 100%;
  width: 100%;
  display: grid;
  grid-template-rows: 1fr 15fr;

  .wrap {
    display: grid;
    //标题
    .title-container {
      margin-top: 1%;
      height: 90%;
      border-radius: 4rem;
      //align-items: center;
      //justify-items: center;

      .up-title {
        letter-spacing: 0.5rem;
        font-size: 1.2rem;
        font-weight: bold;
      }
    }

    //内容
    .content-container {

    }
  }
}
</style>
