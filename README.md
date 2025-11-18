# Login đơn giản với java web

## 1. Giới thiệu

#### Dự án thô sơ chỉ bao gồm 1 đường dẫn web tương tự http://localhost:8081/demo-servlet/login (chạy trên local và chưa có domain name chính thức) với giao diện đơn giản kiểm tra việc nhập username và password có thành công hay không, nếu thành công thì gửi thông báo thành công, dữ liệu database người dùng được gắn cứng vào dữ liệu của mysql

```
  sql
-- Tạo database ltweb
CREATE DATABASE IF NOT EXISTS ltweb;
USE ltweb;

-- Tạo bảng users
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Chèn dữ liệu mẫu
INSERT INTO users (username, password) VALUES
('admin', '123456'),
('nhan', '2207'),
('tai', '1310');
```

## 2. Các tính năng

### 2.1. Đăng nhập (`/login`)
- Form đăng nhập với username và password
- Kiểm tra thông tin đăng nhập từ database MySQL
- Hiển thị thông báo lỗi nếu đăng nhập thất bại
- Lưu thông tin user vào session sau khi đăng nhập thành công

### 2.2. Trang chủ (`/home`)
- Hiển thị thông tin người dùng sau khi đăng nhập
- Yêu cầu đăng nhập để truy cập (kiểm tra session)
- Tự động chuyển hướng về trang login nếu chưa đăng nhập

### 2.3. Đăng xuất (`/logout`)
- Xóa session và đăng xuất người dùng
- Chuyển hướng về trang login

## 3. Cấu trúc dự án

```
demo-servlet/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── servlet/
│   │   │   │   ├── LoginServlet.java    # Xử lý đăng nhập
│   │   │   │   ├── HomeServlet.java     # Trang chủ sau khi đăng nhập
│   │   │   │   └── LogoutServlet.java   # Xử lý đăng xuất
│   │   │   ├── dao/
│   │   │   │   └── UserDAO.java         # Truy vấn database
│   │   │   ├── model/
│   │   │   │   └── User.java            # Model người dùng
│   │   │   └── config/
│   │   │       └── DBMySQLConnect.java  # Kết nối MySQL
│   │   └── webapp/
│   │       └── WEB-INF/
│   │           ├── view/
│   │           │   ├── login.jsp        # Giao diện đăng nhập
│   │           │   └── home.jsp         # Giao diện trang chủ
│   │           └── web.xml              # Cấu hình Jakarta EE
│   └── test/
└── pom.xml                               # Maven dependencies
```

## 4. Các cập nhật và cải thiện

### 4.1. Chuyển đổi sang Jakarta EE
- ✅ Đã chuyển từ `javax.servlet` sang `jakarta.servlet` (Jakarta EE 9+)
- ✅ Cập nhật `web.xml` sử dụng Jakarta EE 5.0 namespace
- ✅ Tất cả servlets sử dụng annotations `@WebServlet` với Jakarta

### 4.2. Sửa lỗi
- ✅ **Sửa lỗi typo**: `WEB_INF` → `/WEB-INF` trong LoginServlet
- ✅ **Sửa hiển thị lỗi**: Đổi `${alert}` thành `${error}` trong login.jsp
- ✅ **Loại bỏ dependency trùng**: Xóa `mysql-connector-java` (giữ lại `mysql-connector-j`)

### 4.3. Thêm tính năng mới
- ✅ **HomeServlet**: Xử lý route `/home` với kiểm tra session
- ✅ **LogoutServlet**: Xử lý đăng xuất và xóa session
- ✅ **home.jsp**: Trang hiển thị thông tin sau khi đăng nhập

### 4.4. Cải thiện giao diện
- ✅ Thêm CSS cho trang login (form đẹp hơn, responsive)
- ✅ Thêm CSS cho trang home
- ✅ Cải thiện hiển thị thông báo lỗi

### 4.5. Cập nhật dependencies
- ✅ Thêm `jakarta.servlet.jsp-api` (version 3.1.1)
- ✅ Cấu hình Java 11 trong maven-compiler-plugin
- ✅ Sử dụng Jakarta Servlet API 6.1.0
- ✅ Sử dụng Jakarta JSTL 3.0.1

## 5. Hướng dẫn chạy dự án với Spring Tools hoặc Eclipse

### Yêu cầu hệ thống
- **JDK**: 11 trở lên (Jakarta EE 9+ yêu cầu Java 11+)
- **Tomcat**: 10.x trở lên (hỗ trợ Jakarta EE)
- **Maven**: 3.6+
- **MySQL**: 8.0+

