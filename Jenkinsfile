pipeline {
    agent any

    tools {
        maven 'maven3.9.11'
        jdk 'jdk-17'
    }

    environment {
        DOCKER_USERNAME = 'mmnassri'
        DOCKER_IMAGE = "${DOCKER_USERNAME}/madrasati-backend"
        CONTAINER_NAME = 'e-learningBackCont'
        COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {

        stage('Checkout') {
            steps {
                git url: 'https://github.com/MohamedAmineM/e-learningProject.git', branch: 'main'
            }
        }

        stage('Build Docker Image and Push to DockerHub') {
            steps {
                dir('demo') {
                    script {
                        echo "======= Building and pushing Backend Docker image ======="

                    
                            // Build Backend image (SpringBoot)
                            bat "docker build -t %DOCKER_IMAGE% ."


                        
                    }
                }
            }

            post {
                always {
                    bat 'docker logout'
                }
                success {
                    echo "✅ Backend image push SUCCESS"
                }
                failure {
                    echo "❌ Backend image push FAILED"
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                dir('demo') {
                    script {
                        echo "======= Deploying with Docker Compose ======="

                        // Safely check and create Docker network using PowerShell
                        powershell '''
                        $networkExists = docker network ls | Select-String "my-network"
                        if (-not $networkExists) {
                            docker network create my-network
                        }
                        '''

                        // Stop and remove old containers (if any), then start fresh
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
            echo '✅ Backend container built and running!'
        }
        failure {
            echo '❌ Something went wrong during the backend pipeline.'
        }
    }
}
