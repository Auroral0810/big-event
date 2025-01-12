package com.auroral.controller;


import com.auroral.pojo.Result;
import com.auroral.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list() {
//        //验证token
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("所有的文章数据.....");
//        } catch (Exception e) {
//            //http响应状态吗为401，表示token验证失败
//            response.setStatus(401);
//            return Result.error("未登录");
//        }
        return Result.success("所有的文章数据.....");
    }
}
