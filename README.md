
#  e-learningProject — Deployment Setup

This project is currently under active development and deployment configuration.  
The goal is to simulate a **production-like environment** locally using **Docker**, **Jenkins**, and **Ngrok** for secure external access and testing.

---

## 🧩 Deployment Architecture

- **Frontend:** Angular application served via **Nginx**
- **Backend:** Spring Boot (Java 17) application exposed via **REST APIs**
- **Database:** Postgres (containerized)
- **Reverse Proxy:** Nginx handles routing between frontend and backend
- **CI/CD:** Jenkins pipeline for automated build, test, and deployment
- **Public Access:** Ngrok used to expose the local environment securely for testing

---

## ⚙️ How It Works

1. **Docker Compose** orchestrates the containers:
   - `frontend` → Angular + Nginx
   - `backend` → Spring Boot + OpenJDK
   - `postgres-madrasati-db` → postgres database
   - `nginx-proxy` → Reverse proxy for routing

2. **Jenkins Pipeline** automates the process:
   - Pulls the latest code from GitHub
   - Builds Docker images for frontend and backend
   - Runs unit tests and integration tests
   - Deploys all services using `docker-compose up -d`

3. **Ngrok** creates a secure HTTPS tunnel to expose the environment publicly:
   - Useful for testing APIs, authentication, and webhooks
   - Example public URL: `https://example.ngrok-free.dev`

---

## 🧪 Testing Environment

- Ngrok URL simulates a real production domain.
- All backend endpoints (e.g. `/api/auth/signin`) are accessible via HTTPS.
- CORS and Nginx configurations are being optimized for real-world deployment scenarios.

---

## 🔧 Current Work in Progress

- [x] Docker environment setup (frontend, backend, database)
- [x] Jenkins pipeline for automated build and deploy
- [x] Nginx reverse proxy configuration
- [x] CI/CD optimization (multi-branch pipelines)
- [ ] Advanced monitoring and logging setup
- [ ] Production-ready SSL configuration
- [ ] kubernetes for orchestrations

---

## 🧠 Author

**Mohamed Mnassri**  
Software Engineer | Java & Angular Developer  
Working on DevOps automation and production-grade deployments

