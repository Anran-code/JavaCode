# JavaCode · 安染的 Java 学习记录

这是我自学 Java 的代码与笔记仓库。我有 C++ 学习基础，希望通过逐章练习和小项目，把 Java 的语法、面向对象、集合、IO、多线程与网络通信串起来。

仓库按学习阶段划分为多个 IntelliJ IDEA 模块，既保留课堂示例和个人练习，也记录学习过程中遇到的问题与总结。部分 GUI 界面、代码审查和笔记整理由 AI 辅助完成。

## 配套 Web 知识库

**[安染的java私人图书馆 · JavaKnowledgeBase](https://github.com/Anran-code/JavaKnowledgeBase)**

这是与本仓库配套的 Web 阅读应用：将知识点讲解、学习笔记和真实 Java 源码放到一起，支持按章节阅读、关键词搜索、源码切换与复制。

两个仓库各有分工：

| 仓库 | 内容 |
| --- | --- |
| **JavaCode（本仓库）** | 完整 Java 练习、实战项目源码、原始学习笔记 |
| **[JavaKnowledgeBase](https://github.com/Anran-code/JavaKnowledgeBase)** | 知识库网站、整理后的章节、全书目录与阅读组件 |

知识库当前出版版本为 **[v0.1](https://github.com/Anran-code/JavaKnowledgeBase/tree/v0.1)**：已整理 Java 基础的 10 个小节，并建立 8 个部分、24 个章节与专题的目录。其余章节已关联现有资料，正文会逐步整理。

**目前 Web 应用需在本机启动，尚未部署公共在线阅读地址。** 上面的链接是应用源码仓库；启动方法见下文，也可查看其 [README](https://github.com/Anran-code/JavaKnowledgeBase#readme) 和 [出版更新记录](https://github.com/Anran-code/JavaKnowledgeBase/blob/main/CHANGELOG.md)。

## 学习路线与模块

模块编号按学习顺序排列。笔记按知识点归类，因此笔记编号与模块编号不一定一一对应。

| 模块目录 | 主要内容 |
| --- | --- |
| [01-HelloWorld](01-HelloWorld/) | 第一个 Java 程序与程序入口 |
| [02-basic](02-basic/) | 基本类型、类型转换、方法、重载、输入与基础练习 |
| [03-flow-control](03-flow-control/) | 分支、循环、计算器与验证码等练习 |
| [04-array](04-array/) | 数组、二维数组、成绩统计与洗牌等练习 |
| [05-oop](05-oop/) | 类与对象、构造方法、封装、静态成员 |
| [06-advanced-oop](06-advanced-oop/) / [07-more-advanced-oop](07-more-advanced-oop/) | 继承、重写、多态、抽象类、枚举等 |
| [08-interface](08-interface/) | 接口及多种实现、面向对象与接口综合实践 |
| [09-more-advanced-oop2](09-more-advanced-oop2/) | 成员内部类、静态内部类、匿名内部类与排序 |
| [10-lambda](10-lambda/) | Lambda、函数式接口与方法引用 |
| [11-string-and-arraylist](11-string-and-arraylist/) | String、ArrayList 与常用 API |
| [12-GUI](12-GUI/) | Swing 组件、布局、事件监听与键盘焦点 |
| [13-collection](13-collection/) | 异常、泛型、包装类、Collection 与集合练习 |
| [14-map-stream](14-map-stream/) | Map、Stream、比较器与扑克牌练习 |
| [15-file-io](15-file-io/) | 文件、字节流、字符流、文件复制及其他 IO 流 |
| [16-thread](16-thread/) | 线程、同步、线程池与综合练习 |
| [17-net](17-net/) | UDP、TCP、多客户端通信与简单 B/S 示例 |
| [18-junit-reflect-annotation-proxy](18-junit-reflect-annotation-proxy/) | 当前继续学习的单元测试、反射与注解；具体进度以源码为准 |

## 项目实战

### 人事管理系统

[源码](anran-employee-sys/) · [设计文档](anran-employee-sys/design.txt) · [项目复盘](notes/实战01-人事系统.md)

使用 Swing 制作登录、注册与员工信息管理界面，练习集合保存数据、员工搜索、添加、编辑、删除，以及表格内容刷新。

### 图书管理系统

[源码](anran-library-sys/) · [设计文档](anran-library-sys/src/design.txt)

围绕管理员与普通用户设计不同界面，练习图书、会员与借阅信息管理，以及借还书、充值、办卡和会员升级。借书卡状态由手动维护，不自动计算到期日或月费。

### 网络聊天室

[客户端](anran-network-chat-sys/) · [服务端](anran-network-server-sys/) · [设计文档](anran-network-chat-sys/src/design.txt)

结合 Swing、Socket 与多线程，实现昵称登录、在线用户列表、群聊、私聊和未读消息提示，并练习私聊历史记录的管理。

## 学习笔记

原始 Markdown 笔记保存在 **[notes](notes/)**，可直接在 GitHub 中阅读：

- 基础：[Java 基础](notes/01基础.md)、[程序流程控制](notes/02程序流程控制.md)。
- 面向对象：[类与对象](notes/03面向对象.md)、[高级面向对象](notes/04高级面向对象.md)、[接口](notes/05接口.md)、[综合实践](notes/06面向对象与接口实践.md)、[内部类](notes/07内部类.md)、[函数式编程](notes/08函数式编程.md)。
- 常用类与界面：[String 和 ArrayList](notes/09String和ArrayList.md)、[GUI 编程](notes/10GUI编程.md)。
- 数据处理：[异常](notes/11异常.md)、[泛型](notes/12泛型.md)、[集合](notes/13集合.md)、[Stream 流](notes/14Stream流.md)。
- IO 与并发网络：[文件与 IO 流](notes/15文件与IO流.md)、[线程](notes/16线程.md)、[网络通信](notes/17网络通信.md)。
- 专题速查：[多态](notes/多态.md)、[类与模块导入](notes/导入.md)、[语法](notes/语法.md)。

笔记会随学习持续补充，早期示例与后续总结可能存在差异，可结合对应源码查看。

## 运行 Java 示例

1. 克隆本仓库，并使用 IntelliJ IDEA 打开项目：

   ```bash
   git clone https://github.com/Anran-code/JavaCode.git
   ```

2. 配置 Project SDK 和模块 SDK。当前提交的 IDEA 项目配置使用 **JDK 25**，可先使用同版本运行。
3. 选择要学习的模块，在其 `src` 中找到带有 `main` 方法的类，单独运行。
4. 若示例使用 Lombok、JUnit 等依赖，检查模块依赖和注解处理配置。相关 JAR 位于 [lib](lib/)；JUnit 测试类通过 IDE 的测试入口运行。

这是由多个独立练习组成的 IDEA 项目，没有统一的全仓库启动入口。不同模块中可能存在同名类，请在对应模块中运行。

| 实战项目 | 启动入口 |
| --- | --- |
| 人事管理系统 | [anran-employee-sys/src/App.java](anran-employee-sys/src/App.java) |
| 图书管理系统 | [anran-library-sys/src/App.java](anran-library-sys/src/App.java) |
| 聊天室服务端 | [anran-network-server-sys/src/com/ranan/Server.java](anran-network-server-sys/src/com/ranan/Server.java) |
| 聊天室客户端 | [anran-network-chat-sys/src/App.java](anran-network-chat-sys/src/App.java) |

聊天室先启动服务端，再启动一个或多个客户端。当前客户端默认连接 `127.0.0.1:6666`；跨电脑使用时，将客户端 `Constant.SERVER_IP` 改为服务端所在电脑的局域网地址，并保持端口一致、网络可达。

部分文件与 IO 示例使用本地文件路径，运行前请根据自己的目录调整。

## 在浏览器中阅读知识库

在同一父目录下准备两个仓库：

```bash
git clone https://github.com/Anran-code/JavaCode.git
git clone https://github.com/Anran-code/JavaKnowledgeBase.git
cd JavaKnowledgeBase
npm ci
npm run dev
```

如果已经下载了 JavaCode，无需重复克隆。知识库需要 Node.js 与 npm；其 v0.1 已在 Node.js 24 环境验证。

目录关系应为：

```text
工作目录/
├── JavaCode/
└── JavaKnowledgeBase/
```

启动后访问 **<http://127.0.0.1:5178/>**。这个地址指向你自己电脑上的服务，需要先运行上述启动命令。

知识库默认读取同级 `JavaCode` 的指定笔记和源码，生成只读展示快照。若两个仓库不在同一级目录，请修改知识库的 `knowledge.config.json`。知识库的启动、构建和页面整理均在独立仓库中进行。

### 小点子：把知识库放到桌面

Windows 用户可以把这个本地 Web 应用做成一个桌面入口：一本写着 **JAVA** 的绿色书本，名字叫 **安染的java私人图书馆**。

1. 在 `JavaKnowledgeBase` 目录完成 `npm ci` 和 `npm run build`。
2. 双击知识库根目录的 **创建桌面快捷方式.cmd**。
3. 以后双击桌面图标，就会自动在后台启动本地服务，并用默认浏览器打开总目录；服务已经运行时直接复用。

图标和自动创建脚本都放在 [JavaKnowledgeBase 仓库](https://github.com/Anran-code/JavaKnowledgeBase)，会根据每个人的仓库位置生成快捷方式。这里仍然是浏览器中的 Web 应用，不需要另外安装桌面客户端。关闭浏览器后服务会留在后台，不会设置开机自启。

## 关于这些练习

这里记录的是持续学习的过程：部分代码采用简化的数据存储和业务规则，部分模块还在完善。欢迎通过 Issue 交流学习思路、指出笔记或代码中的问题。
