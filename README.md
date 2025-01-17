# Big-Event 项目

**Big-Event** 是一个基于 **SpringBoot 3** 和 **Vue 3** 实现的前后端分离项目，涵盖了用户管理、文章管理、文件上传等核心功能，集成了多种现代技术栈，适用于学习与实战开发。

---

## **项目功能**
1. **用户管理**：
   - 用户注册与登录，使用 `Validated` 进行参数校验。
   - 登录优化：利用 Redis 防止已修改密码的用户通过旧 Token 获取信息，实现 Token 主动失效机制。

2. **文章管理**：
   - 实现文章的添加、修改、删除（CRUD）。
   - 支持分页查询和文章分类管理。

3. **文件上传**：
   - 接入阿里云 OSS，实现图片等文件的云端存储。

4. **前端开发**：
   - 使用 Vue 3 和 Element Plus 构建登录注册页面。
   - 顶部导航栏信息显示、文章管理界面等交互设计。
   - 集成 Pinia 状态管理库，支持持久化状态管理。

5. **后端开发**：
   - 基于 SpringBoot 的 Controller-Service-Mapper 分层架构。
   - 使用 MyBatis 操作数据库，支持复杂业务逻辑处理。
   - 使用 `ThreadLocal` 实现资源管理，采用 MD5 加密用户密码。

6. **其他功能**：
   - 使用 Git 和 GitHub 进行版本管理。
   - 打包部署：通过 Maven 打包项目（生成 `.jar` 文件），部署至服务器（需安装 JRE 环境）。

---

## **技术栈**
### **后端技术**
- SpringBoot 3
- MyBatis
- MySQL
- Redis
- 阿里云 OSS
- Maven

### **前端技术**
- Vue 3
- Vue Router
- Pinia（持久化插件）
- Element Plus

### **工具与环境**
- Postman：接口测试
- Git & GitHub：版本管理
- JDK 11
- Node.js 16+

---

## **项目亮点**
- **性能优化**：通过 Redis 实现登录 Token 主动失效机制，提升系统安全性。
- **前后端分离**：使用 Vue 3 和 SpringBoot 完成高效的分离式开发。
- **云存储集成**：文件上传支持阿里云 OSS，实现高效可靠的文件存储。
- **状态管理**：Pinia 持久化处理，提升前端数据管理体验。
- **多功能支持**：支持文章 CRUD、分类管理、用户管理、文件上传等核心功能。

---

## **部署指南**
### **后端部署**
1. 克隆代码：
   ```bash
   git clone https://github.com/<YourGitHubUsername>/big-event.git
   cd big-event
