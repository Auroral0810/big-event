//导入request.js请求工具
import request from '@/utils/request'
//提供调用注册结构的函数
export const userRegisterService = (registerData) => {
    //借助于urlSearchParams完成传递
    const params = new URLSearchParams();
    for (let key in registerData) {
        params.append(key, registerData[key]);
    }
    return request.post('/user/register', params);
}

//提供调用登陆接口的函数
export const userLoginService = (loginData) => {
        //借助于urlSearchParams完成传递
        const params = new URLSearchParams();
        for (let key in loginData) {
            params.append(key, loginData[key]);
        }
    return request.post('/user/login', params);
}