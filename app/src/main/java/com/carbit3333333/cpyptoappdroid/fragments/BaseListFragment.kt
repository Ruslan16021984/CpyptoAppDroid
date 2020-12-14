package com.carbit3333333.cpyptoappdroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carbit3333333.cpyptoappdroid.adapter.BaseAdapter
import kotlinx.android.synthetic.main.fragment_currencies_list.*

abstract class BaseListFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    protected lateinit var viewAdapter: BaseAdapter<*>
    private lateinit var viewMAnager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewMAnager = LinearLayoutManager(context)
        viewAdapter = createAdapterInstance()
        recyclerView = list.apply {
            setHasFixedSize(true)
            layoutManager = viewMAnager
            adapter = viewAdapter
        }
    }
    abstract fun createAdapterInstance(): BaseAdapter<*>
}