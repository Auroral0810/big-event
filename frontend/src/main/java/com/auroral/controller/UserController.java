package com.auroral.controller;

import com.auroral.pojo.Result;
import com.auroral.pojo.User;
import com.auroral.service.UserService;
import com.auroral.utils.JwtUtil;
import com.auroral.utils.Md5Util;
import com.auroral.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        User u = userService.findByUserName(username);
        if (u == null) {
            //没有占用
            //注册
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        //根据用户名
        User loginUser = userService.findByUserName(username);
        //判断该用户名是否存在
        if (loginUser == null) {
            return Result.error("用户名错误");
        }
        //判断密码是否正确  loginUser对象中的password是加密后的密码
        if (loginUser.getPassword().equals(Md5Util.getMD5String(password))) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            //登录成功
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name="Authorization") String token*/) {
        //根据用户名查询用户
/*        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        //1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }
        //原密码是否正确
        //调用userService根据用户名拿到原密码，再和old_pwd对比
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User longinUser = userService.findByUserName(username);
        if (!longinUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码填写不正确");
        }
        //newpwd密码和re_pwd是否一致
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次填写的新密码不一致");
        }
        //2.调用Service完成密码更新
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
