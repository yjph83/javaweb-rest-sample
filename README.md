javaweb-rest-demo
介绍

spring boot 2.1.6版本应用开发
* JDK 1.8
* Springboot 2.1.6.RELEASE
* Mybatis SpringBoot starter 1.3.2
* alibaba druid 连接池1.1.9
* pagehelper SpringBoot starter 1.2.3
* logback日志集成，日志文件存放地址自己修改
* 集成swagger2 在线接口文档
* 集成flyway 数据库版本控制、数据迁移插件
* 集成pmd及阿里巴巴代码规范检测插件；
* 集成asciidoctor 离线文档插件；
* mybatis generator 自动生成代码插件 1.3.2
* 集成feign 功能
* 集成redis，mybatis缓存功能
* 更新了springdocs 2.0.3，h2 1.4.99，flayway 5.2.4,mybatis由等
* 添加了mapstruc和lombok依赖包

软件架构
软件架构说明

安装教程
1、java的maven项目clone下来后编译好即可
使用说明
1、用dev环境启动时，需要配置jvm参数：-spring.profiles.active=dev， 
   或者在idea中springboot下的 Active.files 中填写上dev即可
2、mvn clean test 可以直接进行单元测试

参与贡献
1、Fork 本仓库
2、新建 Feat_xxx 分支
3、提交代码
4、新建 Pull Request
5、码云特技
6、使用 Readme_XXX.md 来支持不同的语言，例如 Readme_en.md, Readme_zh.md

码云官方博客 blog.gitee.com
1、你可以 https://gitee.com/explore 这个地址来了解码云上的优秀开源项目
2、GVP 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
3、码云官方提供的使用手册 https://gitee.com/help
4、码云封面人物是一档用来展示码云会员风采的栏目 https://gitee.com/gitee-stars/