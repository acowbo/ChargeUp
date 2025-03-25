# 简单记账 - 开源记账软件

## 项目简介

**简单记账** 是一个基于 Spring Boot 和 Thymeleaf 开发的开源、免费的记账软件。它的目标是提供一个简单、易用的记账工具，专注于核心的记账功能，没有复杂的附加功能。你可以轻松地记录每一笔收支，并通过分类来查看和管理你的财务状况。

本项目完全开源，代码简洁易懂，适合学习和二次开发。如果你正在寻找一个轻量级的记账工具，或者想要了解如何使用 Spring Boot 和 Thymeleaf 开发一个简单的 Web 应用，那么这个项目将是一个很好的起点。

## 功能特性

- **分类记账**：支持按类别记录收入与支出，方便用户快速分类管理财务。
- **分类展示**：按类别展示收支情况，帮助用户清晰了解每一类别的花费情况。
- **账单展示**：展示所有账单记录，支持按时间排序，方便用户查看历史记录。
- **简洁易用**：界面简洁，操作简单，专注于核心的记账功能，没有多余的花哨功能。

## 技术栈

- **后端框架**：Spring Boot
- **前端模板引擎**：Thymeleaf
- **数据库**：MySQL 
- **构建工具**：Maven

## 快速开始

### 环境要求

- JDK 11 或更高版本
- Maven 3.x

### 运行步骤

1. **克隆项目**

   ```bash
   git clone https://github.com/yourusername/simple-accounting.git
   cd simple-accounting
   ```

2. **编译项目**

   ```bash
   mvn clean install
   ```

3. **运行项目**

   ```bash
   mvn spring-boot:run
   ```

4. **访问应用**

   打开浏览器，访问 `http://localhost:8080`，即可开始使用。

## 项目结构

```
simple-accounting/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/simpleaccounting/
│   │   │       ├── controller/         # 控制器层
│   │   │       ├── entity/             # 实体类
│   │   │       ├── repository/         # 数据访问层
│   │   │       ├── service/            # 服务层
│   │   │       └── SimpleAccountingApplication.java # 启动类
│   │   ├── resources/
│   │   │   ├── static/                 # 静态资源（CSS, JS等）
│   │   │   ├── templates/              # Thymeleaf 模板文件
│   │   │   └── application.properties  # 配置文件
│   └── test/                           # 测试代码
└── pom.xml                             # Maven 配置文件
```

## 贡献指南

我们欢迎任何形式的贡献！如果你有任何建议或想法，欢迎提交 Issue 或 Pull Request。

1. **Fork 项目**
2. **创建新分支** (`git checkout -b feature/your-feature`)
3. **提交更改** (`git commit -m 'Add some feature'`)
4. **推送分支** (`git push origin feature/your-feature`)
5. **提交 Pull Request**

## 许可证

本项目采用 [MIT 许可证](LICENSE)，你可以自由地使用、修改和分发代码。

## 联系作者

如果你有任何问题或建议，欢迎通过以下方式联系我：

- 微信：acowbo
- 邮箱：todoitbo@gmail.com
- GitHub: [acowbo](https://github.com/acowbo)

---

**简单记账** - 让记账变得更简单！