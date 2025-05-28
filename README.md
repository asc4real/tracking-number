# 🚚 Scalable Tracking Number Generator API

This project is a RESTful API for generating **unique, scalable, and concurrent-safe tracking numbers** for logistics systems. Built using **Spring Boot**, it is designed to handle high-throughput environments and concurrent requests.

---

## 🧠 Features

- ✅ Generates tracking numbers that match the pattern `^[A-Z0-9]{1,16}$`
- ✅ Guarantees **uniqueness** and **thread-safety** using atomic counters and concurrent maps
- ✅ Supports **concurrent requests** and horizontal scaling
- ✅ Accepts detailed metadata via query parameters
- ✅ Returns tracking numbers with an RFC 3339 timestamp

---

## 🔗 Live Demo

> 📌 Add your deployed API URL here  
`https://your-api-host.com/next-tracking-number`

---

## 🚀 Endpoint

**GET** `/next-tracking-number`

### Query Parameters

| Parameter             | Type    | Description                            |
|-----------------------|---------|----------------------------------------|
| `origin_country_id`   | String  | ISO 3166-1 alpha-2 code (e.g., `MY`)   |
| `destination_country_id`| String| ISO 3166-1 alpha-2 code (e.g., `ID`)   |
| `weight`              | String  | Parcel weight in kg (e.g., `1.234`)    |
| `created_at`          | String  | RFC 3339 timestamp                     |
| `customer_id`         | UUID    | Customer UUID                          |
| `customer_name`       | String  | Customer name                          |
| `customer_slug`       | String  | Slugified customer name (e.g., `redbox-logistics`) |

### Example Request

```bash
curl -X GET "http://localhost:8080/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2018-11-20T19:29:32%2B08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox%20Logistics&customer_slug=redbox-logistics"
