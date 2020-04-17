def call(Map params) {
  pipeline {
    agent any

    environment {
      registryCredential = 'dockerhub'
      dockerImage = ''
    } 

    stages {
      stage('Checkout Git') {
        steps {
            git branch: param.branch, credentialsId: 'GitCredentials', url: param.gitUrl
        }
      }

      stage('Build Docker Image') {
        steps {
          script {
            dockerImage = docker.build param.dockerRegistry + ":$BUILD_NUMBER")
          }
        }
      }

      stage('Upload Docker Image') {
        steps{
          script {
            docker.withRegistry('', registryCredential ) {
              dockerImage.push()
            }
          }
        }
      }

      stage('Remove Unused docker image') {
        steps{
          sh "docker rmi $param.dockerRegistry:$BUILD_NUMBER"
        }
      }
    }
  }
}