pipeline {
    agent any

    stages {

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t fullstack-backend .'
            }
        }

        stage('Stop Old Container') {
            steps {
                sh '''
                docker stop fullstack-container || true
                docker rm fullstack-container || true
                '''
            }
        }

        stage('Create Env File') {
            steps {
                withCredentials([
                    string(credentialsId: 'DB_URL', variable: 'DB_URL'),
                    string(credentialsId: 'DB_USER', variable: 'DB_USER'),
                    string(credentialsId: 'DB_PASS', variable: 'DB_PASS'),
                    string(credentialsId: 'JWT_SECRET', variable: 'JWT_SECRET'),
                    string(credentialsId: 'MAIL_USER', variable: 'MAIL_USER'),
                    string(credentialsId: 'MAIL_PASS', variable: 'MAIL_PASS'),
                    string(credentialsId: 'AWS_ACCESS', variable: 'AWS_ACCESS'),
                    string(credentialsId: 'AWS_SECRET', variable: 'AWS_SECRET')
                ]) {
                    sh '''
                    cat <<EOF > app.env
DB_URL=$DB_URL
DB_USER=$DB_USER
DB_PASS=$DB_PASS
JWT_SECRET=$JWT_SECRET
MAIL_USER=$MAIL_USER
MAIL_PASS=$MAIL_PASS
AWS_ACCESS=$AWS_ACCESS
AWS_SECRET=$AWS_SECRET
EOF
                    '''
                }
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker run -d \
                --name fullstack-container \
                -p 9090:9090 \
                --env-file app.env \
                fullstack-backend
                '''
            }
        }
    }
}