<template>
  <div>

    <el-select v-model="indexValue" @click.native="openDialog" placeholder="请选择" clearable @clear="clear" :disabled='disabled'>
      <el-option
        :label="showValue"
        :value="indexValue"
      ></el-option>
    </el-select>

    <DialogFormSelect
      ref="dialogForm"
      :configure="configure"
      :width="width"
      @submitData="submit($event)"
      :tree="tree"
      :filter="filter"
      v-model="indexValue"
      :submitControl="submitControl"

    ></DialogFormSelect>

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
      defaultQuery:{
        state:"1"
      }
    }


*/
import DialogFormSelect from "@/components/DialogSelect/dialog";

export default {
  name: "DialogSelect",
  components: {
    DialogFormSelect
  },
  props: {
    value: {
      type: [Object,Number,String,Array],
      default: null,
    },
    configure: {
      type: Object,
      default: () => {},
    },
    width: {
      type: [String,Number],
      default: '600px',
    },
    label: {
      type: String,
      default: null,
    },
    tree: {
      type: Boolean,
      default: false,
    },
    filter: {
      type: Array,
    },
    disabled: {
      type: Boolean,
      default: false,
    },
    submitControl:{
      type: Function,
      default: undefined,
    }
  },
  computed: {
    // inValue() {
    //   if (this.value) {
    //     return Array.isArray(this.value) ? this.value : [this.value];
    //   } else {
    //     return [];
    //   }
    // },
  },
  data() {
    return {
      //实际值
      indexValue:null,
      //显示值
      showValue:null,
    };
  },
  watch: {
    value: function () {
      if(this.indexValue != this.value){
        this.indexValue = this.value;
        if(this.indexValue == null){
          this.showValue = null
        }
        else{
          this.showValue = this.label?this.label:this.indexValue
        }
      }

    },
  },
  created(){

  },
  methods: {

    // 点击弹出框
    openDialog() {
      if (!this.disabled) {
        this.$refs['dialogForm'].visible(true)
      }
    },

    //提交选择的数据
    submit(data) {
      this.indexValue = eval("data."+this.configure.indexLabel)
      this.showValue = eval("data."+this.configure.showLabel)
      this.$emit("input",this.indexValue);
      this.$emit("selectChange",data);
    },

    clear(){
      this.indexValue = null
      this.showValue = null
      this.$emit("input",this.indexValue);
      this.$emit("selectChange",{});
    },

  },
};
</script>

<style type="text/css" scoped>
</style>
