## Overview
This project is a backend service for our application. TO run this project locally, you need to follow the steps below to set up the database and configure the application.
## Prerequisites
* Java 17 or higher
* Maven
* MySQL
* Springboot

## Configure the application
1. Import the `nikon.sql` file into your database.
2. Open the `application.yml` file located in the `src/main/resources` directory.
3. Update the database configuration with your MySQL credentials:
```
spring:
  application:
    name: nikon-backend
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    url: jdbc:mysql://localhost8080:3306/nikon
    username: root
    password: 12345678
```
Replace `password` with your actual password respectively.

4. Update the mail configuration with your email credentials. Replace `your_email@qq.com` and `your_email_password` with your actual QQ email and password.
```
mail:
host: smtp.qq.com
port: 587
username: your_email@qq.com
password: your_email_password
from: your_email@qq.com
```

5. Update the GitHub configuration with your GitHub credentials:
```
github:
  bucket:
    user: "your_github_username"
    repository: "your_repository"
    access-token: "your_github_access_token"
    url: "https://cdn.jsdelivr.net/gh/${github.bucket.user}/${github.bucket.repository}/"
    api: "https://api.github.com/repos/${github.bucket.user}/${github.bucket.repository}/contents/"
```
Replace `your_github_username`, `your_repository`, and `your_github_access_token` with your actual GitHub username, repository, and access token. You can obtain a GitHub access token from your GitHub account settings.