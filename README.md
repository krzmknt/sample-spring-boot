# Small Rest Application for AWS CI/CD

mvn spring-boot:run でサーバーを起動すると、http://localhost:8080 で起動します。

`curl http://localhost:8080` などにより `{"version": 1, "message": "Hello, Sample Spring Boot CI/CD!"}`
など結果がレスポンスが得られます。

src/main/java/com/sample/sampleSpringBootCicd/Application.java の new Config(xxx, yyy) の xxx, yyy 部分を変更して、アプリ修正し、デプロイのテストに利用します。
