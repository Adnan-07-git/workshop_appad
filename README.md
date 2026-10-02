# workshop-app — Course Registration Portal

> ⚠️ **Training application.** This app **intentionally contains security flaws** (vulnerable library,
> hard-coded secrets, injectable SQL, weak TLS, an insecure Dockerfile). It exists so that DevSecOps workshop
> participants can **find, understand and fix** them with real tools. **Never deploy it outside a lab.**

A small **Java 21 / Spring Boot 3** web application where students can see courses and register for them.
It is the single application used in every lab of the DevSecOps workshop (`DevSecOps_VCE` lab guide).

## Tech stack

| Part | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5 (embedded Tomcat, Thymeleaf, JdbcTemplate, Actuator) |
| Database | H2 in-memory (schema + sample data loaded at startup) |
| Build | Maven |
| Port | **8081** |

## Build and run

```bash
mvn clean package
java -jar target/workshop-app.jar
```

Open `http://<SERVER_IP>:8081`.

## Endpoints

| URL | Description |
|---|---|
| `/` | Course list + registration form |
| `POST /register` | Register a student (HTML form) |
| `/api/courses` | Courses as JSON |
| `/api/students/search?name=Asha` | Search students by first name |
| `/api/badge?name=Asha` | Student badge |
| `/api/admin/stats` | Statistics (requires header `X-Admin-Password`) |
| `/actuator/health` | Health check → `{"status":"UP"}` |

## Project layout

```
.
├── Dockerfile-bad                  # deliberately insecure Dockerfile (Day 1, Lab 06)
├── pom.xml                         # Maven build: dependencies + JaCoCo, SonarQube, Dependency-Check plugins
└── src
    ├── main
    │   ├── java/com/workshop/registration
    │   │   ├── WorkshopApplication.java
    │   │   ├── config/             # PartnerApiConfig
    │   │   ├── model/              # Course, Student, Confirmation
    │   │   ├── repository/         # JdbcTemplate data access
    │   │   ├── service/            # Registration, badges, certificate storage
    │   │   └── web/                # HTML controller + JSON APIs
    │   └── resources
    │       ├── application.properties
    │       ├── schema.sql, data.sql
    │       ├── templates/          # Thymeleaf pages
    │       └── static/style.css
    └── test/java/...               # JUnit 5 + MockMvc tests
```

## Security tooling already configured in `pom.xml`

| Tool | Command | Notes |
|---|---|---|
| JaCoCo (coverage) | `mvn verify` | Report in `target/site/jacoco/` — used by SonarQube |
| SonarQube (SAST) | `mvn verify sonar:sonar -Dsonar.host.url=... -Dsonar.token=...` | Analyses `src/main/java` |
| OWASP Dependency-Check (SCA) | `mvn dependency-check:check` | DB in `/opt/dc-data`, NVD key from env var `NVD_API_KEY`, fails on CVSS ≥ 9 |

## Acknowledgement

Inspired by the Spring Boot sample used in the `vickydevo/springboot` and `vickydevo/DevSecOps-WS` workshop
repositories; rewritten as a new application for this workshop.
