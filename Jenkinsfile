pipeline {
    agent any

    tools {
        maven 'maven3.9.11'
        jdk 'jdk-17'
    }

    environment {
        DOCKER_IMAGE = 'mmnassri/madrasati-backend'
        COMPOSE_FILE = 'docker-compose.yml'
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
                        bat "docker build -t ${DOCKER_IMAGE}:latest ."
                    }
                }
            }
        }

        

        stage('Deploy with Docker Compose') {
            steps {
                dir('demo') {
                    script {
                        // Remplace grep par PowerShell pour vérifier si le réseau existe
                        powershell '''
                        if (-not (docker network ls | Select-String "my-network")) {
                            docker network create my-network
                        }
                        '''

                        bat 'docker-compose down || exit 0'
                        bat 'docker-compose up -d'
                    }
                }
            }
        }


        stage('Check Running Containers') {
            steps {
                bat 'docker ps'
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
