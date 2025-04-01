# 🛒 E-Commerce App (Amazon Clone)

## ⚠️ Disclaimer
The backend of this project is hosted on a free server using Render. Before running the application, download and execute the following file in the terminal:

🔗 [Download ConcurrentApiCaller](https://drive.google.com/file/d/1NR6pQlaEMbLLXzMvfd08MzsvZu6w5Q4D/view?usp=share_link)

```sh
java ConcurrentApiCaller
```
Once the response is **OK**, you can proceed with using the app.

---

## ✨ Features

### 🔄 Caching & Single Source of Truth
- 🗄️ The application follows a **Single Source of Truth** principle, ensuring data consistency.
- 📦 **Caching Mechanism**: All data is first stored in a local database before being displayed in the UI.
- 🚀 Improves performance, provides offline support, and reduces redundant API calls.

### 🔐 Authentication
- ✅ **Login Page**: User authentication using **Bearer Token**.
- 📝 **Registration Page**: New users can create an account.
- 🔄 **Forgot Password**: Users receive an **OTP via email** to reset their password.

### 🛍️ Shopping
- 🏠 **Home Screen**: Displays products with an option to **add to cart**.
- 🎯 **Filtering**: Filter products based on various criteria.
- 🛒 **Cart Screen**: View added products and total price.
- 🏷️ **Checkout**: Enter **address, email, and mobile number** before proceeding to payment.
- 💳 **Payment Integration**: Supports **Razorpay UPI & Card payments**.

### 📦 Orders & Profile
- 📋 **Orders Page**: View all past orders.
- 👤 **Profile Page**: Manage addresses and log out.

---

## 📸 Screenshots

### 🔑 Authentication Screens
- **Intro Screen**  
  <img src="screenshots/intro_screen.png" alt="Intro Screen" width="300px">
- **Login Screen**  
  <img src="screenshots/login_screen.png" alt="Login Screen" width="300px"> <img src="screenshots/login_screen_2.png" alt="Login Screen 2" width="300px">
- **Registration Screen**  
  <img src="screenshots/registration_screen_1.png" alt="Registration Screen" width="300px"> <img src="screenshots/registration_screen_2.png" alt="Registration Screen 2" width="300px">
- **Forgot Password - OTP Verification**  
  <img src="screenshots/otp_screen_1.png" alt="OTP Screen" width="300px"> <img src="screenshots/otp_screen_2.png" alt="OTP Screen 2" width="300px">  
  📧 **Received OTP Email**  
  <img src="screenshots/otp_mail.png" alt="OTP Mail" width="300px">
- **Change Password**  
  <img src="screenshots/change_password_1.png" alt="Change Password" width="300px">

### 🛍️ Shopping Screens
- **Home Screen**  
  <img src="screenshots/home_screen.png" alt="Home Screen" width="300px">
- 🎥 **Home Screen - GIF (Auto Play)**  
  <img src="screenshots/home_screen.gif" alt="Home GIF" width="300px">
- **Filter Screens**  
  <img src="screenshots/filter_1.png" alt="Filter 1" width="300px"> <img src="screenshots/filter_2.png" alt="Filter 2" width="300px">  
  <img src="screenshots/filter_3.png" alt="Filter 3" width="300px"> <img src="screenshots/filter_4.png" alt="Filter 4" width="300px">

- 🎥 **Filter Screen - GIF (Auto Play)**  
  <img src="screenshots/filter_screen.gif" alt="Filter GIF" width="300px">

### 🛒 Cart & Checkout Screens
- **Cart Screen**  
  <img src="screenshots/cart_screen.png" alt="Cart Screen" width="300px">
- 🏷️ **Checkout (Bottom Sheet for Address & Contact Details)**  
  <img src="screenshots/checkout_screen.png" alt="Checkout" width="300px">
- 💳 **Payment (Razorpay UPI & Card Demo)**  
  🎥 <img src="screenshots/payment.gif" alt="Payment GIF" width="300px">

### 📦 Orders & Profile Screens
- **Orders Page**  
  <img src="screenshots/order_page.png" alt="Orders Page" width="300px">
- **Profile Page**  
  <img src="screenshots/profile_page.png" alt="Profile Page" width="300px">
- **Add Address**  
  <img src="screenshots/add_address.png" alt="Add Address" width="300px">
- **View Addresses**  
  <img src="screenshots/view_address.png" alt="View Addresses" width="300px">
- **Logout (Redirect to Intro Screen)**  
  🎥 <img src="screenshots/logout.gif" alt="Logout GIF" width="300px">

---

## 🛠️ Setup & Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/Sougata1st/Amazon.git
   ```
2. Navigate to the project folder:
   ```sh
   cd your-repo
   ```
3. Run the backend API caller:
   ```sh
   java ConcurrentApiCaller
   ```
4. Build and run the app on **Android Studio** or your preferred IDE.

---

## 📜 License
This project is **open-source** and available under the **MIT License**.
