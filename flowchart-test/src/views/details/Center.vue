<template>
  <div id="center-chart">

    <el-button type="success" round
               @click="addNodeDialogVisible = true">
      Add
    </el-button>

    <el-button type="success" round @click="$refs.chart.remove()">
      Del
    </el-button>
    <el-button type="success" round @click="$refs.chart.editCurrent()">
      Edit
    </el-button>
    <el-button type="success" round @click="$refs.chart.save()">
      Save
    </el-button>
    <flowchart :nodes="nodes" :connections="connections" @editnode="handleEditNode"
               @dblclick="handleDblClick" @editconnection="handleEditConnection"
               @save="handleChartSave" ref="chart" class="pannel"
               width="99.5%" height="95.5%">
<!--      height="57.6rem"-->
    </flowchart>
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
        name: 'option',
        type: 'operation',
        optUrl: option.optUrl,
        approvers: [{id: 2, name: optionName + ""}]
      })
      this.addNodeDialogVisible = false
    },
    //保存操作
    async handleChartSave(nodes, connections) {
      // let nodes = nodes
      // console.log(nodes)
      // console.log(connections)
      //保存


      let submitOptionsRequest = await interpretationLayer(nodes, connections)
      console.log(submitOptionsRequest)
      submitOptionsRequest.userContact = this.userContact
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

<style lang="less" scoped>
#center-chart {
  width: 100%;
  height: 100%;
  min-width: 330px;
  min-height: 400px;
  .pannel {
    //margin-top: 1rem;

  }
}
</style>
