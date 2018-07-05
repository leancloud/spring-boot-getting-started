# Java Getting started

一个简单的使用 Spring Boot 的 Java 服务端应用，打成 jar 包运行。
可以运行在 LeanEngine Java 运行时环境。

## 一键部署
[![Deploy to LeanEngine](http://ac-32vx10b9.clouddn.com/109bd02ee9f5875a.png)](https://leancloud.cn/1.1/functions/_ops/deploy-button)

## 本地运行

首先确认本机已经安装 [LeanCloud 命令行工具](https://www.leancloud.cn/docs/leanengine_cli.html)，然后执行下列指令：

```
$ git clone https://github.com/leancloud/spring-boot-getting-started.git
$ cd spring-boot-getting-started
```

1. 安装依赖：

```
mvn package
```

2. 关联应用：
将目标应用的 appId/appKey/masterKey，填入 local.sh 脚本
```
export LC_APP_ID=
export LC_APP_KEY=
export LEANCLOUD_APP_ID=
export LEANCLOUD_APP_KEY=
export LEANCLOUD_APP_MASTER_KEY=
```

3. 启动项目：
在项目根目录下运行如下脚本：
```
./local.sh
```

应用即可启动运行：[localhost:3000](http://localhost:3000)

## 部署到 LeanEngine

部署到预备环境（若无预备环境则直接部署到生产环境）：
```
lean deploy
```

将预备环境的代码发布到生产环境：
```
lean publish
```

## 相关文档

* [云引擎服务总览](https://leancloud.cn/docs/leanengine_overview.html)
* [网站托管开发指南](https://leancloud.cn/docs/leanengine_webhosting_guide-java.html)
* [云函数开发指南](https://leancloud.cn/docs/leanengine_cloudfunction_guide-java.html)
* [数据存储开发指南](https://leancloud.cn/docs/leanstorage_guide-java.html)
* [命令行工具使用详解](https://leancloud.cn/docs/leanengine_cli.html)
