<template>
  <div>


    <!-- 弹出框 -->
    <el-dialog
      :title="'选择'+this.configure.title"
      :visible.sync="dialogVisible"
      append-to-body
      :width='width'
      :close-on-click-modal="false"
    >
      <el-form  :model="queryForm" :inline="true" @submit.native.prevent="list" >
        <el-form-item v-for="(search,index) in searchs" :key="index" :label="search.label" :prop="search.id">
          <el-input
            v-if="search.type == null"
            v-model="queryInput[index]"
            clearable
            @input="list"
          />
          <el-select
            v-else
            v-model="queryInput[index]"
            clearable
            @change="list"
          >
            <el-option
              v-for="dict in getOptions(search.id)"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
      </el-form>
        <!-- @row-click="clickGetSomeoneData" -->
      <el-table
        ref="table"
        :data="listData"
        label-width="auto"
        :highlight-current-row="true"

        @current-change="clickGetSomeoneData"
        v-loading="loading"
        :default-expand-all="false"
        :row-key="this.rowKey"
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
        empty-text="暂无数据"
        height="52vh"
        max-height="52vh"
      >
        <el-table-column v-for="(column,index) in configure.column" :key="index" :label="column.label" :property="column.id">
          <template slot-scope="scope" >
            <span  v-if="column.dicts == null">{{getValue(scope.row,column.id)}}</span>
            <dict-tag v-else :options="getOptions(column.id)" :value="getValue(scope.row,column.id)" />
          </template>
        </el-table-column>

      </el-table>

      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryForm.pageNum"
        :limit.sync="queryForm.pageSize"
        @pagination="list"
      />
    </el-dialog>
  </div>
</template>

<script>
/*
    Configure:{
      title:'店铺分类',
      column:[
        {label:'分类名称',id:'name',search:true},
        {label:'首页展示',id:'showIndex',search:true,dicts:'common_show_data'},
      ],
      showLabel:'name',
      indexLabel:'id',
      responseName:'rows',
      load(query){
        return listGoodcategory(query);
      },
      rowKey:"id",
      parentKey:"parentId"
    }


*/



export default {
  name: "DialogFormSelect",
  props: {
    value: {
      type: [String,Number,Array,Object],
      default: () => {},
    },
    configure: {
      type: Object,
      default: () => {},
    },
    width: {
      type: [String,Number],
      default: '600px',
    },
    tree: {
      type: Boolean,
      default: false,
    },
    filter: {
      type: Array,
    },
    submitControl:{
      type: Function,
      default: undefined,
    }

  },
  data() {
    return {
      queryInput:[],
      // 遮罩层
      loading: false,
      //弹框显示
      dialogVisible:false,
      //数据
      listData: [],
      // 总条数
      total: 0,
      //查询条件
      searchs:[],
      //可选项
      options:[],
      //表格row主键
      rowKey:"id",
      //表格row父级主键
      parentKey:"parentId",
      //查询数据的参数
      queryForm: {
        pageNum: 1,
        pageSize: 10,
      },
      // 选中某行的数据
      someoneData: null,
      //提交数据
      submitData:{},
      //输入数据
      inValue:[]
    };
  },
  watch: {
    value: function () {
      // if(this.inValue != this.value){
      //   this.inValue = this.value;
      // }
    },
  },
  created(){
    this.rowKey = this.configure.rowKey != null? this.configure.rowKey:this.rowKey
    this.parentKey = this.configure.parentKey != null? this.configure.parentKey:this.parentKey
    for (let i = 0; i < this.configure.column.length; i++) {
      const element = this.configure.column[i];
      if(element.search){
        var s = {
          label:element.label,
          id:element.searchName?element.searchName:element.id
        }
        if(element.dicts != null){
          s.type = 'select'
        }
        this.searchs.push(s)
      }
      if(element.dicts != null){
        this.getDicts(element.dicts).then((response) => {
          var dict = {
            name:element.id,
            data:response.data
          }
          // var str = '{"name":"'+ element.id +'","data":'+JSON.stringify(response.data)+'}'
          this.options.push(dict)
        });
      }


    }

  },
  methods: {

    visible(bool){
      this.dialogVisible=bool
      if(bool){
        this.list();
      }
      else{
        this.cancel();
      }
    },

    // 获取字典选项
    getOptions(columnName){
      for (let i = 0; i < this.options.length; i++) {
        const element = this.options[i];
        if(element.name == columnName){
          return element.data
        }
      }
    },

    // 获取字典值
    getValue(row,columnName){
      return eval("row."+columnName)
    },

    /** 查询列表 */
    list() {
      this.loading=true
      var val = null
      var str = "{"
      this.searchs.forEach((element,index) => {
        if(typeof this.queryInput[index] == 'number'){
           val = this.queryInput[index]
        }
        else if(this.queryInput[index] == null){
          val = null
        }
        else{
          val = '"'+this.queryInput[index]+'"'
        }
        if(index == 0){
          str = str +'"'+ element.id + '":' + val
        }
        else{
          str = str + ', "' + element.id + '":' + val
        }
      });
      str = str + "}"

      var queryInput = JSON.parse(str)
      if(this.tree){
        this.queryForm = {}
      }
      this.queryForm=Object.assign(this.queryForm,queryInput)
      if(this.configure.defaultQuery != null){
        this.queryForm=Object.assign(this.queryForm,this.configure.defaultQuery)
      }
      this.configure.load(this.queryForm).then(response =>{
        var res = eval('response.'+this.configure.responseName)
        if(this.filter != null &&this.filter.length>0){
          var filterRes = [];
          filterRes = res.filter((item)=>{
            return this.filter.indexOf(item.id)>-1
          })
          res = filterRes
        }

        if(this.tree){
          this.listData = this.handleTree(
            res,
            this.rowKey,
            this.parentKey
          );
          this.total = 0;
        }
        else{
          this.listData = res;
          this.total = response.total != null?response.total:0;
        }

        //选中已选值
        if(this.value != null ){
          res.forEach(element => {
            if(Array.isArray(this.value)){
              this.value.forEach(inv => {
                var key =  eval('inv.'+this.rowKey) || inv
                if(key == eval('element.'+this.rowKey)){
                  this.$refs.table.setCurrentRow(element);

                }
              });
            }
            else{
              var key =  this.value
              if(key == eval('element.'+this.rowKey)){
                this.$refs.table.setCurrentRow(element);
              }

            }
          });
        }




        this.loading=false
      });


    },
    //获取某行数据
    clickGetSomeoneData(row) {
      this.someoneData = row;

    },
    //关闭选择的页面
    cancel() {
      this.someoneData = null;
      this.dialogVisible = false;
      this.$refs.table.setCurrentRow();
    },
    //提交选择的数据
    submit() {
      this.submitData = JSON.parse(JSON.stringify(this.someoneData)) ;
      if(this.submitData != null){
        if(this.submitControl != undefined){
          var res = this.submitControl(this.submitData)
          if(!res){
            return null
          }
        }
        this.$emit("input",this.submitData);
        this.$emit("submitData",this.submitData);
        this.someoneData = null;
        this.dialogVisible = false;
        this.$refs.table.setCurrentRow();
      }
      else{
        this.$message.error('请选择数据');
      }

    },
  },
};
</script>

<style type="text/css" scoped>
</style>
