package org.jsantamariap.androidavanzado.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jsantamariap.androidavanzado.R

class MainFragment : Fragment() {

    // son propiedades y métodos estáticos
    companion object {
        const val TAG = "MainFragment" // nos vendrá bien para poder referenciar al fragment
        fun newInstance (): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}