### Các bước chạy dự án

1. **Clone repository**
   ```bash
   git clone https://github.com/dangminhtai/login-ltweb
   ```

2. **Import vào Eclipse/Spring Tools**
   - File → Import → Existing Maven Projects
   - Chọn thư mục dự án
   - Đợi Maven download dependencies

3. **Cấu hình database**
   - Đảm bảo MySQL đang chạy
   - Tạo database và bảng theo script SQL ở phần 1
   - Kiểm tra thông tin kết nối trong `DBMySQLConnect.java`:
     - URL: `jdbc:mysql://localhost:3306/ltweb`
     - Username: `root`
     - Password: `12345` (có thể cần thay đổi)

4. **Cấu hình Tomcat**
   - Add Tomcat 10.x vào Eclipse
   - Deploy dự án lên Tomcat
   - Start server

5. **Truy cập ứng dụng**
   - Mở trình duyệt: `http://localhost:8080/demo-servlet/login`
   - Đăng nhập với một trong các tài khoản:
     - Username: `admin`, Password: `123456`
     - Username: `nhan`, Password: `2207`
     - Username: `tai`, Password: `1310`

## 6. Lưu ý quan trọng

⚠️ **Thông tin kết nối database**: File `DBMySQLConnect.java` có thông tin kết nối mặc định. Nếu MySQL của bạn có cấu hình khác, cần cập nhật:
- URL database
- Username
- Password

⚠️ **Port Tomcat**: Mặc định là 8080, nếu bạn dùng port khác (ví dụ 8081), cần cập nhật URL truy cập.

> Note: Chúc bạn chạy thành công!

---

## 7. Quản lý sản phẩm (Product CRUD)

### 7.1. Các route chính
- GET `/products` → Danh sách sản phẩm
- GET `/products/create` → Form tạo sản phẩm
- POST `/products/create` → Lưu sản phẩm mới
- GET `/products/edit?id=...` → Form sửa sản phẩm
- POST `/products/edit` → Cập nhật sản phẩm
- POST `/products/delete` → Xóa sản phẩm

Tất cả các route trên yêu cầu đăng nhập (kiểm tra session `user`). Từ trang Home có link "Quản lý sản phẩm" để điều hướng.

### 7.2. SQL tạo bảng và dữ liệu mẫu
Sử dụng MySQL database `ltweb` (theo `config/DBMySQLConnect.java`). Chạy các lệnh sau:

```sql
-- Tạo database nếu chưa có
CREATE DATABASE IF NOT EXISTS ltweb;
USE ltweb;

-- Tạo bảng products
CREATE TABLE IF NOT EXISTS products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  description TEXT NULL
);

-- Dữ liệu mẫu
INSERT INTO products (name, price, description) VALUES
('iPhone 15 Pro', 29990000.00, 'Bản Pro, màu Titan, 256GB'),
('Samsung Galaxy S23', 18990000.00, 'Chip Snapdragon, màn 120Hz'),
('Xiaomi Redmi Note 12', 5990000.00, 'Pin 5000mAh, sạc nhanh'),
('MacBook Air M2', 25990000.00, '8GB RAM, 256GB SSD'),
('Logitech MX Master 3S', 2190000.00, 'Chuột không dây cao cấp');
```

### 7.3. Cấu trúc mã nguồn liên quan
```
src/main/java/
├── dao/
│   └── ProductDAO.java
├── model/
│   └── Product.java
└── servlet/
    ├── ProductListServlet.java     # GET /products
    ├── ProductCreateServlet.java   # GET/POST /products/create
    ├── ProductEditServlet.java     # GET/POST /products/edit
    └── ProductDeleteServlet.java   # POST /products/delete

src/main/webapp/WEB-INF/view/product/
├── list.jsp    # danh sách + nút sửa/xóa
└── form.jsp    # form tạo/sửa dùng chung
```

### 7.4. Troubleshooting
- Lỗi `java.sql.SQLSyntaxErrorException: Table 'ltweb.products' doesn't exist`:
  - Chưa tạo bảng `products`. Hãy chạy SQL ở mục 7.2.
  - Kiểm tra đúng database đang dùng là `ltweb` và user/password trong `DBMySQLConnect.java`.
- Lỗi không truy cập được `/products`:
  - Cần đăng nhập trước tại `/login`.
  - Đảm bảo server Tomcat đang chạy và project đã được deploy thành công.
