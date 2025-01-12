package com.auroral.pojo;



import lombok.Data;

import java.time.LocalDateTime;
//lombok 在编译阶段，为实体类自动生成setter、getter、toString、equals、hashCode方法
//pom文件中引入依赖，在实体类中引入注解
@Data
public class User {
    private Integer id;//主键ID
    private String username;//用户名
    private String password;//密码
    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
