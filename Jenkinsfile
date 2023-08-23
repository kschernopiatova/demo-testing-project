pipeline {
    agent any
    stages {
         stage("Start Grid"){
         	steps{
         		sh "docker-compose up -d selenoid"
         	}
         }
         stage("Pull images") {
            steps{
                sh "docker pull selenoid/video-recorder"
                sh "docker pull dumbdumbych/selenium_vnc_chrome_arm64:91.0.b"
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