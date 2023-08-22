pipeline {
    agent any
    stages {
         stage("Start Grid"){
         	steps{
         		sh "docker-compose up -d selenoid"
         	}
         }
         stage("Run Test"){
         	steps{
         		sh "docker-compose up my_project"
         	}
         }
 		stage("Stop Grid"){
         	steps{
         		sh "docker-compose down"
         	}
         }
    }
}