package org.jsantamariap.androidavanzado.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.jsantamariap.androidavanzado.R
import org.jsantamariap.androidavanzado.repository.model.ApodResponse
import org.jsantamariap.androidavanzado.repository.model.GhibliResponse
import org.jsantamariap.androidavanzado.repository.network.ApodService
import org.jsantamariap.androidavanzado.utils.Common
import org.jsantamariap.androidavanzado.utils.CustomViewModelFactory
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    private val mApodResponse: ApodResponse? = null

    //! lifecycle functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel.getApod(Common.API_KEY_NASA_APOD, object : ApodService.CallbackResponse<List<GhibliResponse>> {
            override fun onResponse(response: List<GhibliResponse>) {

                mApodResponse = response

                if (response.size > 0) {
                    textDetail.text = response[0].description
                } else {
                    textDetail.text = "Lista vacía"
                }
                //textDetail.text = response.description

                /*
                Glide.with(this@DetailActivity)
                    .load(response.url)
                    .into(imageDetail)

                 */
            }

            override fun onFailure(t: Throwable, response: Response<*>?) {
            }
        })

        buttonDetail.setOnClickListener {
            viewModel.insertApodToRoomDatabase(mApodResponse!!)
        }
    }

}