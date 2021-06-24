package com.harin.retrofitdemo1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harin.retrofitdemo1.R
import com.harin.retrofitdemo1.model.Post

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var mPost: List<Post> = emptyList()

    class MyViewHolder : RecyclerView.ViewHolder {
        var userId: TextView
        var id: TextView
        var title: TextView
        var body: TextView
        constructor(view: View) : super(view){
            userId = view.findViewById(R.id.lbl_userId)
            id = view.findViewById(R.id.lbl_id)
            title = view.findViewById(R.id.lbl_title)
            body = view.findViewById(R.id.lbl_body)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.userId.text = mPost[position].userId.toString()
        holder.id.text = mPost[position].id.toString()
        holder.title.text = mPost[position].title
        holder.body.text = mPost[position].body
    }

    override fun getItemCount(): Int {
        return mPost.size
    }

    fun setItems(items: List<Post>){
        mPost = items
        notifyDataSetChanged()
    }
}