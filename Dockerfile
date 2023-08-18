FROM openjdk:11.0.14-jre-slim-buster

ADD target/amazon-project.jar amazon-project.jar
ADD target/amazon-project-tests.jar amazon-project-tests.jar
ADD target/libs libs
ADD target/classes /classes

ADD /src/test/resources/testng_suites/web.xml web.xml

ENTRYPOINT java -cp "amazon-project.jar:amazon-project-tests.jar:libs/*:/classes/com/solvd/demo/project/web/pages/desktop" org.testng.TestNG $MODULE