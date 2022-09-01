package com.example.movieapp.ui.activities.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.movieapp.databinding.ActivitySignUpBinding
import com.example.movieapp.viewmodels.auth.AuthViewModel

class SignUpActivity : AppCompatActivity() {


    private lateinit var binding : ActivitySignUpBinding
    private val authViewModel : AuthViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)




        var email = binding.email.text
        var password = binding.password.text
        var confirmPassword = binding.confirmPassword.text



        binding.btnSignUp.setOnClickListener{
            authViewModel.signUp(email = email.toString(),
                password = password.toString(),
                confirmPassword = confirmPassword.toString(),
                view = it,
                activity = this@SignUpActivity
                )
        }




    }
}