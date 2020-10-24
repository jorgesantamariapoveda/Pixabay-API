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

    private var apodResponse: ApodResponse? = null

    //! lifecycle functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        listeners()

        /*
        viewModel.getApod(
            object : ApodService.CallbackResponse<ApodResponse> {
                override fun onResponse(response: ApodResponse) {

                    //! Revisar porque lo tengo duplicado cuando leo los
                    //extras del intent
                    apodResponse = response

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
                    textDetail.text = response.toString()
                }
            }
        )

         */
    }

    private fun init() {
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        intent?.let {
            if (it.getStringExtra(Common.ORIGEN_APOD) == Common.ORIGIN_APOD_LOCAL) {
                apodResponse = intent.extras!!.getSerializable(Common.KEY_APOD) as ApodResponse?

                Glide.with(this@DetailActivity)
                    .load(apodResponse!!.url)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_launcher_background)
                    )
                    .into(imageDetail)

            } else {
                //! Aquí hay que obtener los datos mediante petición a la NASA
                //para la foto del día
                print("kaka")
            }
        }
    }

    private fun listeners() {
        saveButtonDetail.setOnClickListener {
            viewModel.insertApod(apodResponse!!)
            finish()
        }
    }
}