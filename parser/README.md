# Resume Parser API

### This project is a RESTful API for parsing resumes. It uses chunking and an LLM (Large Language Model) client to extract and fill in details from resume documents. The API processes uploaded resume files and returns a structured JSON representation of the user's details.



### Curl cmd
```
curl --location 'http://localhost:8080/api/v1/parsing/upload' \
--form 'file=@"/C:/Users/prakash/Downloads/resume.txt"'
```

#### sample response
```
{
    "name": "Prakash A",
    "email": "prakasha@gmail.com",
    "contactNumber": "9342175722",
    "city": "Chennai, Tamil Nadu, India",
    "skills": [
        "Java",
        "JavaScript",
        "Python",
        "HTML",
        "CSS",
        "Typescript",
        "React.js",
        "spring boot",
        "flask",
        "Hibernate",
        "Spring Security",
        "spring cloud",
        "Tailwind CSS",
        "Boot strap",
        "swagger",
        "redux",
        "MongoDB",
        "MySQL",
        "Firebase",
        "Visual Studio Code",
        "IntelliJ IDEA",
        "Git & Github",
        "postman"
    ],
    "employment": [
        {
            "organizationName": "Arakoo.ai",
            "role": "Backend Developer Intern",
            "description": "Developed a built-in JSON plugin that converts OpenAI text into the JSON format for an open-source project called EdgeChain, using spring-boot. Acquired skills in spring boot, Jsonnet, JUnit, Mockito during the internship at Arakoo.ai."
        },
        {
            "organizationName": "MNC",
            "role": "Java full stack Developer Intern",
            "description": "Developed REST APIs using Spring Boot, enabling communication between the front-end and back-end. Implemented authentication and authorization functionality using Spring Security."
        },
        {
            "organizationName": "Suven Consultants and Technology Pvt. Ltd.",
            "role": "JavaScript Coding Intern",
            "description": "Completed one month internship focused on JavaScript development."
        }
    ],
    "projects": [
        {
            "projectName": "College-Student Connect Web app",
            "technologyStack": [
                "Spring-boot",
                "React js",
                "MySql",
                "tailwind css"
            ]
        },
        {
            "projectName": "Health Monitoring Web app",
            "technologyStack": [
                "React.js",
                "Tailwind css",
                "Firebase-auth",
                "Firbase-Firestore"
            ]
        },
        {
            "projectName": "Job recommender Web app",
            "technologyStack": [
                "TailwindCSS",
                "JavaScript",
                "Flask",
                "RapidAPI",
                "Firebase",
                "Railway"
            ]
        },
        {
            "projectName": "ChatGPT JSON Format Plugin",
            "technologyStack": [
                "Spring boot",
                "react js",
                "TypeScript",
                "tailwind css"
            ]
        }
    ]
}
```
