# Smart Library System (Deploy-ready)

## What this package includes
- Java Spring Boot app (Thymeleaf frontend)
- H2 database (local file: ./data/librarydb)
- Dockerfile for building a container
- render.yaml for Render.com deployment
- Default admin credentials: admin / admin

## Quick local run
1. Install Java 17+ and Maven.
2. Unzip the folder and open terminal in the project root.
3. Run:
   ```
   mvn spring-boot:run
   ```
4. Open in Chrome:
   - http://localhost:8080/login
   - Use admin / admin

## Deploy to Render (one-click)
1. Create a GitHub repository and push the entire project.
2. Sign in to https://render.com using GitHub.
3. Click "New" → "Blueprint" and select your repo.
4. Render will detect render.yaml and build the Docker image.
5. After deploy, you'll get a public URL like:
   https://smart-library-system.onrender.com

## H2 Console (if needed)
- <app-url>/h2-console
- JDBC URL: jdbc:h2:./data/librarydb
- User: sa  Password: (leave blank)
