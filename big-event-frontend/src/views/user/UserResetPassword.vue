<script setup>
import { Lock } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const passwordForm = ref({
    old_pwd: '',
    new_pwd: '',
    re_pwd: ''
})

const rules = {
    oldPassword: [
        { required: true, message: '请输入原密码', trigger: 'blur' },
        { min: 6, max: 15, message: '密码长度为6-15位', trigger: 'blur' }
    ],
    newPassword: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
        { min: 6, max: 15, message: '密码长度为6-15位', trigger: 'blur' }
    ],
    confirmPassword: [
        { required: true, message: '请再次输入新密码', trigger: 'blur' },
        { min: 6, max: 15, message: '密码长度为6-15位', trigger: 'blur' },
        {
            validator: (rule, value, callback) => {
                if (value !== passwordForm.value.newPassword) {
                    callback(new Error('两次输入的密码不一致'))
                } else {
                    callback()
                }
            },
            trigger: 'blur'
        }
    ]
}

import { userResetPasswordService } from '@/api/user.js'
import { useTokenStore } from '@/stores/token.js'
import router from '@/router'
const tokenStore = useTokenStore()
const formRef = ref()
const resetPassword = async () => {
    //判断
    //显示确认框
    ElMessageBox.confirm(
        '您确定要修改密码吗？',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',

        }
    )
        .then(async () => {
            //调用修改密码接口
            let result = await userResetPasswordService(passwordForm.value)
            ElMessage.success(result.message ? result.message : '修改密码成功，请重新登陆')
                        //修改完成后重新登陆
                        router.push('/login')
        })
        .catch(() => {
            ElMessage.info(result.message ? result.message : '已取消修改密码')
        })
}
</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>修改密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form ref="formRef" :model="passwordForm" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="原密码" prop="old_pwd">
                        <el-input v-model="passwordForm.old_pwd" :prefix-icon="Lock" type="password"
                            show-password></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="new_pwd">
                        <el-input v-model="passwordForm.new_pwd" :prefix-icon="Lock" type="password"
                            show-password></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="re_pwd">
                        <el-input v-model="passwordForm.re_pwd" :prefix-icon="Lock" type="password"
                            show-password></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="resetPassword">修改密码</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>

<style lang="scss" scoped>
.page-container {
    margin: 20px;
}

.header {
    display: flex;
    align-items: center;
    justify-content: space-between;
}
</style>