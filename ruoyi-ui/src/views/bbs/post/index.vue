<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="发帖人ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入发帖人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="帖子标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入帖子标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类" prop="categoryId">
        <DialogSelect
          :configure="categoryIdConfigure"
          width="800px"
          v-model="queryParams.categoryId"
          :label="queryParams.name"
        ></DialogSelect>
      </el-form-item>
      <el-form-item label="浏览量" prop="viewCount">
        <el-input
          v-model="queryParams.viewCount"
          placeholder="请输入浏览量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['bbs:post:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bbs:post:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bbs:post:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bbs:post:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="postList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="postId" />
      <el-table-column label="发帖人姓名" align="center" prop="user.nickName" />
      <el-table-column label="帖子标题" align="center" prop="title" />
      <el-table-column label="帖子内容" align="center" prop="content" />
      <el-table-column label="分类" align="center" prop="categoryId" :formatter="categoryIdFormat"/>
      <el-table-column label="浏览量" align="center" prop="viewCount" />
      <el-table-column label="封面图路径" align="center" prop="coverImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.coverImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bbs:post:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bbs:post:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改论坛主题对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="发帖人ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入发帖人ID" />
        </el-form-item> -->
        <el-form-item label="帖子标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入帖子标题" />
        </el-form-item>
        <el-form-item label="帖子内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="分类" prop="categoryId">
          <DialogSelect
            :configure="categoryIdConfigure"
            width="800px"
            v-model="form.categoryId"
            :label="form.name"
          ></DialogSelect>
        </el-form-item>
        <!-- <el-form-item label="浏览量" prop="viewCount">
          <el-input v-model="form.viewCount" placeholder="请输入浏览量" />
        </el-form-item> -->
        <el-form-item label="封面图路径" prop="coverImage">
          <image-upload v-model="form.coverImage"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPost, getPost, delPost, addPost, updatePost } from "@/api/bbs/post"
import { listCategory } from "@/api/bbs/category"
export default {
  name: "Post",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 论坛主题表格数据
      postList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        title: null,
        content: null,
        categoryId: null,
        viewCount: null,
        coverImage: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "发帖人ID不能为空", trigger: "blur" }
        ],
        title: [
          { required: true, message: "帖子标题不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "帖子内容不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "发帖时间不能为空", trigger: "blur" }
        ],
      },
      //分类数据字典
      categoryList: [],
      //分类配置
      categoryIdConfigure: {
        title: "分类选择",
        column: [
          // { label: "学生id", id: "studentId"},
          { label: "分类名称", id: "name", search: true },
          { label: "排序权重", id: "orderNum", search: true }
        ],
        showLabel: "name",
        indexLabel: "categoryId",
        responseName: "rows",
        load(query) {
          return listCategory(query);
        },
      },
    }
  },
  created() {
    this.getList();
    // 获取分类数据字典
    listCategory().then(response => {
      this.categoryList = response.rows
    })
  },
  methods: {
    // 数据格式化
    categoryIdFormat(row, column){
      var result = this.selecOwntDictLabel(this.categoryList, 'categoryId', row.categoryId);
      return result.name;
    },
    /** 查询论坛主题列表 */
    getList() {
      this.loading = true
      listPost(this.queryParams).then(response => {
        this.postList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        postId: null,
        userId: null,
        title: null,
        content: null,
        categoryId: null,
        viewCount: null,
        coverImage: null,
        createTime: null,
        updateTime: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.postId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加论坛主题"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const postId = row.postId || this.ids
      getPost(postId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改论坛主题"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 将用户信息赋值在上传内容里
          this.form.userId = this.$store.getters.id;
          if (this.form.postId != null) {
            updatePost(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addPost(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const postIds = row.postId || this.ids
      this.$modal.confirm('是否确认删除论坛主题编号为"' + postIds + '"的数据项？').then(function() {
        return delPost(postIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bbs/post/export', {
        ...this.queryParams
      }, `post_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
