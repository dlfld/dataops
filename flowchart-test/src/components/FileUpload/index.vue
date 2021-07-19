<template>
  <el-card class="upload-container">
    <el-upload
        :data="dataObj"
        multiple
        :limit="limit"
        :list-type="listType"
        :show-file-list="showFileList"
        :before-upload="beforeAvatarUpload"
        class="image-uploader image-upload-border"
        accept=".csv"
        :on-remove="rmImage"
        :on-exceed="outLimit"
        :file-list="fileList"
        :auto-upload="true"
        :http-request="uploadSectionFile"
        action="123"
    >
      <i class="el-icon-upload"/>
      <div class="el-upload__text">将csv文件拖到此处，或<em>点击上传</em>csv文件</div>
    </el-upload>
  </el-card>
</template>

<script>
import {uploadpic} from '@/api/singleImage'
// import { getToken } from "@/utils/auth";

export default {
  name: 'SingleImageUpload',
  props: {
    limit: {type: Number, default: 1}, //限定数量
    listType: {type: String, default: 'picture'}, //限定展示方式 text/picture/picture-card
    pathname: {type: String, default: 'file'},
    showFileList: {type: Boolean, default: true} //是否显示文件列表
  },
  data() {
    return {
      fileMsgAndUrl: [], //图片UID和url
      fileUrlList: [], // 路径url
      fileList: [], //文件列表
      tempUrl: '',
      dataObj: {Authorization: ''}
    };
  },
  mounted() {

  },
  methods: {
    // 上传图片之前
    beforeAvatarUpload(file) {
      // const isLt10M = file.size / 1024 / 1024 < 10;
      // if (!isLt10M) {
      //   this.$message.error('上传图片大小不能超过 10MB!');
      // }
      // return isLt10M;
    },
    //上传图片
    uploadSectionFile(param) {
      this.$message({
        message: '正在上传',
        type: 'info'
      });
      var fileObj = param.file;
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append(this.pathname, fileObj);
      let _this = this;
      uploadpic(form).then(res => {
        // console.log(res);
        if (res.msg === "请求成功") {
          let url = {
            uid: fileObj.uid,
            url: res.data
          };
          // console.log(res.data.list[0]);
          _this.fileMsgAndUrl.push(url);
          _this.fileUrlList.push(res.data);
          this.$message({
            message: '上传成功',
            type: 'success'
          });
          // 上传成功
          _this.$emit('uploadSuccess', _this.fileUrlList);

        } else {
          // 上传失败
          _this.$emit('error', res.msg);
        }
      });
    },
    init() {
      // this.dataObj.Authorization = getToken();
      this.rmImages();
    },
    //文件数量超出限制
    outLimit() {
      // this.$emit("outLimit","文件超出限制" );
      this.$message({
        message: '文件数量超出限制',
        type: 'warning'
      });
    },
    //删除已经上传的文件  val是文件
    rmImage(val) {
      this.fileMsgAndUrl = this.fileMsgAndUrl.filter(
          item => item.uid !== val.uid
      );
      this.fileUrlList = this.fileMsgAndUrl.map(item => item.url);
      this.$emit('uploadSuccess', this.fileUrlList);
    },
    //清空所有
    rmImages() {
      this.fileUrlList = [];
      this.fileList = [];
      this.fileUrlList = [];
      // this.$emit('uploadSuccess', []);
    }

  }
};
</script>


<style scoped lang="less">
.upload-container {
  .image-upload-border {
    .el-upload__text{
      font-size: 1rem;
    }
  }
}
</style>
