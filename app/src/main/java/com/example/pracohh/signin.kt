package com.example.pracohh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signin : AppCompatActivity() {
    lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val userN = findViewById<TextInputEditText>(R.id.etusername)
        val psswd = findViewById<TextInputEditText>(R.id.passwd)
        val btn = findViewById<Button>(R.id.signinbtn)
        btn.setOnClickListener {
            val search = userN.text.toString()
            val pswd = psswd.text.toString()
            if (search.isNotEmpty()) {
                read(search, pswd)
            } else {
                Toast.makeText(this, "enter the details first", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun read(search: String, pswd: String) {
        db = FirebaseDatabase.getInstance().getReference("Users")
        db.child(search).get().addOnSuccessListener {
            if (it.exists()) {
                val password = it.child("passwd").value
                if (password == pswd) {
                    startActivity(Intent(applicationContext, HomePage::class.java))
                }

            } else {
                Toast.makeText(this, "User dooes not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "failed to connect to db", Toast.LENGTH_SHORT).show()
        }

    }
}
