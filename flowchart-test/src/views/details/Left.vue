<template>
  <div id="left-app">
    <div class="wrap">
      <el-card class="title-container">
        <span class="up-title">数据上传</span>
      </el-card>
    </div>
    <div class="wrap">
      <el-card class="content-container" shadow="hover">
        <file-upload @uploadSuccess='uploadSuccess' listType='text' class="file-uploader"/>
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
  },
  data: {
    return() {
      fileMsg:""
    }
  },
  methods: {
    uploadSuccess(nameList) {
      console.log(nameList);
      this.fileMsg = nameList[0]

      this.$store.state.fileMsg = this.fileMsg
    }
  }
}
</script>

<style scoped lang="less">
#left-app {
  min-width: 152px;
  height: 100%;
  width: 100%;
  display: grid;
  margin-top: 0.3rem;
  grid-template-rows: 1fr 15fr;

  .wrap {
    display: grid;
    //标题
    .title-container {
      //margin-top: 1%;
      height: 90%;
      border-radius: 4rem;
      //align-items: center;
      //justify-items: center;
      background-color: #349ee9;

      .up-title {
        letter-spacing: 0.5rem;
        font-size: 1.2rem;
        font-weight: bold;
        color: white;
      }
    }

    //内容  上传图片的
    .content-container {
      border-radius: 1rem;
      //background-color: #349ee9;
      .file-uploader {
        border-radius: 2rem;
      }

      .file-uploader:hover {
        -webkit-transform: scale(1.1);
      }

      //box-shadow:1px 1px 1px 1px aquamarine;
    }

    .file-describe {

    }
  }
}
</style>
