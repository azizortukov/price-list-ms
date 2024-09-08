# Price List Microservice

## Overview

Price List Microservice is a Spring Boot project that provides a set of CRUD operations for managing price lists. It exposes a RESTful API with endpoints for creating, reading, updating, and deleting price data. The service is dockerized and uses a PostgreSQL database, allowing easy deployment and scaling.

## Technologies Used

- **Spring Boot JPA** - For interacting with the database
- **Spring Boot Web** - For building REST APIs
- **PostgreSQL** - Database management
- **Lombok** - For reducing boilerplate code
- **Swagger Documentation** - API documentation and testing interface
- **MapStruct** - Object mapping between DTOs and entities
- **Docker & Docker Compose** - Containerization and orchestration

## API Endpoints

| Method | Endpoint                  | Description                          |
|--------|---------------------------|--------------------------------------|
| GET    | `/api/v1/price_list/{id}`  | Get price by ID                      |
| GET    | `/api/v1/price_list`       | Get all prices with optional filters |
| POST   | `/api/v1/price_list`       | Create a new price                   |
| PUT    | `/api/v1/price_list/{id}`  | Update an existing price by ID       |
| DELETE | `/api/v1/price_list/{id}`  | Delete price by ID                   |

## How to Use

The project is dockerized using Docker and Docker Compose. To run the application and its PostgreSQL database, follow these steps:

1. Make sure Docker is installed and running on your system.

2. Download the `docker-compose.yml` file from the root of the repository [here](./docker-compose.yml).

3. Run the following command to start the application and database containers:
   ```bash 
   docker compose up -d
   ```

   **This will start:**
   - The application container on port `8080`
   - The PostgreSQL database container on port `5432`

   *If you face problem as `Error response from daemon: Ports are not available`, you can modify the ports with the following command:*
   ```bash 
   PRICE_LIST_PORT=7070 POSTGRES_PORT=6060 docker-compose up -d
   ```

4. Access the Swagger UI at http://localhost:8080/swagger-ui.html to explore and test the API. After visit, when you use `GET` method, you can see 3 sample data of prices which are added as mock data.

   ***Keep in mind:** If you run on your custom port, then http://localhost:<entered_port>/swagger-ui.html.*

5. To stop the containers, run:
   ```bash
   docker compose down
   ```