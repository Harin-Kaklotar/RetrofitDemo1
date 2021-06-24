package com.harin.retrofitdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.harin.retrofitdemo1.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var etId: EditText
    private lateinit var btnGet: Button
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etId = findViewById(R.id.et_id)
        btnGet = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        progressBar = findViewById(R.id.progressBar)

        val repository = Repository();
        val viewFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewFactory).get(MainViewModel::class.java)

        btnGet.setOnClickListener {
            textView.text = ""
            var id = etId.text.toString()

            var params: HashMap<String, String> = HashMap()
            params["_sort"] = "id"
            params["_order"] = "desc"

            viewModel.getPost5( id.toInt(), params)
        }

        viewModel.post5Response.observe(this, Observer { it ->
            if (it.isSuccessful) {
                textView.text = "success : ${it.body()?.size}"
                it.body()?.forEach {
                    Log.d("response", "id : ${it.id}")
                    Log.d("response", "userId : ${it.userId}")
                    Log.d("response", "title : ${it.title}")
                    Log.d("response", "body : ${it.body}")
                    Log.d("response", "---------------------")
                }
            }else {
                textView.text = it.code().toString()
            }
        })

        viewModel.isShowProgress().observe(this, Observer {
            if(it){
                progressBar.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.GONE
            }
        })
    }

}