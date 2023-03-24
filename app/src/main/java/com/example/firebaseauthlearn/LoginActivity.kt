package com.example.firebaseauthlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.firebaseauthlearn.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener{

            val email = binding.signinemail.text.toString()
            val pass = binding.signinpassword.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty()){
                binding.progressBarsignin.visibility = View.VISIBLE
                binding.login.isEnabled = false
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.signinemail.text.clear()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }

                    binding.signinpassword.text.clear()
                    binding.login.isEnabled = true
                    binding.progressBarsignin.visibility = View.GONE
                }
            }else{
                Toast.makeText(this, "Empty fields", Toast.LENGTH_SHORT).show()
            }
        }


    }
}