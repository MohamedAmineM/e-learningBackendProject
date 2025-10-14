pipeline {
    agent any

    tools {
        maven 'maven3.9.11'
        jdk 'jdk-17'
    }

    environment {
        DOCKER_IMAGE = 'mmnassri/madrasati-backend'
        COMPOSE_FILE = 'demo/docker-compose.yml'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/MohamedAmineM/e-learningBackendProject.git', branch: 'main'
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('demo') {
                    script {
                        sh "docker build -t ${DOCKER_IMAGE}:latest ."
                    }
                }
            }
        }

        

        stage('Deploy with Docker Compose') {
            steps {
                dir('demo') {
                    script {
                        // Make sure external network exists
                        sh 'docker network ls | grep my-network || docker network create my-network'

                        sh 'docker-compose down || true'
                        sh 'docker-compose up -d'
                    }
                }
            }
        }

        stage('Check Running Containers') {
            steps {
                sh 'docker ps'
            }
        }
    }

    post {
        success {
            echo '✅ Image pushed and services deployed successfully.'
        }
        failure {
            echo '❌ Something went wrong during the pipeline.'
        }
    }
}
