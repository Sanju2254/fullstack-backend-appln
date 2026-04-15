pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Sanju2254/fullstack-backend-appln.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t fullstack-backend .'
            }
        }

        stage('Run') {
            steps {
                sh 'docker run -d -p 9090:9090 fullstack-backend'
            }
        }
    }
}