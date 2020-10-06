package com.example.sample_pbl

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.internal.Objects
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    lateinit var email_login:EditText
    lateinit var pass_login:EditText
    lateinit var login_btn:Button
    lateinit var register_intent:TextView
    lateinit var token:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email_login=findViewById(R.id.et_email_login)
        pass_login=findViewById(R.id.et_pasword_login)
        login_btn=findViewById(R.id.btn_login)
        register_intent=findViewById(R.id.tv_create_ac)

        token=getSharedPreferences("username",Context.MODE_PRIVATE)

        if (token.getString("loginusername","") != ""){
            val intent76=Intent(this, SignoutActivity::class.java)
            startActivity(intent76)
            finish()
        }



        register_intent.setOnClickListener {
            val intent3=Intent(this,RegisterActivity::class.java)
            startActivity(intent3)
            finish()

        }

        login_btn.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val add_email=email_login.text.trim()
        val  password_login=pass_login.text.trim()

        if (add_email.isEmpty() || password_login.isEmpty()){
            Toast.makeText(this,"Fill all details for login...!!!",Toast.LENGTH_SHORT).show()
        }
        else{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(add_email.toString(),password_login.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this,"Logged In succesfully",Toast.LENGTH_SHORT).show()
                        val intent4=Intent(this, SignoutActivity::class.java)
                            .putExtra("username",add_email.toString())
                        val editor=token.edit()
                        editor.putString("loginusername",add_email.toString())
                        editor.apply()
                        startActivity(intent4)
                        finish()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(this,"Failed To login....",Toast.LENGTH_SHORT).show()
                }
        }


    }
}
