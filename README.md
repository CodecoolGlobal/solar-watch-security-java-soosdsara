<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a id="readme-top"></a>

<!-- PROJECT SHIELDS -->
[![LinkedIn][linkedin-shield]][linkedin-url]

<h3 align="center">Solar Watch</h3>


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#with-docker">Steps with Docker</a></li>
        <li><a href="#without-docker">Steps withot Docke</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

A small application that simply shows us the time of sunrise and sunset by entering a date and city.

[![Login Screen Shot][login-screenshot]]

### Built With
<a id="built-with"></a>

* [![JavaScript][JavaScript.com]][JavaScript-url]
* [![Java][Java.com]][Java-url]
* [![Vite][Vite.com]][Vite-url]
* [![React][React.js]][React-url]
* [![Spring Boot][Spring.com]][Spring-url]
* [![Postgresql][Postgresql.com]][Postgresql-url]
* [![Docker][Docker.com]][Docker-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started
<a id="getting-started"></a>

### Prerequisites
<a id="prerequisites"></a>

**With Docker**

- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Docker](https://docs.docker.com/desktop/)
- [Docker Compose](https://docs.docker.com/compose/install/)

**Without Docker**

- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Node.js](https://nodejs.org/)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Postgresql](https://www.postgresql.org/)


### Installation
<a id="installation"></a>

1. Clone the repo
 ```
 git clone https://github.com/your_username_/Project-Name.git
 cd Project-Name
 ```

2. Create a new PostgreSQL database in terminal or with pgAdmin4.

3. Set the following environment variables in the .env file:
```
JWT_SECRET=your_jwt_secret_key
DATABASE_USERNAME=your_database_username
DATABASE_PASSWORD=your_database_password
``` 

#### With Docker
<a id="with-docker"></a>

4. Set the following environment variables in the .env file:
```
DATABASE_URL=jdbc:postgresql://solarwatch-database:5432/your_database
BACKEND_URL=http://backend:8080
``` 

5. Run the following command to start all necessary services:
```
docker-compose up
```

Backend availability: http://localhost:8080

Frontend availability: http://localhost:3000


#### Without Docker
<a id="without-docker"></a>

  **Backend:**

  4. Set the following environment variables in the .env file:
  ```
  DATABASE_URL=jdbc:postgresql://localhost:5432/your_database
  BACKEND_URL=http://localhost:8080
  ``` 
  
  5. Navigate to the backend library:
   ```
   cd backend
   ```
  
  6. Download dependencies:
   ```
   ./mvnw clean install
   ```
  
  7. Start the application:
   ```
   ./mvnw spring-boot:run
   ```

Backend availability: http://localhost:8080

  **Frontend:**

  4. Open a new terminal window and navigate to the frontend directory:
  ```
  cd frontend
  ```
  
  5. Install dependencies:
  ```
  npm install
  ```
  
  6. Start the application:
  ```
  npm run dev
  ```

Frontend availability: http://localhost:5173


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage
<a id="usage"></a>

Simply select a date, enter a city name, and the two times will appear.

[![Example Screen Shot][example-screenshot]]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap
<a id="roadmap"></a>

- [x] Add UI message
- [ ] Add Loading page
- [ ] Add unique username Verification
- [ ] Add password Verification
- [ ] Create Admin page

See the [open issues](https://github.com/CodecoolGlobal/solar-watch-security-java-soosdsara/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[linkedin-shield]: https://img.shields.io/badge/LinkedIn-frontend?logo=Linkedin&logoColor=black&labelColor=blue&color=blue
[linkedin-url]: https://linkedin.com/in/sára-soós-251772305
[product-screenshot]: images/screenshot.png
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&labelColor=red&color=red
[React-url]: https://reactjs.org/
[Postgresql.com]: https://img.shields.io/badge/PostgreSQL-sql?style=for-the-badge&logo=postgresql&color=yellow&labelColor=yellow
[Postgresql-url]: https://www.postgresql.org/
[Spring.com]: https://img.shields.io/badge/Spring_Boot-boot?style=for-the-badge&logo=Spring%20Boot&labelColor=grey&color=grey
[Spring-url]: https://spring.io/projects/spring-boot
[Java.com]: https://img.shields.io/badge/Java-language?style=for-the-badge&logo=openjdk&labelColor=grey&color=grey
[Java-url]: https://www.oracle.com/java/technologies/?er=221886
[JavaScript.com]: https://img.shields.io/badge/JavaScript-language?style=for-the-badge&logo=javascript&labelColor=red&color=red
[JavaScript-url]: https://developer.mozilla.org/en-US/docs/Web/JavaScript
[Vite.com]: https://img.shields.io/badge/Vite-frontend?style=for-the-badge&logo=vite&labelColor=yellow&color=yellow
[Vite-url]: https://vitejs.dev/
[Docker.com]: https://img.shields.io/badge/Docker-docker?style=for-the-badge&logo=Docker&labelColor=red&color=red
[Docker-url]: https://www.docker.com/
[login-screenshot]: images/login.png
[example-screenshot]: images/example.png

