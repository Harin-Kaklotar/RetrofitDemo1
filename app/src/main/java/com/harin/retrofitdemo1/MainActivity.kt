package com.harin.retrofitdemo1

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harin.retrofitdemo1.adapter.MyAdapter
import com.harin.retrofitdemo1.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        initRecyclerView()

        val repository = Repository();
        val viewFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewFactory).get(MainViewModel::class.java)

        var params: HashMap<String, String> = HashMap()
        params["_sort"] = "id"
        params["_order"] = "desc"

        viewModel.getPost5(2, params)

        viewModel.post5Response.observe(this, Observer {
            if (it.isSuccessful) {
                it.body()?.let { it1 -> adapter.setItems(it1) }
            } else {
                Toast.makeText(this@MainActivity, it.code().toString(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.isShowProgress().observe(this, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun initRecyclerView() {
        adapter = MyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}