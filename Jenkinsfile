pipeline {
    agent any

    stages {
        stage('Build & Test') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/feature']], userRemoteConfigs: [[url: 'https://github.com/mhayo/eCommerceApp.git']]])
              //  sh './mvnw clean package'
              //  sh 'cd App && mvn clean package'
                  sh 'cd App && mvn compile package'
            }
        }
/*
        stage('Test') {
           steps {
             //   sh './mvnw test'
                 sh 'cd App && ./mvnw test'
            }
        }

        stage('Deploy') {
            steps  {
                    sh 'cd App && cp target/eCommerceApp.war /usr/local/tomcat/webapps'            }
        }*/
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
