# spring-jpa-student


THIS README IS NOT UP-TO-DATE, IT WILL BE UPDATED SOON

## Contributors
- Sıla Özyurt (https://github.com/sila-ozyurt)

This project contributed by Aykut Büyükkılıç (linkedin.com/in/aykutbuyukkilic) during my internship term in İŞ YAZILIM


> Full-stack ready RESTful backend application for managing students, courses, departments, and student cards.

> Öğrenci, ders, bölüm ve öğrenci kartı işlemlerini yöneten Spring Boot tabanlı RESTful backend uygulaması.

---

## 📌 Project Description | Proje Açıklaması

This is a **Spring Boot** project built to manage student records, their departments, courses, and student cards.  
The system is modular and includes layered architecture with DTOs, Entities, Repositories, Mappers, Services, Controllers, and a Global Exception Handler.

Bu proje, öğrenciler, bölümler, dersler ve öğrenci kartlarını yönetmek amacıyla geliştirilmiş bir **Spring Boot** uygulamasıdır.  
Katmanlı mimari kullanılarak, DTO, Entity, Repository, Mapper, Service, Controller ve küresel hata yönetimi sınıflarıyla yapılandırılmıştır.

---

## 🗂 Project Structure | Proje Yapısı
com.hediyesilaozyurt
├── controller # REST API controllers
├── dto # Data Transfer Objects (DTOs)
├── entities # JPA Entities
├── exception # Exception Handling
├── mapper # ModelMapper-based entity/DTO conversion
├── repository # JPA Repository interfaces
├── services # Service Interfaces
├── services.impl # Service Implementations
└── config # Configuration classes



---

## ✅ Main Features | Ana Özellikler

- 🎓 Create, Read, Update, Delete for Students (Öğrenci işlemleri)
- 🆔 StudentCard assignment and search (Öğrenci kartı atama ve arama)
- 📚 Course management and student-course relations (Ders ve kayıt ilişkileri)
- 🏛 Department creation and listing (Bölüm yönetimi)
- 🔎 Search students by name (İsme göre arama)
- 📅 Sort students by birth date (Doğum tarihine göre sıralama)
- 🌐 Global exception handling with custom response format (Özelleştirilmiş global hata yönetimi)

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

