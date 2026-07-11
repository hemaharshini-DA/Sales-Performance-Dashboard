# 📊 Sales Performance Dashboard

A full-stack Sales Performance Dashboard built using **Spring Boot**, **MySQL**, **HTML**, **CSS**, **JavaScript**, and **Chart.js**. The application helps businesses monitor sales performance, visualize analytics, and manage sales records efficiently.

---

## 🚀 Features

- 📈 Dashboard displaying Total Sales, Total Profit, and Total Products
- 🏆 Top Selling Product
- ➕ Add New Sales Record
- ✏️ Update Existing Sales
- ❌ Delete Sales Record
- 🔍 Search Products
- 🌍 Filter Products by Region
- 📊 Sales by Region (Bar Chart)
- 🥧 Sales Distribution (Pie Chart)
- 📅 Monthly Sales Trend (Line Chart)
- 💾 MySQL Database Integration
- 🔄 REST API using Spring Boot

---

## 🛠 Tech Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- REST API

### Frontend
- HTML5
- CSS3
- JavaScript
- Chart.js

### Database
- MySQL

### Tools
- Eclipse IDE
- Maven
- Git
- GitHub
- Postman

---

## 📂 Project Structure

```
salesdashboard
│
├── src
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   └── resources
│
├── pom.xml
└── README.md
```

---

## 📷 Screenshots

(Add screenshots here after capturing them.)

### Dashboard

<img width="100%" src="images/dashboard.png">

### Charts

<img width="100%" src="images/charts.png">

---

## ▶️ How to Run

1. Clone the repository

```bash
git clone https://github.com/hemaharshini-DA/Sales-Performance-Dashboard.git
```

2. Open in Eclipse or IntelliJ IDEA

3. Configure MySQL in `application.properties`

4. Run

```
SalesdashboardApplication.java
```

5. Open

```
http://localhost:8080
```

---

## 📡 REST API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | /sales | Get All Sales |
| POST | /save | Add Sale |
| PUT | /update/{id} | Update Sale |
| DELETE | /delete/{id} | Delete Sale |
| GET | /total-sales | Total Sales |
| GET | /total-profit | Total Profit |
| GET | /sales-count | Total Products |
| GET | /sales-by-region | Sales by Region |
| GET | /top-product | Top Selling Product |
| GET | /monthly-sales | Monthly Sales |

---

## 🔮 Future Enhancements

- User Authentication
- Export to Excel/PDF
- Dark Mode
- Sales Forecasting
- Product Images
- Responsive Mobile Dashboard

---

## 👩‍💻 Author

**Hema Harshini**

GitHub:
https://github.com/hemaharshini-DA
