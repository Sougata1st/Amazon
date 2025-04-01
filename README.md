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
  ![Intro Screen](screenshots/intro_screen.png)
- **Login Screen**
  ![Login Screen 1](screenshots/login_screen.png)
  ![Login Screen 2](screenshots/login_screen_2.png)
- **Registration Screen**
  ![Registration Screen 1](screenshots/registration_screen_1.png)
  ![Registration Screen 2](screenshots/registration_screen_2.png)
- **Forgot Password - OTP Verification**
  ![OTP Screen 1](screenshots/otp_screen_1.png)
  ![OTP Screen 2](screenshots/otp_screen_2.png)
  ![OTP Mail Screenshot](screenshots/otp_mail.png)
- **Change Password**
  ![Change Password 1](screenshots/change_password_1.png)

### Shopping Screens
- **Home Screen**
  ![Home Screen](screenshots/home_screen.png)
- **Home Screen - GIF (Auto Play)**
  ![Home GIF](screenshots/home_screen.gif)
- **Filter Screen**
  ![Filter 1](screenshots/filter_1.png)
  ![Filter 2](screenshots/filter_2.png)
  ![Filter 3](screenshots/filter_3.png)
  ![Filter 4](screenshots/filter_4.png)
- **Filter Result Screen**
  ![Filter Result](screenshots/filter_result.png)
- **Filter Screen - GIF (Auto Play)**
  ![Filter GIF](screenshots/filter_screen.gif)

### Cart & Checkout Screens
- **Cart Screen**
  ![Cart Screen](screenshots/cart_screen.png)
- **Checkout (Bottom Sheet for Address & Contact)**
  ![Checkout Screen](screenshots/checkout_screen.png)
- **Payment (Razorpay UPI & Card Demo)**
  ![Payment GIF](screenshots/payment.gif)

### Orders & Profile Screens
- **Orders Page**
  ![Orders Page](screenshots/order_page.png)
- **Profile Page**
  ![Profile Page](screenshots/profile_page.png)
- **Add Address**
  ![Add Address 1](screenshots/add_address.png)

- **View Addresses**
  ![View Addresses](screenshots/view_address.png)
- **Logout (Redirect to Intro Screen)**
  ![Logout](screenshots/logout.gif)

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
