#!groovy

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
            git branch: params.branch, credentialsId: params.gitCredentialsId, url: params.gitUrl
        }
      }

      stage('Build Docker Image') {
        steps {
          script {
            dockerImage = docker.build params.dockerRegistry + ":$BUILD_NUMBER"
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
          sh "docker rmi $params.dockerRegistry:$BUILD_NUMBER"
        }
      }
    }

    post{
      success{
          notifyLINE(params.lineToken, "succeed")
      }
      failure{
          notifyLINE(params.lineToken, "failed")
      }
    }
  }
}

def notifyLINE(token, status) {
    def jobName = env.JOB_NAME +' '+env.BRANCH_NAME
    def buildNo = env.BUILD_NUMBER
      
    def url = 'https://notify-api.line.me/api/notify'
    def message = "${jobName} Build #${buildNo} ${status} \r\n"
    sh "curl ${url} -H 'Authorization: Bearer ${token}' -F 'message=${message}'"
}