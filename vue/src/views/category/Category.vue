<template>
  <div>
    <el-card shadow="hover" :body-style="{padding: '20px'}">
      <template #header>
        <div class="card-header">
          <span>图书分类管理</span>
          <el-button type="success" @click="handleAdd">添加分类</el-button>
        </div>
      </template>
      
      <!-- 分类列表 -->
      <el-table :data="tableData" border stripe style="width: 100%" v-loading="loading">
        <el-table-column prop="categoryId" label="ID" width="80"></el-table-column>
        <el-table-column prop="name" label="分类名称" width="200"></el-table-column>
        <el-table-column prop="parentName" label="父分类" width="120"></el-table-column>
        <el-table-column prop="description" label="描述"></el-table-column>
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-popconfirm title="确定删除?" @confirm="handleDelete(scope.row.categoryId)">
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
    
    <!-- 添加/编辑分类的对话框 -->
    <el-dialog v-model="dialogVisible" :title="form.categoryId ? '编辑分类' : '添加分类'" width="30%">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入分类名称"></el-input>
        </el-form-item>
        <el-form-item label="父分类">
          <el-select v-model="form.parentId" placeholder="请选择父分类" clearable style="width: 100%">
            <el-option
              v-for="item in categoryOptions"
              :key="item.categoryId"
              :label="item.name"
              :value="item.categoryId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" :rows="3" placeholder="请输入分类描述"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import request from '@/untils/request';

export default {
  name: 'Category',
  data() {
    return {
      loading: false,
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
      dialogVisible: false,
      categoryOptions: [],
      form: {
        categoryId: null,
        name: '',
        parentId: null,
        description: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入分类名称', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
        ],
        description: [
          { max: 200, message: '长度不能超过 200 个字符', trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    this.load();
    this.loadAllCategories();
  },
  methods: {
    // 加载分类数据
    load() {
      this.loading = true;
      request.get('/category/page', {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize
        }
      }).then(res => {
        if (res.code === '0' && res.data) {
          this.tableData = res.data.list || [];
          this.total = res.data.total || 0;
        } else {
          this.tableData = [];
          this.total = 0;
        }
        this.loading = false;
      }).catch(() => {
        this.loading = false;
      });
    },
    // 加载所有分类，用于下拉选择
    loadAllCategories() {
      request.get('/category/list').then(res => {
        if (res.code === '0') {
          this.categoryOptions = res.data || [];
        }
      });
    },
    // 处理添加分类
    handleAdd() {
      this.form = {
        categoryId: null,
        name: '',
        parentId: null,
        description: ''
      };
      this.dialogVisible = true;
    },
    // 处理编辑分类
    handleEdit(row) {
      this.form = { ...row };
      this.dialogVisible = true;
    },
    // 处理删除分类
    handleDelete(categoryId) {
      request.delete('/category/' + categoryId).then(res => {
        if (res.code === '0') {
          this.$message.success('删除成功');
          this.load();
        } else {
          this.$message.error(res.msg || '删除失败');
        }
      });
    },
    // 提交表单
    submitForm() {
      this.$refs.formRef.validate(valid => {
        if (valid) {
          const url = this.form.categoryId ? '/category/update' : '/category/add';
          const method = this.form.categoryId ? 'put' : 'post';
          
          request[method](url, this.form).then(res => {
            if (res.code === '0') {
              this.$message.success('保存成功');
              this.dialogVisible = false;
              this.load();
              this.loadAllCategories();
            } else {
              this.$message.error(res.msg || '保存失败');
            }
          });
        } else {
          return false;
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
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style> 