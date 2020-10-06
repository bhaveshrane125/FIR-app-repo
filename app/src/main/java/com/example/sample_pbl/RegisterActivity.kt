package com.example.sample_pbl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var emailadd:EditText
    lateinit var pass:EditText
    lateinit var register_btn:Button
    lateinit var login_intent:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailadd=findViewById(R.id.et_email_register)
        pass=findViewById(R.id.et_pasword_register)
        register_btn=findViewById(R.id.btn_register)
        login_intent=findViewById(R.id.tv_login_ac)




        register_btn.setOnClickListener {
            register()

        }

        login_intent.setOnClickListener {
            val intent2=Intent(this,LoginActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }

    private fun register() {
        val email_add=emailadd.text.trim()
        val password=pass.text.trim()
        if (email_add.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Fill all credentials...!!!",Toast.LENGTH_SHORT).show()
        }
        else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email_add.toString(),password.toString())
                .addOnCompleteListener {
                    if (!it.isSuccessful){
                        return@addOnCompleteListener
                    }
                    else{

                        Toast.makeText(this,"Registered Succsesfully",Toast.LENGTH_SHORT).show()
                        val intent1=Intent(this,LoginActivity::class.java)
                        startActivity(intent1)
                        finish()
                    }

                }
                .addOnFailureListener{
                    Toast.makeText(this,"failed to register",Toast.LENGTH_SHORT).show()
                }
        }




    }
}
