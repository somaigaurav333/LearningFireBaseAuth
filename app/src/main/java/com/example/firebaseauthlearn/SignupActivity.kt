package com.example.firebaseauthlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.firebaseauthlearn.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signupbtn.setOnClickListener{
            val email = binding.signupmail.text.toString()
            val password = binding.signuppassword.text.toString()
            val confirmpass = binding.confirmsignuppass.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                if(password!=confirmpass){
                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
                }else{
                    binding.signupbtn.isEnabled = false
                    binding.progressBar.visibility= View.VISIBLE
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }

                        binding.signupmail.text.clear()
                        binding.signuppassword.text.clear()
                        binding.confirmsignuppass.text.clear()
                        binding.progressBar.visibility= View.GONE
                        binding.signupbtn.isEnabled = true
                    }


                }
            }else{
                Toast.makeText(this, "Empty field", Toast.LENGTH_SHORT).show()
            }
        }



    }
}