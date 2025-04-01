# E-Commerce App (Amazon Clone)

## Disclaimer
The backend of this project is hosted on a free server using Render. Before running the application, download and execute the following file in the terminal:

[Download ConcurrentApiCaller](https://drive.google.com/file/d/1NR6pQlaEMbLLXzMvfd08MzsvZu6w5Q4D/view?usp=share_link)

```sh
java ConcurrentApiCaller
```
Once the response is OK, you can proceed with using the app.

## Features

### Caching & Single Source of Truth
- The application follows a **Single Source of Truth** principle, meaning that all data is first stored in the local database before being displayed in the UI.
- **Caching Mechanism**: The app caches data locally to ensure fast and seamless access, reducing redundant API calls.
- This approach enhances performance, provides offline support, and ensures consistency between the UI and backend.

### Authentication
- **Login Page**: User authentication with Bearer Token.
- **Registration Page**: New users can register.
- **Forgot Password**: Users receive an OTP via email to reset their password.

### Shopping
- **Home Screen**: Displays products with an option to add to the cart.
- **Filtering**: Filter products based on various criteria.
- **Cart Screen**: View added products and total price.
- **Checkout**: Enter address, email, and mobile number before proceeding to payment.
- **Payment**: Razorpay integration for UPI & Card payments.

### Orders & Profile
- **Order Page**: View all past orders.
- **Profile Page**: Add/view addresses and logout.

## Screenshots

### Authentication Screens
- **Intro Screen**  
  <img src="screenshots/intro_screen.png" width="300">
- **Login Screen**  
  <img src="screenshots/login_screen.png" width="300">
  <img src="screenshots/login_screen_2.png" width="300">
- **Registration Screen**  
  <img src="screenshots/registration_screen_1.png" width="300">
  <img src="screenshots/registration_screen_2.png" width="300">
- **Forgot Password - OTP Verification**  
  <img src="screenshots/otp_screen_1.png" width="300">
  <img src="screenshots/otp_screen_2.png" width="300">
  <img src="screenshots/otp_mail.png" width="300">
- **Change Password**  
  <img src="screenshots/change_password_1.png" width="300">

### Shopping Screens
- **Home Screen**  
  <img src="screenshots/home_screen.png" width="300">
- **Home Screen - GIF (Auto Play)**  
  <img src="screenshots/home_screen.gif" width="300">
- **Filter Screen**  
  <img src="screenshots/filter_1.png" width="300">
  <img src="screenshots/filter_2.png" width="300">
  <img src="screenshots/filter_3.png" width="300">
  <img src="screenshots/filter_4.png" width="300">
- **Filter Result Screen**  
  <img src="screenshots/filter_result.png" width="300">
- **Filter Screen - GIF (Auto Play)**  
  <img src="screenshots/filter_screen.gif" width="300">

### Cart & Checkout Screens
- **Cart Screen**  
  <img src="screenshots/cart_screen.png" width="300">
- **Checkout (Bottom Sheet for Address & Contact)**  
  <img src="screenshots/checkout_screen.png" width="300">
- **Payment (Razorpay UPI & Card Demo)**  
  <img src="screenshots/payment.gif" width="300">

### Orders & Profile Screens
- **Orders Page**  
  <img src="screenshots/order_page.png" width="300">
- **Profile Page**  
  <img src="screenshots/profile_page.png" width="300">
- **Add Address**  
  <img src="screenshots/add_address.png" width="300">
- **View Addresses**  
  <img src="screenshots/view_address.png" width="300">
- **Logout (Redirect to Intro Screen)**  
  <img src="screenshots/logout.gif" width="300">

## Setup & Installation
1. Clone the repository:
```sh
git clone https://github.com/your-username/your-repo.git
```
2. Navigate to the project folder:
```sh
cd your-repo
```
3. Run the backend API caller:
```sh
java ConcurrentApiCaller
```
4. Build and run the app on Android Studio or your preferred IDE.

## License
This project is open-source and available under the MIT License.
