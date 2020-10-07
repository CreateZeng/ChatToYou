FROM java:8
#VOLUME 指定了临时文件目录为/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为app.jar
ADD *.jar app.jar
# 运行jar包
RUN bash -c 'touch /app.jar'
#缩短 Tomcat 启动时间
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]