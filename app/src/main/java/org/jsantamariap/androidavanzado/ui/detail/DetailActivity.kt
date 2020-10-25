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
    private var isLocalPod = true

    //! lifecycle functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        init()
        listeners()
    }

    private fun init() {

        intent?.let {
            if (it.getStringExtra(Common.ORIGEN_APOD) == Common.ORIGIN_APOD_LOCAL) {
                btnDetail.text = "DELETE"
                isLocalPod = true

                apodResponse = intent.extras!!.getSerializable(Common.KEY_APOD) as ApodResponse?
                apodResponse?.let { response ->
                    setDataToView(response)
                }

            } else {
                btnDetail.text = "SAVE"
                isLocalPod = false

                getDataFromServer()
            }
        } ?: print("hola")
    }

    private fun getDataFromServer() {
        viewModel.getServerApod(object : ApodService.CallbackResponse<ApodResponse> {
            override fun onResponse(response: ApodResponse) {
                apodResponse = response
                setDataToView(response)
            }

            override fun onFailure(t: Throwable, response: Response<*>?) {
                textDetail.text = response.toString()
            }
        })
    }

    private fun setDataToView(data: ApodResponse) {
        textDetail.text = data.explanation

        Glide.with(this@DetailActivity)
            .load(data.url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
            )
            .into(imageDetail)
    }

    private fun listeners() {
        btnDetail.setOnClickListener {

            if (isLocalPod) {
                viewModel.deleteApod(apodResponse!!)
            } else {
                viewModel.insertApod(apodResponse!!)
            }

            finish()
        }
    }
}