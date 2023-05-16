package com.example.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView     //declare
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://rapidapi.com/Glavier/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<Mydata?> {
            override fun onResponse(call: Call<Mydata?>, response: Response<Mydata?>) {
                //if api call is sucessfull,then use the data of api and show in your app

                val responeBody = response.body()
                val productList = responeBody?.products!!  //!! tells if




                //create textview,recycler view and etc
                myAdapter = MyAdapter(this@MainActivity, productList)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                myAdapter.setOnItemClickListener(object:MyAdapter.onItemClickListener{
                    override fun onItemClicking(position:Int){
                        val intent = Intent(this@MainActivity,image::class.java)

                        startActivity(intent)



                    }
                })

            }


            //  if any issue the below message will be displayed as default to the user
            override fun onFailure(call: Call<Mydata?>, t: Throwable) {
                //if api fails
                Log.d("MainActivity", "onFailure:" + t.message)
            }

        })

    }
}