package org.jsantamariap.androidavanzado.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import org.jsantamariap.androidavanzado.R

class MainFragment : Fragment() {

    //! son propiedades y métodos estáticos de la clase MainFragment
    companion object {
        const val TAG = "MainFragment" // nos vendrá bien para poder referenciar al fragment
        fun newInstance (): MainFragment {
            return MainFragment()
        }
    }

    //! properties
    private var adapter: MainAdapter? = null
    private var listItems: ArrayList<String> = ArrayList()

    //! lifecycle functions
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listItems.add("")
        listItems.add("")
        listItems.add("")
        adapter = MainAdapter(activity!!.applicationContext, listItems)

        recyclerViewMain.layoutManager = LinearLayoutManager(activity)
        recyclerViewMain.isNestedScrollingEnabled = false
        recyclerViewMain.setHasFixedSize(false)
        recyclerViewMain.adapter = adapter
    }
}