package org.jsantamariap.androidavanzado.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jsantamariap.androidavanzado.R
import org.jsantamariap.androidavanzado.ui.detail.DetailActivity
import org.jsantamariap.androidavanzado.utils.Common

class MainActivity : AppCompatActivity() {

    // MARK: - Lifecycle functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, MainFragment.newInstance())
                .commit()

        floatingActionButton.setOnClickListener {
//            Forma vista hasta ahora para llamar a una activity desde otra activity
//            val intent2 = Intent(this, DetailActivity::class.java)
//            startActivity(intent2)
//            intent2.putExtra("KEY", "value")

//            Forma vista en avanzada, queda más limpio, además de poder hacer más cosas
            Intent(this, DetailActivity::class.java).apply {
                putExtra(Common.ORIGEN_PIXABAY, Common.ORIGIN_PIXABAY_SERVER)
                startActivity(this)
            }
        }

    }
}