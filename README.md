# cloud-backend

云笔记后端 分布式版  

|    模块名    |          简介          | 端口 |
| :----------: | :--------------------: | :--: |
| CommonModule |     通用基础支持包     |  无  |
| ConfigServer |    统一配置中心服务    | 1201 |
|  EditServer  |        笔记服务        | 3054 |
| EurekaServer |      服务注册中心      | 8761 |
|  FileServer  | 文件存储服务，暂未完成 |  无  |
|  UserServer  |        用户服务        | 3055 |
|  ZuulServer  |          网关          | 1000 |

# 编译

编译依赖：jdk8，maven3.3+

**使用maven编译打包**

```
git clone  https://github.com/liangliangle/cloud-backend.git
cd cloud-backend
mvn clean pakcage -DskipTests
```

**打成Docker镜像**

请确保安装了docker，并且按照
http://blog.lianglianglee.com/article/docker-openjre
打入open-jre-8的镜像

```
build-docker.sh
```

# 环境配置

mysql5.7

由于配置中心读取DB数据，请注意配置初始化。

您可更改Config配置完成DB读取地址

模版配置脚本：config_properties.sql

# 运行

**jar运行**

在jar目录运行

```
java -jar **.jar
```

docker运行

以ConfigServer为例

```
docker run -d -p 1201:1201 --name config-server --net host ImgId
```



