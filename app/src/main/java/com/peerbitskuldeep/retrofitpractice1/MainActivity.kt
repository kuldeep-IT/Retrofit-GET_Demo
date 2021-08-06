package com.peerbitskuldeep.retrofitpractice1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.peerbitskuldeep.retrofitpractice1.adapter.RecAdapter
import com.peerbitskuldeep.retrofitpractice1.api.HomeData
import com.peerbitskuldeep.retrofitpractice1.constants.Constants.Companion.END_POINT
import com.peerbitskuldeep.retrofitpractice1.retrofit.NetworkCall
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recAdapter: RecAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofitData = NetworkCall.instance.getData(END_POINT)

        retrofitData.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {

                var responseBody = response.body()?.string()
                var jsonObject = JSONObject(responseBody)
                var list = Gson().fromJson(jsonObject.optJSONObject("data").optJSONArray("HomeData").toString(), Array<HomeData>::class.java).toList()

                recAdapter = RecAdapter(this@MainActivity, list as ArrayList<HomeData>)
                recAdapter.notifyDataSetChanged()
                recyclerView.adapter = recAdapter
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }
}