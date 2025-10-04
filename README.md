# spring-jpa-student


## Contributors
- Sıla Özyurt (https://github.com/sila-ozyurt)

This project contributed by Aykut Büyükkılıç (linkedin.com/in/aykutbuyukkilic) during my internship term in İŞ YAZILIM


>A full-stack **Spring Boot Student Management API** that supports **JWT Authentication**, **Role-Based Access Control**, **Exception Handling**, and **Data Management** for students, courses, departments, and student cards.  

> JWT tabanlı kimlik doğrulama, rol bazlı yetkilendirme, hata yönetimi ve öğrenci, bölüm, ders, öğrenci kartı yönetimi sunan Spring Boot REST API projesi.

---
## 🚀 Features
- 🔐 **Authentication & Authorization** with JWT (Access & Refresh Tokens)  
- 👨‍🎓 Manage **Students**, **Departments**, **Courses**, **Student Cards**  
- 📊 **Department Statistics** with Scheduled Tasks  
- 🛡️ **Global Exception Handling** (validation, DB constraints, type mismatches)  
- 🗂️ DTO & Mapper Layer for clean data transfer  
- 🗄️ Repositories with custom queries (pagination, search, statistics)  
- ⚡ REST API with clear response wrapper (`RootEntity<T>`)  


🔐 JWT ile Kimlik Doğrulama & Yetkilendirme
👨‍🎓 Öğrenci, Bölüm, Ders, Öğrenci Kartı yönetimi
📊 Zamanlanmış Görevler ile departman istatistikleri
🛡️ Global Exception Handling (doğrulama, DB constraint, tip hataları)
🗂️ DTO & Mapper yapısı
🗄️ Repository özel sorguları (sayfalama, arama, istatistik)
⚡ Response Wrapper (RootEntity<T>)
---

## 📌 Project Description | Proje Açıklaması

This is a Spring Boot application designed to manage students, their departments, courses, and student cards.
It follows a layered architecture, including DTOs, Entities, Repositories, Mappers, Services, Controllers, and a Global Exception Handler for clean and maintainable code.

Bu proje, öğrenciler, bölümler, dersler ve öğrenci kartlarını yönetmek amacıyla geliştirilmiş bir Spring Boot uygulamasıdır.
Katmanlı mimari kullanılarak; DTO, Entity, Repository, Mapper, Service, Controller ve küresel hata yönetimi sınıflarıyla yapılandırılmıştır.

---

## 🗂 Project Structure | Proje Yapısı
com.hediyesilaozyurt
├── config  #mapper, security, swagger etc. config files
├── controller # REST API controllers
├── dto # Data Transfer Objects (DTOs)
├── entities # JPA Entities
├── exception # Exception Handling
├── jwt
├── mapper # ModelMapper-based entity/DTO conversion
├── repository # JPA Repository interfaces
├── scheduled
├── services # Service Interfaces
└── starter



---

## ✅ Main Features | Ana Özellikler

- 🎓 CRUD operations for students (Öğrenci işlemleri)

🆔 StudentCard assignment & lookup (Öğrenci kartı atama ve arama)

📚 Course management & student-course relationships (Ders yönetimi ve öğrenci-ders ilişkileri)

🏛 Department creation & listing (Bölüm yönetimi)

🔎 Search students by name (İsme göre öğrenci arama)

📅 Sort students by birth date (Doğum tarihine göre sıralama)

🌐 Global Exception Handling with custom response (Özelleştirilmiş global hata yönetimi)

🔐 JWT Authentication & Role-based Authorization (JWT ile kimlik doğrulama ve rol bazlı yetkilendirme)

⏰ Scheduled tasks for department statistics (Departman istatistikleri için zamanlanmış görev)

---

## 🧪 How to Run the Project | Projeyi Çalıştırma

### Prerequisites | Gereksinimler
- Java 17+
- Maven
- PostgreSQL (or other supported DB)
- Postman / Swagger for testing APIs

### Step-by-step | Adım adım
```bash
# 1. Clone the repository
git clone <your-repo-url>
cd project-folder

# 2. Configure application.properties
# src/main/resources/application.properties dosyasına DB bilgilerini girin

# 3. Build the project
mvn clean install

# 4. Run the app
mvn spring-boot:run




Used Technologies 

-Spring Boot 3

-Spring Data JPA

-Hibernate

-ModelMapper

-Jakarta Validation

-PostgreSQL (or any RDBMS)

-Lombok

-SLF4J Logging

