package com.example.sample_pbl

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class SignoutActivity : AppCompatActivity() {
    lateinit var signout:Button
    lateinit var fileagain:Button

    lateinit var token:SharedPreferences

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signout)

        fileagain=findViewById(R.id.bt_file_report_signout)
        signout=findViewById(R.id.bt_signout)


        token=getSharedPreferences("username",Context.MODE_PRIVATE)





        fileagain.setOnClickListener {
            val intent_fileagain=Intent(this,MainActivity::class.java)
            startActivity(intent_fileagain)
        }

        signout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent_finish=Intent(this,LoginActivity::class.java)
            var editor=token.edit()
            editor.putString("loginusername","")
            editor.commit()
            startActivity(intent_finish)
            finish()
        }


    }
}
