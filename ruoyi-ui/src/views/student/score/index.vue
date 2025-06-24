<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生" prop="studentId">
        <DialogSelect
          :configure="studentIdConfigure"
          width="800px"
          v-model="queryParams.studentId"
          :label="queryParams.name"
        ></DialogSelect>
      </el-form-item>
      <el-form-item label="课程" prop="courseId">
        <DialogSelect
          :configure="courseIdConfigure"
          width="800px"
          v-model="queryParams.courseId"
          :label="queryParams.courseName"
        ></DialogSelect>
      </el-form-item>
      <el-form-item label="平时成绩" prop="regularScore">
        <el-input
          v-model="queryParams.regularScore"
          placeholder="请输入平时成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最终成绩" prop="finalScore">
        <el-input
          v-model="queryParams.finalScore"
          placeholder="请输入最终成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总成绩" prop="totalScore">
        <el-input
          v-model="queryParams.totalScore"
          placeholder="请输入总成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学期" prop="semester">
        <el-input
          v-model="queryParams.semester"
          placeholder="请输入学期"
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
          v-hasPermi="['student:score:add']"
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
          v-hasPermi="['student:score:edit']"
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
          v-hasPermi="['student:score:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['student:score:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="分数id主键" align="center" prop="scoreId" /> -->
      <el-table-column label="学生" align="center" prop="studentId" :formatter="studentIdFormat"/>
      <el-table-column label="课程" align="center" prop="courseId" :formatter="courseIdFormat"/>
      <el-table-column label="平时成绩" align="center" prop="regularScore" />
      <el-table-column label="最终成绩" align="center" prop="finalScore" />
      <el-table-column label="总成绩" align="center" prop="totalScore" />
      <el-table-column label="学期" align="center" prop="semester" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['student:score:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['student:score:remove']"
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

    <!-- 添加或修改分数对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生" prop="studentId">
          <DialogSelect
            :configure="studentIdConfigure"
            width="800px"
            v-model="form.studentId"
            :label="form.name"
          ></DialogSelect>
        </el-form-item>
        <el-form-item label="课程" prop="courseId">
          <DialogSelect
            :configure="courseIdConfigure"
            width="800px"
            v-model="form.courseId"
            :label="form.courseName"
          ></DialogSelect>
        </el-form-item>
        <el-form-item label="平时成绩" prop="regularScore">
          <el-input v-model="form.regularScore" placeholder="请输入平时成绩" />
        </el-form-item>
        <el-form-item label="最终成绩" prop="finalScore">
          <el-input v-model="form.finalScore" placeholder="请输入最终成绩" />
        </el-form-item>
        <el-form-item label="总成绩" prop="totalScore">
          <el-input v-model="form.totalScore" placeholder="请输入总成绩" />
        </el-form-item>
        <el-form-item label="学期" prop="semester">
          <el-input v-model="form.semester" placeholder="请输入学期" />
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
import { listScore, getScore, delScore, addScore, updateScore } from "@/api/student/score"
import { listCourse } from "@/api/student/course"
import { listStudent } from "@/api/student/student";

export default {
  name: "Score",
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
      // 分数表格数据
      scoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        courseId: null,
        regularScore: null,
        finalScore: null,
        totalScore: null,
        semester: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // 获取学生数据和课程数据
      studentList: [],
      courseList: [],
      //学生配置
      studentIdConfigure: {
        title: "学生选择",
        column: [
          // { label: "学生id", id: "studentId"},
          { label: "学号", id: "studentNo", search: true },
          { label: "学生名称", id: "name", search: true },
          { label: "电话号码",id: "phone", search: true }
        ],
        showLabel: "name",
        indexLabel: "studentId",
        responseName: "rows",
        load(query) {
          return listStudent(query);
        },
      },
      //课程配置
      courseIdConfigure: {
        title: "课程选择",
        column: [
          { label: "课程编号", id: "courseCode", search: true },
          { label: "课程名称", id: "courseName", search: true },
          { label: "课程学分",id: "credit"},
          { label: "老师名称", id: "teacher", search: true }
        ],
        showLabel: "courseName",
        indexLabel: "courseId",
        responseName: "rows",
        load(query) {
          return listCourse(query);
        },
      },
    }
  },
  created() {
    this.getList(),
    // 获取学生数据和课程数据
    listStudent().then(response => {
      this.studentList = response.rows
    })
    listCourse().then(response => {
      this.courseList = response.rows
    })
  },
  methods: {
    // 数据格式化
    studentIdFormat(row, column){
      var result = this.studentList.find(item => item.studentId == row.studentId);
      return result ? result.name : '';
    },
    courseIdFormat(row, column){
      var result = this.courseList.find(item => item.coursetId == row.coursetId);
      return result ? result.courseName : '';
    },
    /** 查询分数列表 */
    getList() {
      this.loading = true
      listScore(this.queryParams).then(response => {
        this.scoreList = response.rows
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
        scoreId: null,
        studentId: null,
        courseId: null,
        regularScore: null,
        finalScore: null,
        totalScore: null,
        semester: null
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
      this.ids = selection.map(item => item.scoreId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加分数"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const scoreId = row.scoreId || this.ids
      getScore(scoreId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改分数"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scoreId != null) {
            updateScore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addScore(this.form).then(response => {
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
      const scoreIds = row.scoreId || this.ids
      this.$modal.confirm('是否确认删除分数编号为"' + scoreIds + '"的数据项？').then(function() {
        return delScore(scoreIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('student/score/export', {
        ...this.queryParams
      }, `score_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
