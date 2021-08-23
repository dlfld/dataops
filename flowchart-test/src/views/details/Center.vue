<template>
  <div id="center-chart">
    <el-card class="button-wrap">
      <el-button type="success" round @click="addNodeDialogVisible = true" class="func-btn">Add</el-button>
      <el-button type="success" round @click="$refs.chart.remove()" class="func-btn">Del</el-button>
      <el-button type="success" round @click="$refs.chart.editCurrent()" class="func-btn">Edit</el-button>
      <el-button type="success" round @click="inputOptionMessageVisible = true" class="func-btn">Save</el-button>
    </el-card>
    <el-card class="pannel">
      <flowchart :nodes="nodes" :connections="connections" @editnode="handleEditNode"
                 @dblclick="handleDblClick" @editconnection="handleEditConnection"
                 @save="handleChartSave" ref="chart"
                 @delete="handleDelete"
                 width="100%" height="54rem">
        <!--      height="57.6rem"-->
      </flowchart>
    </el-card>
    <el-dialog
        title="添加节点"
        :visible.sync="addNodeDialogVisible"
        width="30%">
      <span>请选择功能节点:</span>
      <el-select v-model="option" placeholder="请选择">
        <el-option
            v-for="item in options"
            :key="item.optUrl"
            :label="item.optName"
            :value="item.optUrl"
        >
          <el-tooltip class="item" effect="dark" :content="item.optDesc" placement="right">
            <span>{{ item.optName }}</span>
          </el-tooltip>
        </el-option>
      </el-select>
      <!--      <el-input v-model="baseNumber" placeholder="请输入内容"></el-input>-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addNodeDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addNode">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
        title="信息完善"
        :visible.sync="inputOptionMessageVisible"
        width="30%">
      <span style="display: flex;place-items: center">
        <span style="font-size: 1rem;width: 11rem">请输入QQ号:</span>
        <el-input style="width: 10rem;margin-left: 3rem" v-model="userContact" placeholder="请输入QQ"></el-input>
      </span>
      <el-divider content-position="right"></el-divider>
      <span style="display: flex;place-items: center">
        <span style="font-size: 1rem;width: 11rem">请输入上传文件的desc:</span>
        <el-input style="width: 10rem;margin-left: 3rem" v-model="paramsDesc" placeholder="请输入上传文件的desc"></el-input>
      </span>


      <span slot="footer" class="dialog-footer">
        <el-button @click="inputOptionMessageVisible = false">取 消</el-button>
        <el-button type="primary" @click="nodeSavePre">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
// @ is an alias to /src
import {getOPtionsList, submitOptions} from '@/api/Home'
import {interpretationLayer} from '@/api/topoSortFuncs'

export default {
  name: 'Center',
  data() {
    return {
      inputOptionMessageVisible: false,//节点信息完善
      paramsDesc: "",//上传数据文件的desc
      baseNumber: 10,//基本的操作数
      options: [],//提供选择的功能节点
      option: "",//用户选择的功能节点
      //添加节点的弹出选择框
      addNodeDialogVisible: false,
      userContact: "2441086385",
      //节点
      nodes: [
        // Basic fields
        {id: 1, x: 1, y: 1, name: 'Start', type: 'start'},
        // You can add any generic fields to node, for example: description
        // It will be passed to @save, @editnode
        {id: 2, x: 500, y: 110, name: 'End', type: 'end', description: 'Im here'}
      ],
      //链接
      connections: [
        {
          source: {id: 1, position: 'right'},
          destination: {id: 2, position: 'left'},
          id: 1,
          type: 'pass',
        },
      ],
    }
  },
  created() {
    this.init()
  },
  methods: {
    //初始化方法
    async init() {
      const res = await getOPtionsList();
      console.log(res)
      this.options = res.data
    },
    //添加一个节点
    async addNode() {
      /**
       * 添加节点设计：
       *
       */
      let option = this.options.filter(e => {
        return e.optUrl === this.option
      })[0]
      let optionName = option.optName
      console.log(optionName)
      this.$refs.chart.add({
        id: +new Date(),
        x: 10,
        y: 10,
        name: 'Option',
        type: 'operation',
        optUrl: option.optUrl,
        approvers: [{id: 2, name: optionName + ""}]
      })
      this.addNodeDialogVisible = false
    },
    convertScheme(nodes, connections) {
    },
    //用户点击保存
    nodeSavePre() {
      this.inputOptionMessageVisible = false
      this.$refs.chart.save()
    },
    //保存操作
    async handleChartSave(nodes, connections) {
      //如果换了框架只需要在这个上面加一层格式转换层
      // convertScheme(nodes, connections)
      //调用解释层进行解释
      let submitOptionsRequest = await interpretationLayer(nodes, connections)
      //解释之后再添加一些信息
      submitOptionsRequest.userContact = this.userContact
      console.log(this.$store.getters.getFileMsg)
      submitOptionsRequest.dataFileName = this.$store.getters.getFileMsg.fileName
      submitOptionsRequest.dataFileFullPath = this.$store.getters.getFileMsg.filePath
      submitOptionsRequest.downloadUrl = this.$store.getters.getFileMsg.downloadUrl
      submitOptionsRequest.paramsDesc = this.paramsDesc
      if (submitOptionsRequest.dataFileName === null || submitOptionsRequest.dataFileName.length === 0) {
        this.$notify({
          title: '警告',
          message: '请上传数据',
          type: 'error',
          duration: 2000
        });
        return
      }
      const res = await submitOptions(submitOptionsRequest)
      console.log(res)
      alert(res.data)
    },
    handleEditNode(node) {
      if (node.id === 2) {
        console.log(node.description);
      }
    },
    handleEditConnection(connection) {
    },
    //处理删除节点
    handleDelete() {
    },
    //双击的事件处理
    handleDblClick(position) {
      // this.$refs.chart.add({
      //   id: +new Date(),
      //   x: position.x,
      //   y: position.y,
      //   name: 'New',
      //   type: 'operation',
      //   approvers: [],
      // });
    }
  }
}
</script>

<style scoped lang="less">
#center-chart {
  width: 100%;
  height: 100%;
  min-width: 400px;
  min-height: 400px;
  padding-top: 0.3rem;
  display: grid;
  grid-template-rows: 1fr 15fr;

  .button-wrap {
    width: 100%;
    height: 100%;
    border-radius: 4rem;
    display: grid;
    //align-items: center;
    align-content: center;
    background-color: #349ee9;

    .func-btn {
      //margin-top: -1rem;
    }

    .func-btn:hover {
      -webkit-transform: scale(1.1);
    }
  }

  .pannel {
    margin-top: 0.6rem;
    border-radius: 1rem;
    width: 100%;
  }

}
</style>
