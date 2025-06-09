<template>
  <div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增借阅</el-button>
      <el-button type="success" @click="goReturn">办理归还</el-button>
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" class="ml-5" clearable></el-input>
      <el-button type="primary" class="ml-5" @click="load">查询</el-button>
      <el-button type="warning" class="ml-5" @click="reset">重置</el-button>
    </div>
    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="borrowId" label="ID" width="80"></el-table-column>
      <el-table-column prop="bookTitle" label="图书"></el-table-column>
      <el-table-column prop="readerName" label="读者"></el-table-column>
      <el-table-column prop="cardId" label="证件号"></el-table-column>
      <el-table-column prop="borrowDate" label="借阅日期" width="100">
        <template #default="scope">
          {{ formatDate(scope.row.borrowDate) }}
        </template>
      </el-table-column>
      <el-table-column prop="dueDate" label="应还日期" width="100">
        <template #default="scope">
          {{ formatDate(scope.row.dueDate) }}
        </template>
      </el-table-column>
      <el-table-column prop="returnDate" label="实际归还日期" width="100">
        <template #default="scope">
          {{ formatDate(scope.row.returnDate) }}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" width="100">
        <template #default="scope">
          <el-tag v-if="scope.row.status === 1" type="info">已借出</el-tag>
          <el-tag v-else-if="scope.row.status === 2" type="success">已归还</el-tag>
          <el-tag v-else-if="scope.row.status === 3" type="warning">已续借</el-tag>
          <el-tag v-else-if="scope.row.status === 4" type="danger">已逾期</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="fine" label="罚款"></el-table-column>
      <el-table-column fixed="right" label="操作" width="260" align="center">
        <template #default="scope">
          <el-button type="success" size="small" @click="handleReturn(scope.row)" v-if="scope.row.status === 1 || scope.row.status === 3 || scope.row.status === 4">归还</el-button>
          <el-button type="primary" size="small" @click="handleRenew(scope.row)" v-if="scope.row.status === 1">续借</el-button>
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

    <!-- 归还对话框 -->
    <el-dialog title="归还图书" v-model="returnDialogVisible" width="30%">
      <el-form label-width="120px">
        <el-form-item label="图书">
          <span>{{ returnForm.bookTitle }}</span>
        </el-form-item>
        <el-form-item label="读者">
          <span>{{ returnForm.readerName }}</span>
        </el-form-item>
        <el-form-item label="借阅日期">
          <span>{{ formatDate(returnForm.borrowDate) }}</span>
        </el-form-item>
        <el-form-item label="应还日期">
          <span>{{ formatDate(returnForm.dueDate) }}</span>
        </el-form-item>
        <el-form-item label="实际归还日期">
          <span>{{ formatDate(new Date()) }}</span>
        </el-form-item>
        <el-form-item label="罚款" v-if="isOverdue">
          <span class="text-danger">{{ fine }} 元</span>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="returnDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="confirmReturn">确定归还</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/untils/request";

export default {
  name: "Borrow",
  data() {
    return {
      tableData: [],
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      returnDialogVisible: false,
      returnForm: {},
      isOverdue: false,
      fine: 0
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/borrow/page", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          bookTitle: this.search,
          readerName: this.search,
          cardId: this.search
        }
      }).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    add() {
      this.$router.push("/manager/borrow/add")
    },
    goReturn() {
      this.$router.push("/manager/borrow/return")
    },
    handleReturn(row) {
      this.returnForm = row
      this.returnDialogVisible = true
      
      // 检查是否逾期
      const currentDate = new Date()
      const dueDate = new Date(row.dueDate)
      this.isOverdue = currentDate > dueDate
      
      if (this.isOverdue) {
        // 计算罚款
        const diffTime = Math.abs(currentDate - dueDate)
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))
        this.fine = diffDays * 1.0 // 每天1元
      }
    },
    confirmReturn() {
      const user = JSON.parse(localStorage.getItem("user") || "{}")
      
      request.post("/borrow/return", null, {
        params: {
          borrowId: this.returnForm.borrowId,
          operatorId: user.id
        }
      }).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "归还成功"
          })
          this.returnDialogVisible = false
          this.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    },
    handleRenew(row) {
      const user = JSON.parse(localStorage.getItem("user") || "{}")
      
      this.$confirm('确定续借该图书?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.post("/borrow/renew", null, {
          params: {
            borrowId: row.borrowId,
            operatorId: user.id
          }
        }).then(res => {
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "续借成功"
            })
            this.load()
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
        })
      }).catch(() => {})
    },
    handleDelete(row) {
      this.$confirm('确认删除该借阅记录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.delete("/borrow/" + row.borrowId).then(res => {
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
      }).catch(() => {});
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
    },
    formatDate(date) {
      if (!date) return ''
      const d = new Date(date)
      const year = d.getFullYear()
      const month = (d.getMonth() + 1).toString().padStart(2, '0')
      const day = d.getDate().toString().padStart(2, '0')
      return `${year}-${month}-${day}`
    }
  }
}
</script>

<style scoped>
.ml-5 {
  margin-left: 5px;
}
.text-danger {
  color: #F56C6C;
  font-weight: bold;
}
</style> 