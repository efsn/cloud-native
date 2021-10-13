FROM $registry/common/jvm-dependencies:latest as build
WORKDIR /home
ADD . /home
RUN sh ./gradlew clean oho:bootJar

FROM $registry/common/openjdk:17-jdk-alpine
LABEL org.label-schema.docker.cmd="docker run -p 8080:8080 -d $registry/app/oho:latest"
WORKDIR /home/appuser
COPY --from=build /home/oho/build/libs/application-*.jar app.jar

RUN apk --no-cache add curl tzdata bash \
    && cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \
    && apk del tzdata \
    && addgroup -S appusers \
    && adduser -S appuser -G appusers \
    && chmod g+r,g+w,g+X -R /home/appuser/

USER appuser
EXPOSE 8080
ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-Dfile.encoding=UTF-8","-jar","/home/appuser/app.jar"]
CMD ["java","-Dorg.jooq.no-logo=true","-jar","/home/appuser/app.jar"]



