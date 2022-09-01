package com.example.movieapp.ui.activities.login



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.movieapp.databinding.ActivityLoginBinding
import com.example.movieapp.viewmodels.auth.AuthViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private  val authViewModel: AuthViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var email = binding.email.text
        var password = binding.password.text




        // Sign In buton
        binding.btnSignIn.setOnClickListener {
            authViewModel.signIn(email = email.toString(), password = password.toString(), activity = this@LoginActivity)
        }


        // Create new account buton
        binding.newAccountBtn.setOnClickListener {
            authViewModel.passToSignUp(this@LoginActivity)
        }


        // Current User Check

        authViewModel.checkCurrentUser(this@LoginActivity)

    }
}