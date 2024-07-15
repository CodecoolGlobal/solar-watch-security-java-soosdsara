<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a id="readme-top"></a>

<!-- PROJECT SHIELDS -->
[![LinkedIn][linkedin-shield]][linkedin-url]

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
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

A small application that simply shows us the time of sunrise and sunset by entering a date and city.

KÉP

### Built With

* [![JavaScript][JavaScript.com]][JavaScript-url]
* [![Java][Java.com]][Java-url]
* [![Vite][Vite.com]][Vite-url]
* [![React][React.js]][React-url]
* [![Spring Boot][Spring.com]][Spring-url]
* [![Postgresql][Postgresql.com]][Postgresql-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

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

1. Clone the repo
 ```
 git clone https://github.com/your_username_/Project-Name.git
 cd Project-Name
 ```

#### With Docker

1. Set the following environment variables:
 ```
${JWT_SECRET}
${DATABASE_URL}
${DATABASE_USERNAME}
${DATABASE_PASSWORD}
 ```

2. Run the following command to start all necessary services:
```
docker-compose up
```

Backend availability: http://localhost:8080

Frontend availability: http://localhost:5173


#### Without Docker

**Backend:** 
1. Navigate to the backend library:
 ```
 cd backend
 ```
2. Set the following environment variables:
  ```
  ${JWT_SECRET}
  ${DATABASE_URL}
  ${DATABASE_USERNAME}
  ${DATABASE_PASSWORD}
  ```
 3. Download dependencies:
 ```
 ./mvnw clean install
 ```
 4. Start the application:
 ```
 ./mvnw spring-boot:run
 ```

Backend availability: http://localhost:8080

**Frontend:**
1. Open a new terminal window and navigate to the frontend directory:
```
cd frontend
```
2. Install dependencies:
```
npm install
```
3. Start the application:
```
npm run dev
```

Frontend availability: http://localhost:5173

**Express:**
.........


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

Simply select a date, enter a city name, and the two times will appear.

KÉP

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] Add UI message
- [ ] Add Loading page
- [ ] Add unique username Verification
- [ ] Add password Verification
- [ ] Create Admin page

See the [open issues](https://github.com/CodecoolGlobal/solar-watch-security-java-soosdsara/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/LinkedIn-frontend?logo=Linkedin&logoColor=black&labelColor=blue&color=blue
[linkedin-url]: www.linkedin.com/in/sára-soós-251772305
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
