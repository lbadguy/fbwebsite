<template>
  <div>
    <!-- 卡片视图 -->
    <el-card shadow="hover" :body-style="{padding: '20px'}">
      <!-- 搜索区域 -->
      <div style="margin: 10px 0">
        <el-row :gutter="10">
          <el-col :span="6">
            <el-input v-model="name" placeholder="读者姓名" clearable @clear="load"></el-input>
          </el-col>
          <el-col :span="6">
            <el-input v-model="cardId" placeholder="借书证号" clearable @clear="load"></el-input>
          </el-col>
          <el-col :span="6">
            <el-input v-model="phone" placeholder="联系电话" clearable @clear="load"></el-input>
          </el-col>
          <el-col :span="6">
            <div style="text-align: right">
              <el-button type="primary" @click="load">搜索</el-button>
              <el-button type="success" @click="handleAdd">新增</el-button>
            </div>
          </el-col>
        </el-row>
      </div>

      <!-- 读者列表 -->
      <el-table :data="tableData" border stripe style="width: 100%" v-loading="loading">
        <el-table-column prop="readerId" label="ID" width="60"></el-table-column>
        <el-table-column prop="name" label="姓名" width="120"></el-table-column>
        <el-table-column prop="cardId" label="借书证号" width="120"></el-table-column>
        <el-table-column prop="gender" label="性别" width="60">
          <template #default="scope">
            {{ scope.row.gender === '1' ? '男' : '女' }}
          </template>
        </el-table-column>
        <el-table-column prop="type" label="读者类型" width="100">
          <template #default="scope">
            <el-tag v-if="scope.row.type === '1'" type="success">学生</el-tag>
            <el-tag v-if="scope.row.type === '2'" type="warning">教师</el-tag>
            <el-tag v-if="scope.row.type === '3'" type="info">其他</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="电话" width="120"></el-table-column>
        <el-table-column prop="email" label="邮箱" width="180"></el-table-column>
        <el-table-column prop="status" label="状态" width="80">
          <template #default="scope">
            <el-tag v-if="scope.row.status === 1" type="success">正常</el-tag>
            <el-tag v-if="scope.row.status === 0" type="danger">禁用</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180"></el-table-column>
        <el-table-column label="操作" fixed="right" width="220">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleBorrowHistory(scope.row)">借阅记录</el-button>
            <el-button type="success" size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-popconfirm title="确定删除?" @confirm="handleDelete(scope.row.readerId)">
              <template #reference>
                <el-button type="danger" size="small">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区域 -->
      <div style="margin-top: 20px; display: flex; justify-content: center">
        <el-pagination
            :current-page="currentPage"
            :page-size="pageSize"
            :page-sizes="[5, 10, 20, 50]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import request from '@/untils/request';

export default {
  name: 'Reader',
  data() {
    return {
      name: '',
      cardId: '',
      phone: '',
      loading: false,
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      total: 0
    };
  },
  created() {
    this.load();
  },
  methods: {
    // 加载读者数据
    load() {
      this.loading = true;
      request.get('/reader/page', {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          name: this.name,
          cardId: this.cardId,
          phone: this.phone
        }
      }).then(res => {
        console.log('读者数据:', res.data);
        // 确保使用正确的字段名
        if (res.code === '0' || res.code === '200') {
          if (res.data && res.data.list) {
            this.tableData = res.data.list;
            this.total = res.data.total;
          } else if (res.data && res.data.records) {
            this.tableData = res.data.records;
            this.total = res.data.total;
          } else {
            this.tableData = [];
            this.total = 0;
          }
        } else {
          this.$message.error(res.msg || '获取读者数据失败');
          this.tableData = [];
          this.total = 0;
        }
        this.loading = false;
      }).catch(err => {
        console.error('加载读者数据出错:', err);
        this.$message.error('加载读者数据出错');
        this.loading = false;
        this.tableData = [];
        this.total = 0;
      });
    },
    // 处理添加读者
    handleAdd() {
      this.$router.push('/manager/reader/add');
    },
    // 处理编辑读者
    handleEdit(row) {
      this.$router.push({
        path: '/manager/reader/add',
        query: {
          readerId: row.readerId
        }
      });
    },
    // 处理删除读者
    handleDelete(readerId) {
      request.delete('/reader/' + readerId).then(res => {
        if (res.code === '0' || res.code === '200') {
          this.$message.success('删除成功');
          this.load();
        } else {
          this.$message.error(res.msg || '删除失败');
        }
      }).catch(err => {
        console.error('删除读者出错:', err);
        this.$message.error('删除读者出错');
      });
    },
    // 查看借阅记录
    handleBorrowHistory(row) {
      this.$router.push({
        path: '/manager/borrow',
        query: {
          readerId: row.readerId
        }
      });
    },
    // 处理每页显示条数变化
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    // 处理页码变化
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.load();
    }
  }
};
</script>

<style scoped>
.el-card {
  margin: 20px;
}
</style> 