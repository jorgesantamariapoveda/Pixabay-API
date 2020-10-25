package org.jsantamariap.androidavanzado.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import org.jsantamariap.androidavanzado.R
import org.jsantamariap.androidavanzado.repository.model.ItemPixabay
import org.jsantamariap.androidavanzado.ui.detail.DetailActivity
import org.jsantamariap.androidavanzado.utils.Common
import org.jsantamariap.androidavanzado.utils.CustomViewModelFactory

class MainFragment : Fragment(), CallbackItemClick {

    // MARK: - statics

    companion object {

        //const val TAG = "MainFragment" // nos vendrá bien para poder referenciar al fragment

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    // MARK: - Properties

    private var adapter: MainAdapter? = null

    private val viewModel: MainFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(activity!!.application)
        ViewModelProvider(this, factory).get(MainFragmentViewModel::class.java)
    }

    // MARK: - Lifecycle functions

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

    // MARK: - Private functions

    private fun init() {
        recyclerViewMain.layoutManager = LinearLayoutManager(activity)
        recyclerViewMain.isNestedScrollingEnabled = false
        recyclerViewMain.setHasFixedSize(false)
    }

    private fun getLocalAllApod() {
        //! Como se hace la petición a la base de datos no debe hacerse en el mainQueue
        //liveData se encarga de ello y lo hace en background
        viewModel.getAllItemsPixabay().observe(viewLifecycleOwner, Observer { items ->
            adapter = MainAdapter(activity!!.applicationContext, this, items)
            recyclerViewMain.adapter = adapter
        })
    }

    // MARK: - Interface CallbackItemClick

    override fun onItemClickPixabay(itemPixabay: ItemPixabay) {
        //! Llamar a una activity desde un fragment
        this.activity?.let { fragment ->
            Intent(fragment, DetailActivity::class.java).apply {

                //! Forma 1 porque es serializable
                arguments = Bundle().apply {
                    putSerializable(Common.EXTRA_ITEM_PIXABAY, itemPixabay)
                    putExtras(this)
                }

                //! Forma 2, sino fuese serializable
                // putExtra(Common.KEY_APOD, apodResponse.id)

                putExtra(Common.ORIGEN_PIXABAY, Common.ORIGIN_PIXABAY_LOCAL)

                startActivity(this)
            }
        }
    }
}