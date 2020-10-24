package org.jsantamariap.androidavanzado.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import org.jsantamariap.androidavanzado.R
import org.jsantamariap.androidavanzado.repository.model.ApodResponse
import org.jsantamariap.androidavanzado.ui.detail.DetailActivity
import org.jsantamariap.androidavanzado.ui.detail.DetailViewModel
import org.jsantamariap.androidavanzado.utils.CustomViewModelFactory

class MainFragment : Fragment(), CallbackItemClick {

    //! static
    companion object {

        const val TAG = "MainFragment" // nos vendrá bien para poder referenciar al fragment

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    //! properties
    private var adapter: MainAdapter? = null

    private val viewModel: MainFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(activity!!.application)
        ViewModelProvider(this, factory).get(MainFragmentViewModel::class.java)
    }

    //! lifecycle functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        getLocalAllApod()
    }

    private fun init() {
        recyclerViewMain.layoutManager = LinearLayoutManager(activity)
        recyclerViewMain.isNestedScrollingEnabled = false
        recyclerViewMain.setHasFixedSize(false)
    }

    private fun getLocalAllApod() {
        //Como se hace la petición a la base de datos no debe hacerse en el mainQueue
        // para ello lo hace el liveData que lo hace en background
        viewModel.getLocalAllApod().observe(viewLifecycleOwner, Observer {
            adapter = MainAdapter(activity!!.applicationContext, this, it)
            recyclerViewMain.adapter = adapter
        })
    }

    //! Interface CallbackItemClick
    override fun onItemClick() {
        //! Llamar a una activity desde un fragment
        this.activity?.let {
            Intent(it, DetailActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}