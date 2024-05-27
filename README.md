# REST API and SPA Project For ToDos

> This repository contains an example of a web application based on a RESTful API with Spring Boot (`api/`) and a single page application with Vue.js v3 (`frontend/`).
> You can create and delete different todos with their assignees (frontend therefore does not exist)
> Please refer to the `README.md` files in the respective folders for concrete instructions.

## Quick Start

1. Clone this repository.
2. Follow the [api/README.md](/api/README.md) instructions in the `/api` folder to install the prerequisites for the backend.
3. Follow the [frontend/README.md](/frontend/README.md) instructions in the `/frontend` folder to install the prerequisites the frontend.
4. Start the API by navigating into its folder and executing 
    - `./mvnw spring-boot:run`
5. Now start the frontend by navigating into its folder and executing
    - `npm install`
    - `npm run dev`

## Usage

Assuming all prerequisites are fulfilled, you can follow these instructions to get to know the application:

### Testing the backend
- Open http://localhost:8080/api/v1/assignees in your browser. It will show you all assignees, probably there is none 
- Open http://localhost:8080/api/v1/todos in your browser. It will show you all todos, probably there is none

### Testing the frontend
- It will host the UI at http://localhost:5173, which you can now also open in your browser.
- Navigate to the Todos view by clicking on the respective menu item or via the direct ULR http://localhost:5173/#/todos.
- Delete a todo. Then confirm its deletion via the API by opening http://localhost:8080/api/v1/todos in your browser again.
