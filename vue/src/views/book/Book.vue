<template>
  <div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" class="ml-5" clearable></el-input>
      <el-button type="primary" class="ml-5" @click="load">查询</el-button>
      <el-button type="warning" class="ml-5" @click="reset">重置</el-button>
    </div>
    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="bookId" label="ID" width="80"></el-table-column>
      <el-table-column prop="isbn" label="ISBN" width="150"></el-table-column>
      <el-table-column prop="title" label="书名"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="publisher" label="出版社"></el-table-column>
      <el-table-column prop="categoryName" label="分类"></el-table-column>
      <el-table-column prop="price" label="价格"></el-table-column>
      <el-table-column prop="status" label="状态">
        <template #default="scope">
          <el-tag v-if="scope.row.status === 1" type="success">可借</el-tag>
          <el-tag v-else-if="scope.row.status === 0" type="info">已借出</el-tag>
          <el-tag v-else-if="scope.row.status === 2" type="danger">损坏</el-tag>
          <el-tag v-else-if="scope.row.status === 3" type="warning">丢失</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="240" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="success" size="small" @click="handleView(scope.row)" v-if="scope.row.status === 1">借阅</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 50]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from "@/untils/request";

export default {
  name: "Book",
  data() {
    return {
      tableData: [],
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/book/page", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          title: this.search,
          isbn: this.search,
          author: this.search
        }
      }).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    add() {
      this.$router.push("/manager/book/add")
    },
    handleEdit(row) {
      this.$router.push("/manager/book/edit/" + row.bookId)
    },
    handleView(row) {
      this.$router.push("/manager/borrow/add?bookId=" + row.bookId)
    },
    handleDelete(row) {
      this.$confirm('确认删除该图书?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.delete("/book/" + row.bookId).then(res => {
          if (res.code === '0') {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.load()
          } else {
            this.$message({
              type: 'error',
              message: res.msg
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    reset() {
      this.search = ''
      this.load()
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.load()
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.load()
    }
  }
}
</script>

<style scoped>
.ml-5 {
  margin-left: 5px;
}
</style> 