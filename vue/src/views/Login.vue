<template>
  <div class="login-container">
    <div class="login-box">
      <div
          style="padding: 50px 30px;background-color: white;margin-left: 50px;margin-bottom: 200px;border-radius:5px;box-shadow: 0 0 10px rgba(0,0,0.2)">

        <el-form ref="formRef" :rules="data.rules" :model="data.form" style="width: 350px">
          <div style="margin-bottom: 30px;font-size:25px;text-align:center;color:#0742b1;font-weight: bold">
            欢迎登陆管理系统
          </div>
          <el-form-item prop="username">
            <el-input size="large" v-model="data.form.username" placeholder="请输入用户名"
                      prefix-icon="User"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input show-password size="large" v-model="data.form.password" placeholder="请输入密码"
                      prefix-icon="Lock"></el-input>
          </el-form-item>
          <el-form-item prop="role">
            <el-select v-model="data.form.role" style="width: 100%" size="large">
              <el-option value="ADMIN" label="管理员" ></el-option>
              <el-option value="EMP" label="员工" ></el-option>
            </el-select>
          </el-form-item>
          <div style="margin-bottom: 20px">
            <el-button @click="login" size="large" style="width: 100%" type="primary">登 录</el-button>
          </div>
          <div style="text-align: right">还没有账号？<a style="color:#0742b1;text-decoration: none" href="/register">注
            册</a></div>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script setup>
import {reactive, ref} from "vue";
import request from "@/untils/request.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

const data = reactive({
  form: { role: 'ADMIN',},

  rules: {
    username: [
      {required: true, message: '请输入用户名', trigger: 'blur'},
    ],
    password: [
      {required: true, message: '请输入密码', trigger: 'blur'},
    ],
  }
})




const formRef = ref()

const login = () => {
  formRef.value.validate(valid => {
    if (valid) {
      request.post('/login', data.form).then(res => {
        if (res.code === '0' || res.code === '200') {
          localStorage.setItem('xm-pro-user', JSON.stringify(res.data))
          ElMessage.success('登录成功')
          setTimeout( () => {
            location.href = '/manager/home'
          },500)
        } else {
          ElMessage.error(res.msg || '登录失败')
        }
      })
    }
  })
}
</script>

<style scoped>
.login-container {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/login.jpg");
  background-size: cover;
  background-position: 0 0;
}

.login-box {
  width: 50%;
  height: 100%;
  right: 0;
  display: flex;
  align-items: center;
  position: absolute;
}
</style>