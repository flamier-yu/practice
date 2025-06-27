<template>
  <div class="container">
    <el-carousel indicator-position="outside">
      <el-carousel-item v-for="item in 4" :key="item">
        <h3>{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
    <div class="content">
      <div class="content_main">
        <div class="content_main_one" v-for="item in postList" :key="item.postId" :value="item.title">
          <div class="avatar-wrapper">
            <!-- <img :src="item.user.nickName" class="user-avatar"> -->
            <span class="user-nickname"> {{ item.user.nickName }} </span>
          </div>
          <div class="content_main_post">
            <div class="content_main_post_title">{{item.title}}</div>
            <p v-html="item.content" ></p>
            <image-preview :src="item.coverImage" :width="150" :height="150"/>
          </div>
          <div class="content_main_like"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { listPost, getPost, delPost, addPost, updatePost } from "@/api/bbs/post"

export default {
  data () {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 论坛主题表格数据
      postList: [],
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
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询论坛主题列表 */
    getList() {
      this.loading = true
      listPost(this.queryParams).then(response => {
        this.postList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
  },
}
</script>

<style type="text/css" scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  * {
    box-sizing: border-box; /* 统一盒模型 */
    margin: 0;
    padding: 0;
  }

  body {
    font-family: 'Segoe UI', Arial, sans-serif;
    line-height: 1.6;
    color: #333;
    background-color: #f8f9fa;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
  }

  /* 居中容器设置 */
  .container {
    width: 90%; /* 默认宽度 */
    max-width: 1200px; /* 最大宽度限制 */
    margin: 0 auto; /* 水平居中 */
    padding: 0 15px; /* 内容区两侧留白 */
  }

  .content{
    display: flex;
    align-items: center;
    justify-content: center;
    height: 1000px;
  }

  .content_main{
    width: 90%;
    /* background-color: blue; */
    color: black;
    margin-top: 100px;
  }

  .content_main_one{
    width: 100%;
    margin-right: 0px;
    padding-right: 0px;
    &:hover {
      cursor: pointer;
      transition: background .3s;
      background: #d3dce6;
    }
  }

  .avatar-wrapper {

    .user-avatar {
      cursor: pointer;
      width: 30px;
      height: 30px;
      border-radius: 50%;
    }

    .user-nickname{
      bottom: 10px;
      font-size: 18px;
      font-weight: bold;
      color: #595959;
    }
  }

  .content_main_post{
    width: 100%;
    height: 60%;

    .content_main_post_title{
      font-size: 18px;
      font-weight: bold;
    }
  }

  .content_main_like{
    width: 100%;
    height: 20%;
  }
</style>
