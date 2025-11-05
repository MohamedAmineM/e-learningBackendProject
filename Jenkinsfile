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
                git url: 'https://github.com/MohamedAmineM/e-learningBackendProject.git', branch: 'main'
            }
        }

        stage('SonarQube Analysis') {
                    
                    steps {
                            dir('demo') {
                                script {
                                    withSonarQubeEnv('mmnassriSonarQube') {
                                        withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_AUTH_TOKEN')]) {
                                            bat """
                                                mvn clean verify sonar:sonar -DskipTests ^
                                                -Dsonar.projectKey=e-learningBackend ^
                                                -Dsonar.host.url=http://localhost:9000 ^
                                                -Dsonar.login=%SONAR_AUTH_TOKEN%
                                            """
                                            }
                                        }
                                    }
                            }
                    }
            }
            
                stage('Quality Gate') {
                        steps {
                            timeout(time: 1, unit: 'HOURS') {
                                waitForQualityGate abortPipeline: true
                            }
                        }
                    }


        stage('Build Docker Image') {
            steps {
                dir('demo') {
                    script {
                        echo "======= Building backend Docker image ======="
                        bat "docker build -t ${DOCKER_IMAGE}:latest ."
                    }
                }
            }
        }

        stage('Scan Docker Image with Trivy') {
            steps {
                dir('demo') {
                    script {
                        echo "======= Running Trivy scan (HIGH & CRITICAL) ======="
                        bat """
                        docker run --rm -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy:latest image --severity HIGH,CRITICAL ${DOCKER_IMAGE}:latest
                        """
                    }
                }
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                dir('demo') {
                    script {
                        echo "======= Pushing backend Docker image to DockerHub ======="
                        withCredentials([
                            usernamePassword(
                                credentialsId: 'dockerhub_cred',
                                usernameVariable: 'DOCKER_USER',
                                passwordVariable: 'DOCKER_PASS'
                            )
                        ]) {
                            // Login to Docker Hub
                            bat "echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin"

                            // Push image
                            bat "docker push ${DOCKER_IMAGE}:latest"
                        }
                    }
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
