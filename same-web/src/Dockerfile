FROM docker-reg.devops.xiaohongshu.com/library/jnlp-slave-general:4.0-xhs
WORKDIR /java
ADD . /java
RUN cd offline-engine-web && mvn -Dmaven.test.skip=true clean package

FROM docker-reg.devops.xiaohongshu.com/library/java:jdk1.8.0_201-s3cmd
ENV TZ=Asia/Shanghai
WORKDIR /apps
COPY --from=0 /java/offline-engine-web/target/offline-engine-web*.jar /apps
RUN mv /apps/offline-engine-web*.jar /apps/offline-engine-web.jar