pipeline {
agent any
tools {
maven 'Maven_config'
}
stages {
stage('Checkout') {
steps {
git 'https://github.com/petrova-olena/OTP1.git'
}
}
stage('Build') {
steps {
sh 'mvn clean install'
}
}
stage('Test') {
steps {
sh 'mvn test'
}
}
stage('Code Coverage') {
steps {
sh 'mvn jacoco:report'
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

stage('Login to Docker Hub') {
    steps {
        sh 'echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin'
    }
}

stage('Push Docker Image to Docker Hub') {
    steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-username_password', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
            sh 'echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin'
            sh 'docker push olenape/otp1:latest'
        }
    }
}


}
}