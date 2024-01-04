pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Mouslih0/JUnit-Jenkis-OrderCraft'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }

        failure {
             echo 'Build failed!'
        }
    }
}