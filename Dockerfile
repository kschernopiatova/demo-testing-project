FROM maven:3.6.3-jdk-11

ADD src src
ADD pom.xml .

RUN mvn clean package -DskipTests

ADD /src/test/resources/testng_suites/web.xml web.xml

ENTRYPOINT java -cp "target/amazon-project.jar:target/amazon-project-tests.jar:target/libs/*:target/classes/com/solvd/demo/project/web/pages/desktop" org.testng.TestNG $MODULE -Dselenium_url=$URL