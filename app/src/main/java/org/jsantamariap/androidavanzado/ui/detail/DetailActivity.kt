package org.jsantamariap.androidavanzado.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*
import org.jsantamariap.androidavanzado.R
import org.jsantamariap.androidavanzado.repository.model.ApodResponse
import org.jsantamariap.androidavanzado.repository.network.ApodService
import org.jsantamariap.androidavanzado.utils.Common
import org.jsantamariap.androidavanzado.utils.CustomViewModelFactory
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    private var mApodResponse: ApodResponse? = null

    //! lifecycle functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel.getApod(
            object : ApodService.CallbackResponse<ApodResponse> {
                override fun onResponse(response: ApodResponse) {

                    mApodResponse = response

                    /*
                    if (response.size > 0) {
                        textDetail.text = response[0].description
                    } else {
                        textDetail.text = "Lista vacía"
                    }
                     */

                    textDetail.text = response.explanation

                    Glide.with(this@DetailActivity)
                        .load(response.url)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.ic_launcher_background)
                        )
                        .into(imageDetail)

                }

                override fun onFailure(t: Throwable, response: Response<*>?) {
                }
            }
        )

        saveButtonDetail.setOnClickListener {
            viewModel.insertApod(mApodResponse!!)
        }
    }

}