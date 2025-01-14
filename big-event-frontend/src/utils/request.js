//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
//定义一个变量,记录公共的前缀  ,  baseURL
import { ElMessage } from 'element-plus'
import { useTokenStore } from '@/stores/token.js'
/* import { useRouter } from 'vue-router'
const router = useRouter() */
import router from '@/router'
const baseURL = '/api';
const instance = axios.create({ baseURL })
//添加请求拦截器

instance.interceptors.request.use(
    (config) => {
        //请求前的回调
        const tokenStore = useTokenStore()
        //判断有没有token
        if(tokenStore.token){
            //在请求头中添加token
            config.headers['Authorization'] = tokenStore.token
        }
        return config
    },
    (err) => {
        //请求错误的回调
        return Promise.reject(err)
    }
)

//添加响应拦截器
instance.interceptors.response.use(
    result => {
        //判断业务状态吗
        if (result.data.code === 0) {
            return result.data;
        } else {
            //操作失败
            //alert(result.data.msg?result.data.msg:'服务异常');
            ElMessage.error(result.data.msg ? result.data.msg : '服务异常');
            //将异步操作的状态转换为失败
            return Promise.reject(result.data);
        }
    },
    err => {
        //alert('服务异常');
        //如果响应状态码为401，则表示未登录，给出对应的提示，并跳转到登录页
        if(err.response.status === 401){
            ElMessage.error('未登录');
            router.push('/login')
        }else{
            ElMessage.error('服务异常');
        }
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;