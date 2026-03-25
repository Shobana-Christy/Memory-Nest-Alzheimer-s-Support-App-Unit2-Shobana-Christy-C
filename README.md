🧠 Memory Nest – Alzheimer’s Support App

A full‑stack web application designed to help individuals with memory challenges to stay organized, emotionally connected, and mentally engaged. Memory Nest provides a calming, accessible experience with features like Google OAuth login, Reminders, Memory Spot (photo storage), and an Engagement Center.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

✨ Features

🔐 Google OAuth Login
- Secure authentication using Google OAuth2
- Smooth redirect flow between React (frontend) and Spring Boot (backend)

⏰ Reminders Page (CRUD)
- Add, edit, delete, and view reminders
- Backend built with Spring Boot REST APIs
- Data stored in MySQL
- Designed for simplicity and ease of use
- Helps users to remind important days and appointments
 
📸 Memory Spot – Photo Upload & Display
- Upload cherished photos
- Images stored securely in Cloudinary
- Supports emotional connection and memory recall

🎮 Engagement Center
- Simple activity links stored in the database
- Activities include games like Chess and Sudoku
- Helps users stay mentally active

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🚀 Tech Stack

🎨 FrontEnd
  
- React (Vite)
- JavaScript
- HTML
- CSS
  
⚙️ Backend and Database
  
- Java
- Spring Boot
- Spring Web
- Spring Security
- MySQL
- Cloudinary

🛠️ Tools
  
- Postman
- Git
- GitHub
- VS Code/ IntelliJ IDEA
  
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  🛠️ Prerequisites & Installation
  
📌 Note
To run Memory Nest locally, ensure you have the following installed:
- Node.js 
- npm 
- Java Development Kit (JDK) 21
- MySQL Server (8.0+)
- A Google OAuth Client ID & Secret
- A Cloudinary account (for image storage)

⚙️ Back End Setup (Java / Spring Boot / MySQL)
1. Clone the repository
In your terminal, navigate to the directory where you want the project to live, then run:

  git clone https://github.com/Shobana-Christy/Memory-Nest-Alzheimer-s-Support-App-Unit2-Shobana-Christy-C.git
  cd Memory-Nest-Alzheimer-s-Support-App-Unit2-Shobana-Christy-C/backend


2. Configure application properties
Create or update the file:
src/main/resources/application.properties


# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/memorynest
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update

# Google OAuth2
spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}
spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}

# Cloudinary
cloudinary.cloud-name=${CLOUDINARY_CLOUD_NAME}
cloudinary.api-key=${CLOUDINARY_API_KEY}
cloudinary.api-secret=${CLOUDINARY_API_SECRET}


3. Create a .env file (Backend Only)
Create this file inside the backend folder:
backend/.env


Add your real credentials:
# MySQL
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password

# Cloudinary
CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret

# Google OAuth
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret


4. Create the MySQL database
Open MySQL Workbench:
CREATE DATABASE memorynest;


Hibernate will automatically create the tables.

5. Run the Spring Boot application
If using IntelliJ, simply click Run.
Or run from terminal:
mvn spring-boot:run


🟢 The backend API will now be running at:
http://localhost:8080

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🎨 Front End Setup (React / Vite)
1. Navigate to the frontend directory
cd ../frontend


2. Install dependencies
npm install


3. Run the React application
npm run dev



🟢 The frontend will be available at:
http://localhost:5173



🧠 Google OAuth Setup (Required)
- Go to Google Cloud Console
- Create OAuth credentials
- Add authorized redirect URIs:
http://localhost:8080/login/oauth2/code/google




☁️ Cloudinary Setup (Required)
- Create a Cloudinary account
- Go to your Cloudinary Dashboard
- Copy the following values:
- Cloud Name
- API Key
- API Secret
Add them to your backend .env

🗄️ Database Structure (ERD)
Memory Nest uses a simple, clean MySQL structure:
- User (Google OAuth authenticated)
- Reminder — linked to user
- Engagement Activities — stored in DB
  
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------


ERD
https://lucid.app/lucidspark/9d2b2843-d8d1-4670-8f6e-1d2fd391af33/edit?invitationId=inv_dfdd33ef-9df0-4d1f-a192-f129cbe96d03&page=0_0#
  
Wireframing
https://www.figma.com/proto/8IUfRacFXGzOPaXs0yDJaS/Memory-Nest?node-id=41-731&t=mspCRetxC2JZ6qKd-1&starting-point-node-id=41%3A731

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# ⚙️ API Endpoints  
The following RESTful endpoints manage reminders, memory albums, engagement activities, and authentication.  
All user‑specific endpoints require Google OAuth authentication.



## ⏰ Reminders 📝  
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| 🟢 GET | `/reminders` | Retrieve all reminders for the logged‑in user | 🔐 Auth Required |
| 🟢 GET | `/reminders/{id}` | Retrieve a specific reminder by ID | 🔐 Auth Required |
| 🟡 POST | `/reminders` | Create a new reminder | 🔐 Auth Required |
| 🟡 PUT | `/reminders/{id}` | Update an existing reminder | 🔐 Auth Required |
| 🔴 DEL | `/reminders/{id}` | Delete a reminder by ID | 🔐 Auth Required |

---

## 🖼️ Memory Spot (Albums & Images) 📸  
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| 🟢 GET | `/memoryspot/albums` | Retrieve all albums for the logged‑in user | 🔐 Auth Required |
| 🟡 POST | `/memoryspot/albums` | Create a new album with multiple images | 🔐 Auth Required |
| 🟢 GET | `/memoryspot/albums/{albumName}` | Retrieve all items inside a specific album | 🔐 Auth Required |
| 🟡 POST | `/memoryspot/albums/{albumName}/items` | Add a single image to an existing album | 🔐 Auth Required |

---

## 🎮 Engagement Center 🎯  
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| 🟢 GET | `/engagementcenter` | Retrieve all engagement activities | 🌎 Public |

---

## 🔐 Authentication (Google OAuth)  
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| 🟢 GET | `/oauth2/authorization/google` | Redirect user to Google login | 🌎 Public |
| 🟢 GET | `/login/oauth2/code/google` | Google OAuth callback | 🌎 Public |
| 🟢 GET | `/user` | Get logged‑in user details | 🔐 Auth Required |
| 🟢 GET | `/logout` | Logout user and clear session | 🔐 Auth Required |



-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🚀 Future Enhancements

🛡️ Admin System
- Manage users
- Monitor uploads and activities
- Approve or remove content
📝 Registration System
- Email/password signup
- Role‑based access (Admin, Caregiver, User)

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

✍️ Author: Shobana Christy


Full‑Stack Developer (React • Java • Spring Boot)







  

