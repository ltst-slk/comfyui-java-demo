# 基础镜像使用Java
FROM docker.1ms.run/openjdk:21-jdk-slim

# 指定维护者信息
LABEL maintainer="reboot@email.cn"

# 在镜像中创建一个目录存放我们的应用
VOLUME /tmp

# 将jar文件添加到容器中并更名为app.jar
ADD connect-server-1.0.jar app.jar

# 暴露容器内的端口给外部访问
EXPOSE 8081

# 定义环境变量
ENV JAVA_OPTS=""

# 在容器启动时运行jar包
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.avtive=dev"]

# !!启动docker 时 应该考虑 是否与 comfyUI网络共享