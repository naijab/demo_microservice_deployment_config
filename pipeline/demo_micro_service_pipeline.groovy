def call(Map params) {
  pipeline {
    agent any

    def app

    stages {
      stage('Checkout Git') {
        steps {
            git branch: param.branch, credentialsId: 'GitCredentials', url: param.scmUrl
        }
      }

      stage('Build Docker Image') {
        steps {
          script {
            app = docker.build param.dockerImage + ":$BUILD_NUMBER")
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
          sh "docker rmi $param.dockerImage:$BUILD_NUMBER"
        }
      }
    }
  }
}