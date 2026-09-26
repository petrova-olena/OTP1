pipeline {
agent any
stages {
stage('Checkout') {
steps {
git 'https://github.com/petrova-olena/OTP1.git'
}
}
stage('Build') {
steps {
bat 'mvn clean install'
}
}
stage('Test') {
steps {
bat 'mvn test'
}
}
stage('Code Coverage') {
steps {
bat 'mvn jacoco:report'
}
}
stage('Publish Test Results') {
steps {
junit '**/target/surefire-reports/*.xml'
}
}
stage('Publish Coverage Report') {
steps {
jacoco()
}
}
stage('Build Docker Image') {
            steps {
                sh 'docker build -t olenape/otp1:latest .'
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'Docker_username_password',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push olenape/otp1:latest
                    '''
                }
            }
        }
}
}