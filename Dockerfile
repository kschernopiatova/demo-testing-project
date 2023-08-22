FROM maven:3.6.3-jdk-11

ADD src src
ADD pom.xml .

RUN mvn clean package -DskipTests

#ADD target/amazon-project.jar amazon-project.jar
#ADD target/amazon-project-tests.jar amazon-project-tests.jar
#ADD target/libs libs
#ADD target/classes /classes

ADD /src/test/resources/testng_suites/web.xml web.xml

ENTRYPOINT java -cp "target/amazon-project.jar:target/amazon-project-tests.jar:target/libs/*:target/classes/com/solvd/demo/project/web/pages/desktop" org.testng.TestNG web.xml