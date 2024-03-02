package com.example.pracohh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var db:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val a= findViewById<TextInputEditText>(R.id.etmail)
        val b=findViewById<TextInputEditText>(R.id.etname)
        val c=findViewById<TextInputEditText>(R.id.etpasswd)
        val d=findViewById<TextInputEditText>(R.id.etusername)
        val e= findViewById<Button>(R.id.btn)
        val f=findViewById<Button>(R.id.signin)
        f.setOnClickListener{

            Log.d("TAG","button to press hua h")
            startActivity(Intent(applicationContext,signin::class.java))
            finish()
        }
        e.setOnClickListener{
            val name = b.text.toString()
            val mail = a.text.toString()
            val passwd = c.text.toString()
            val username = d.text.toString()
            val user= users(name,mail,passwd,username)
            db=FirebaseDatabase.getInstance().getReference("Users")
            db.child(username).setValue(user).addOnSuccessListener {
                a.text?.clear()
                b.text?.clear()
                c.text?.clear()
                d.text?.clear()

                Toast.makeText(this, "data saved babe", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }


        }


    }
}