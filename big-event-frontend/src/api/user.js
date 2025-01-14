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

//获取用户详细信息
export const userInfoService = ()=>{
    return request.get('/user/userInfo')
}

//更新用户信息
export const userInfoUpdateService = (userInfo) => {
    return request.put('/user/update', userInfo)
}

//上传头像，使用patch请求
export const uploadAvatarService = (imgUrl) => {
    const params = new URLSearchParams()
    params.append('avatarUrl', imgUrl)
    return request.patch('/user/updateAvatar', params)
}

//修改密码
export const userResetPasswordService = (passwordData) => {
    //打印调试信息，看看发送的请求
    console.log(passwordData)
    return request.patch('/user/updatePwd', passwordData)
}
