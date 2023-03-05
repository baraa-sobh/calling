package com.example.mybaraas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybaraas.Model.User
import com.example.mybaraas.databinding.ActivityShowDataBinding
import com.example.mybaraas.databinding.CardBinding
import com.example.mybaraas.databinding.UserItemBinding
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ShowData : AppCompatActivity() {
    lateinit var binding: ActivityShowDataBinding
     lateinit  var db: FirebaseFirestore
    var adapter: FirestoreRecyclerAdapter<User, UserViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
       gitData()
        binding.btnImg.setOnClickListener {
            var i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }

    private fun gitData() {
        db = Firebase.firestore
        Log.e("bar", "Error adding document = 1")

        val query = db.collection("contacts")
        Log.e("bar", "Error adding document = 2")

        val option = FirestoreRecyclerOptions.Builder<User>().setQuery(query, User::class.java).build()
        Log.e("bar", "Error adding document = 3")

        adapter = object :FirestoreRecyclerAdapter<User, UserViewHolder>(option){

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

                return UserViewHolder(CardBinding.inflate(layoutInflater))
            }

            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: User) {
                holder.name.text = model.name
                holder.email.text = model.address
                holder.number.text = model.number
            }


        }
        Log.e("bar", "Error adding document = 4")

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }


    class UserViewHolder( binding: CardBinding) : RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvNameGet
        var email = binding.tvEmailGet
        var number = binding.tvNumberGet

    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }
}