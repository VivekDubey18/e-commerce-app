package com.example.e_commerceplatform

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById<RecyclerView>(R.id.recyclerView)


        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData= retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
              //when api call is success
                var p1Body=p1.body()
                val productArray=p1Body?.products!!

                myAdapter=MyAdapter(this@MainActivity, productArray)
                recyclerView.adapter= myAdapter //get data from my adapter in my adapter.kt
                recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
            //if failure of
                Log.d(TAG, "onFailure: " + p1.message)

            }
        })
    }
}