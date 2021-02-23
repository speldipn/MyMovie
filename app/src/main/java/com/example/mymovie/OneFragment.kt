package com.example.mymovie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.fragment_one.*

class OneFragment : Fragment() {

    lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            activity = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
//        pager.adapter = MoviePagerAdapter()
//        pager.offscreenPageLimit = 1
        chipGroup.setOnCheckedChangeListener { _, checkedId ->
           when(checkedId) {
               R.id.aChip -> showToast("A Chip selected")
               R.id.bChip -> showToast("B Chip selected")
           }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    inner class MoviePagerAdapter: PagerAdapter() {
        override fun getCount(): Int {
            TODO("Not yet implemented")
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            TODO("Not yet implemented")
        }

    }

}