# 빌드 스테이지
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app

# pom.xml 복사 후 의존성 다운로드 (캐싱 활용)
COPY pom.xml .
RUN mvn dependency:go-offline

# 소스 코드 복사 및 빌드
COPY src ./src
RUN mvn package -DskipTests

# 런타임 스테이지
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 타임존 설정
RUN apk add --no-cache tzdata
ENV TZ=Asia/Seoul

# 빌드 스테이지에서 생성된 JAR 파일 복사
COPY --from=build /app/target/*.jar app.jar

# 포트는 Render에서 자동으로 $PORT 환경변수에 할당
ENV PORT=9002
EXPOSE $PORT

# Render에서 제공하는 PORT 환경변수 사용
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]