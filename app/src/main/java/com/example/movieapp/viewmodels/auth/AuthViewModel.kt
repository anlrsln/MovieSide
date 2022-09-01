package com.example.movieapp.viewmodels.auth

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.movieapp.ui.activities.MainActivity
import com.example.movieapp.ui.activities.login.LoginActivity
import com.example.movieapp.ui.activities.login.SignUpActivity
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private var auth : FirebaseAuth = FirebaseAuth.getInstance()



    fun signIn(email:String, password:String,activity: Activity){
        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                }else{
                    Toast.makeText(activity,it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(activity,"Empty Fields Are Not Allowed !", Toast.LENGTH_LONG).show()
        }
    }



    fun passToSignUp(activity: Activity){
        val intent = Intent(activity,SignUpActivity::class.java)
        activity.startActivity(intent)
    }



    fun checkCurrentUser(activity: Activity){
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }



    fun signUp(email:String, password:String, confirmPassword:String, view: View, activity: Activity){
        if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
            if(password==confirmPassword){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(activity, LoginActivity::class.java)
                        activity.startActivity(intent)
                        activity.finish()
                    }else{
                        Toast.makeText(activity,it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }else
                Toast.makeText(activity,"Passwords Are Not Matching !", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(activity,"Empty Fields Are Not Allowed !", Toast.LENGTH_LONG).show()
        }
    }



}