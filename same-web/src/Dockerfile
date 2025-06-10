FROM /library/jnlp-slave-general:4.0-xhs
WORKDIR /java
ADD . /java
RUN cd same-web && mvn -Dmaven.test.skip=true clean package

FROM /library/java:jdk1.8.0_201-s3cmd
ENV TZ=Asia/Shanghai
WORKDIR /apps
COPY --from=0 /java/same-web/target/same-web*.jar /apps
RUN mv /apps/same-web*.jar /apps/same-web.jar