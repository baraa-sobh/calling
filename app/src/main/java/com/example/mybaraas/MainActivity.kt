package com.example.mybaraas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mybaraas.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val name = binding.tvName.text.toString()
        val number = binding.tvNumber.text.toString()
        val address = binding.tvEmail.text.toString()
        var  db = Firebase.firestore

        binding.btnSave.setOnClickListener {


            val contact = hashMapOf(
                "name" to name,
                "number" to number,
                "address" to address,
            )
            db.collection("contacts")
                .add(contact)
                .addOnSuccessListener { documentReference ->
                    Log.e("bar", "DocumentSnapshot added with ID: ${documentReference.id}")

                }.addOnFailureListener { e ->
                    Log.e("bar", "Error adding document", e)
                }

    }

        binding.show.setOnClickListener {
            var i = Intent(this,ShowData::class.java)
            startActivity(i)
        }
}
}