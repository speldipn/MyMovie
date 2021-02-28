package com.example.mymovie

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val TAG = "speldipn"

    private val url = "https://movie.naver.com/movie/running/current.nhn?order=reserve"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        requestButton.setOnClickListener {
            doTask()
        }
    }

    @SuppressLint("CheckResult")
    private fun doTask() {
        showData("Request url: $url")

        var itemList: ArrayList<MovieItem> = arrayListOf()
        var documentTitle = ""

        Single.fromCallable {
            try {
                val doc = Jsoup.connect(url).get()

                val elems: Elements = doc.select("ul.lst_detail_t1 li")
                run elemsLoop@{
                    elems.forEachIndexed { index, elem ->
                        val title = elem.select("dt.tit a").text()
                        val score1 = elem.select("dl.info_star div.star_t1 span.num").text()
                        val score2 = elem.select("span.num2").text()
                        val reserve = elem.select("dl.info_exp div.star_t1 span.num").text()

                        val item = MovieItem(title, score1, score2, reserve)
                        itemList.add(item)

                        if (index == 9) {
                            return@elemsLoop
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return@fromCallable documentTitle
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showData("onSuccess called.")
                showData(itemList.joinToString())
            }, {
                showData("onError called.")
                it.printStackTrace()
            })

        val testArray = arrayListOf<A>()
        testArray.add(A("neo", 30))
        testArray.add(A("noah", 35))
        testArray.add(A("jack", 22))
        debug(testArray.joinToString())

    }

    data class A(val name: String?, val age: Int?)


    private fun showData(msg: String) {
        contentTextView.append(msg)
    }

    private fun debug(msg: String) = Log.d(TAG, msg)
    private fun showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    data class MovieItem(
        val title: String?,
        val score1: String?,
        val score2: String?,
        val reserve: String?
    )

}

