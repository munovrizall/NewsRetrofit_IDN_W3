package com.bootcamp.tugas3_bootcampidn

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bootcamp.tugas3_bootcampidn.databinding.ActivityDetailNewsBinding
import com.bootcamp.tugas3_bootcampidn.model.ArticlesItem
import com.bumptech.glide.Glide

class DetailNewsActivity : AppCompatActivity() {

	lateinit var binding: ActivityDetailNewsBinding

	companion object {
		const val EXTRA_NEWS = "news"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailNewsBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val dataNews = intent.getParcelableExtra<ArticlesItem>(EXTRA_NEWS)!!

		binding.apply {
			Glide.with(this@DetailNewsActivity)
				.load(dataNews?.urlToImage)
				.error(R.drawable.ic_launcher_foreground)
				.into(imgNews)
			tvJudul.text = dataNews.title
			tvDeskripsi.text = dataNews.description
			btnGoUrl.setOnClickListener() {
				openNews(dataNews.url.toString())
			}
			btnShare.setOnClickListener() {
				shareNews(dataNews.url.toString())
			}
		}
	}

	private fun openNews(newsLink: String) {
		val intent = Intent(Intent.ACTION_VIEW)
		intent.data = Uri.parse(newsLink)
		startActivity(intent)
	}
	private fun shareNews(newsLink: String) {
		val intent = Intent()
		intent.apply {
			action = Intent.ACTION_SEND
			putExtra(Intent.EXTRA_TEXT, newsLink)
			type = "text/plain"
		}

		val shareIntent = Intent.createChooser(intent, "Bagikan Berita")
		startActivity(shareIntent)
	}
}