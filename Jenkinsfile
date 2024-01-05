pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'java8'
        git 'git'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main' , url: 'https://github.com/Mouslih0/JUnit-Jenkis-OrderCraft/'
            }
        }

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean install'
                    } else {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn test'
                    } else {
                        bat 'mvn test'
                    }
                }
            }
        }
    }


    stage ('report') {
        steps {
           script {
               sh 'mvn clean site'
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