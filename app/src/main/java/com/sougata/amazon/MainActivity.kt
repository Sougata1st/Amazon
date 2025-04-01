package com.sougata.amazon

import android.annotation.SuppressLint
import android.net.http.HttpException
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import com.sougata.amazon.networking.PaymentRequest
import com.sougata.amazon.networking.RetrofitClient
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.components.AmazonGradient
import com.sougata.shopping.database.dao.ProductDao
import com.sougata.shopping.domain.repository.ShopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity(), PaymentResultWithDataListener {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var productDao: ProductDao
    private lateinit var repository: ShopRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Checkout.preload(applicationContext)
        Checkout().setKeyID("rzp_test_KKsSpLmyNmWyvn")
        productDao = inject<ProductDao>().value
        repository = inject<ShopRepository>().value

        setContent {
            AmazonTheme {
                AmazonGradient {
                    val navHostController = rememberNavController()
                    val isLoggedIn = viewModel.authState.isLoggedIn
                    val isCheckingAuth = viewModel.authState.isCheckingAuth
                    if (!isCheckingAuth){
                        NavigationRoot(navHostController, isLoggedIn)
                    }

                }
            }
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?, data: PaymentData?) {
        val orderId = data?.orderId ?: "N/A"
        val txnId = razorpayPaymentId ?: "N/A"
        val signature = data?.signature ?: "N/A"


        val request = PaymentRequest(
            orderId = orderId,
            txnId = txnId,
            signature = signature
        )

        lifecycleScope.launch {
            productDao.clearCart()
        }
        lifecycleScope.launch {
            delay(500)
            repository.fetchAllCarts()
        }
        lifecycleScope.launch {
            repository.getAllOrders()
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.paymentApiService.processPayment(request)

                if (response.isSuccessful) {
                    repository.clearCart()
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "Payment Processed Successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "Payment Failed: ${response.errorBody()?.string()}",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("sougata", "Payment Failed: ${response.errorBody()?.string()}")
                    }
                }
            } catch (@SuppressLint("NewApi") e: HttpException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Unexpected Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun onPaymentError(p0: Int, response: String?, p2: PaymentData?) {
        Toast.makeText(this, "Payment Failed: $response", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            withContext(Dispatchers.IO+ NonCancellable){
                repository.clearCart()
            }

            withContext(Dispatchers.IO+ NonCancellable){
                productDao.deleteAllProducts()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            withContext(Dispatchers.IO + NonCancellable) {
                repository.fetchAllProducts(0, 10)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.launch {
            withContext(Dispatchers.IO+ NonCancellable){
                productDao.clearCart()
            }
        }
    }

}

