pipeline {
    agent any
    tools {
        maven 'Maven 3.8.6'
    }
    stages {
        stage('Build Maven') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: '{github-project}']]])
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                script{
                    sh '''
                    cd $WORKSPACE/spring-soap-webservice
                    docker build -t {username}/spring-soap-webservice .
                    '''
                    sh '''
                    cd $WORKSPACE/spring-soap-webservice-client
                    docker build -t {username}/spring-soap-webservice-client  .
                    '''
                }
            }
        }
        stage('Push image to Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub_pwd', variable: 'dockerhub_pwd')]) {
                        sh 'docker login -u {username} -p ${dockerhub_pwd}'
                    }
                    sh 'docker push {username}/spring-soap-webservice-client'
                    sh 'docker push {username}/spring-soap-webservice'
                }
            }
        }
    }
}