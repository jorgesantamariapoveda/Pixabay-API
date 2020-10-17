package org.jsantamariap.androidavanzado.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jsantamariap.androidavanzado.R

class DetailActivity: AppCompatActivity() {

    //! lifecycle functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))


    }

}