import com.naijab.pipeline.LineNotify

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
          @LineNotify.send(params.lineToken, "succeed")
      }
      failure{
          @LineNotify.send(params.lineToken, "failed")
      }
    }
  }
}

