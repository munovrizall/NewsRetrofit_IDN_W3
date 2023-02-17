package com.bootcamp.tugas3_bootcampidn.adapter

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.tugas3_bootcampidn.DetailNewsActivity
import com.bootcamp.tugas3_bootcampidn.R
import com.bootcamp.tugas3_bootcampidn.databinding.ItemRowNewsBinding
import com.bootcamp.tugas3_bootcampidn.model.ArticlesItem
import com.bumptech.glide.Glide
import java.text.ParseException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NewsAdapter() :

    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var dataNews: List<ArticlesItem> = listOf()

    inner class NewsViewHolder(private val binding: ItemRowNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: ArticlesItem) {
            binding.apply {

                Glide.with(imgNews)
                    .load(news.urlToImage)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imgNews)

                tvJudul.text = news.title
                tvPenulis.text = news.author


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    tvTanggalPosting.text = formatDate(news.publishedAt.toString())
                } else {
                    tvTanggalPosting.text = news.publishedAt
                }

                binding.cardNews.setOnClickListener {
                    val intent = Intent(itemView.context, DetailNewsActivity::class.java)
                    intent.putExtra(DetailNewsActivity.EXTRA_NEWS, news)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowBinding = ItemRowNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(rowBinding)
    }

    override fun getItemCount(): Int {
        return dataNews.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        return holder.bind(dataNews[position])
    }

    fun setData(data: List<ArticlesItem>) {
        dataNews = data
        notifyDataSetChanged()
    }


    fun formatDate(inputDate: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

        val date = LocalDateTime.parse(inputDate, inputFormatter)
        return outputFormatter.format(date)
    }

}