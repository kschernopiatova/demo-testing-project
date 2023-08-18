pipeline {
    agent any
    stages {
        stage('Build Jar') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                sh "docker build -t='kchernopiatova/amazon-project' ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
			        sh "docker login --username=${user} --password=${pass}"
			        sh "docker push kchernopiatova/amazon-project:latest"
			    }
            }
        }
        stage("Pull Latest Image"){
         	steps{
         		sh "docker pull kchernopiatova/amazon-project"
         	}
         }
         stage("Start Grid"){
         	steps{
         		sh "docker-compose up -d hub chrome firefox chrome-recording"
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