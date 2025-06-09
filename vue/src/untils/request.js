import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

const request = axios.create({
    baseURL: 'http://localhost:9090',  // 后台接口基础路径
    timeout: 30000  // 后台接口超时时间
});

// request 拦截器
// 可以自请求发送前对请求做一些处理
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config;
}, error => {
    return Promise.reject(error);
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
    let res = response.data;
    // 兼容服务端返回的字符串数据
    if (typeof res === 'string') {
        res = res ? JSON.parse(res) : res;
    }
    
    // 只有当状态码不是200或0且存在时，才认为是错误
    if (res.code && res.code !== '200' && res.code !== '0' && res.code !== 200 && res.code !== 0) {
        ElMessage.error(res.msg || '操作失败');
    }
    
    // 统一处理成功状态码
    if (res.code === '200' || res.code === 200) {
        res.code = '0';
    }
    
    return res;
},
    error => {
    console.error("请求错误", error);
    if (error.response && error.response.status === 404) {
        ElMessage.error('未找到请求接口');
    } else if (error.response && error.response.status === 500) {
        ElMessage.error('系统异常，请查看后端控制台报错');
    } else {
        ElMessage.error(error.message || '请求失败');
    }
    return Promise.reject(error);
}
);

export default request
