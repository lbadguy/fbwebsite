<template>
  <div>
    <el-card shadow="hover" :body-style="{padding: '20px'}">
      <template #header>
        <div class="card-header">
          <span>{{ form.readerId ? '编辑读者' : '添加读者' }}</span>
        </div>
      </template>
      
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入读者姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="借书证号" prop="cardId">
              <el-input v-model="form.cardId" placeholder="请输入借书证号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="form.gender">
                <el-radio :label="1">男</el-radio>
                <el-radio :label="2">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="读者类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择读者类型" style="width: 100%;">
                <el-option :value="1" label="学生"></el-option>
                <el-option :value="2" label="教师"></el-option>
                <el-option :value="3" label="其他"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="电话" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入联系电话"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱地址"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio :label="1">正常</el-radio>
                <el-radio :label="0">禁用</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大借阅量" prop="maxBorrowNum">
              <el-input-number v-model="form.maxBorrowNum" :min="1" :max="20"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" :rows="3" placeholder="请输入备注信息"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-form-item>
          <el-button type="primary" @click="submitForm">保存</el-button>
          <el-button @click="$router.push('/manager/reader')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import request from '@/untils/request';

export default {
  name: 'ReaderAdd',
  data() {
    return {
      form: {
        readerId: null,
        name: '',
        cardId: '',
        gender: 1,
        type: 1,
        phone: '',
        email: '',
        status: 1,
        maxBorrowNum: 5,
        remark: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入读者姓名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        cardId: [
          { required: true, message: '请输入借书证号', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        type: [
          { required: true, message: '请选择读者类型', trigger: 'change' }
        ],
        phone: [
          { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号码', trigger: 'blur' }
        ],
        email: [
          { pattern: /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/, message: '请输入正确的邮箱地址', trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    const readerId = this.$route.query.readerId;
    if (readerId) {
      this.loadReaderInfo(readerId);
    }
  },
  methods: {
    // 加载读者信息
    loadReaderInfo(readerId) {
      request.get('/reader/' + readerId).then(res => {
        if (res.code === '0') {
          this.form = res.data;
        } else {
          this.$message.error(res.msg || '加载读者信息失败');
        }
      });
    },
    // 提交表单
    submitForm() {
      this.$refs.formRef.validate(valid => {
        if (valid) {
          const url = this.form.readerId ? '/reader/update' : '/reader/add';
          const method = this.form.readerId ? 'put' : 'post';
          
          request[method](url, this.form).then(res => {
            if (res.code === '0') {
              this.$message.success('保存成功');
              this.$router.push('/manager/reader');
            } else {
              this.$message.error(res.msg || '保存失败');
            }
          });
        } else {
          return false;
        }
      });
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