pipeline {
    agent any

    stages {
        stage('Build') {
            dir('eCommerceApp/App')  {
                checkout([$class: 'GitSCM', branches: [[name: '*/feature']], userRemoteConfigs: [[url: 'https://github.com/mhayo/eCommerceApp.git']]])
              //  sh './mvnw clean package'
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            dir('eCommerceApp/App')  {
             //   sh './mvnw test'
                 sh 'mvn test'
            }
        }

        stage('Deploy') {
            dir('eCommerceApp/App')  {
                sh 'cp target/eCommerceApp.jar /path/to/deployment/location'
            }
        }
    }

    post {
        success {
            echo 'Process successfully!!'
            // Hier könntest du weitere Benachrichtigungen oder Integrationen hinzufügen
        }

        failure {
            echo 'An error is occured'
        }
    }
}
