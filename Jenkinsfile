pipeline {
    agent any

    environment {
        DB_USERNAME = credentials('DB_USERNAME')
        DB_PASSWORD = credentials('DB_PASSWORD')
        JWT_SECRET = credentials('JWT_SECRET')
        MAIL_USERNAME = credentials('MAIL_USERNAME')
        MAIL_PASSWORD = credentials('MAIL_PASSWORD')
        AWS_ACCESS_KEY = credentials('AWS_ACCESS_KEY')
        AWS_SECRET_KEY = credentials('AWS_SECRET_KEY')

        KAFKA_BOOTSTRAP_SERVERS = "34.237.111.250:9092"

        IMAGE_NAME = "fullstack-backend"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'YOUR_GIT_REPO'
            }
        }

        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh """
                docker build -t $IMAGE_NAME .
                """
            }
        }

        stage('Stop Old Container') {
            steps {
                sh """
                docker rm -f fullstack-app || true
                """
            }
        }

        stage('Run Container') {
            steps {
                sh """
                docker run -d \
                --name fullstack-app \
                -p 9090:9090 \
                -e DB_USERNAME=$DB_USERNAME \
                -e DB_PASSWORD=$DB_PASSWORD \
                -e JWT_SECRET=$JWT_SECRET \
                -e MAIL_USERNAME=$MAIL_USERNAME \
                -e MAIL_PASSWORD=$MAIL_PASSWORD \
                -e AWS_ACCESS_KEY=$AWS_ACCESS_KEY \
                -e AWS_SECRET_KEY=$AWS_SECRET_KEY \
                -e KAFKA_BOOTSTRAP_SERVERS=$KAFKA_BOOTSTRAP_SERVERS \
                $IMAGE_NAME
                """
            }
        }
    }
}