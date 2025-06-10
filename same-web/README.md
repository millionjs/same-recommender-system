项目启动：
1：项目编译：「jdk8」，「mvn3.8.6」
mvn clean package -U -DskipTests -Dos.arch=x86_64

2：Spring服务，本地启动：
入口类：EnginePlatformServiceApplication

3：本地接口入口。 
http://localhost:18081/api/same/engine/platform/common/ping

4：本地模块调用测试
http://localhost:18081/api/same/engine/platform/common/test

5：本地api地址信息查看：
http://localhost:18081/api/swagger-ui/index.html#/
