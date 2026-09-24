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
// follow the lecture demo for hub.docker.com deployment stages
}
}