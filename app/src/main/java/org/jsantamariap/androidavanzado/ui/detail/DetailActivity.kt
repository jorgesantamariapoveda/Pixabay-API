package org.jsantamariap.androidavanzado.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_detail.*
import org.jsantamariap.androidavanzado.R
import org.jsantamariap.androidavanzado.repository.model.ApodResponse
import org.jsantamariap.androidavanzado.repository.network.ApodService
import org.jsantamariap.androidavanzado.utils.Common
import org.jsantamariap.androidavanzado.utils.CustomViewModelFactory
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by lazy {
        val factory = CustomViewModelFactory()
        ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    //! lifecycle functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel.getApod(Common.API_KEY, object : ApodService.CallbackResponse<ApodResponse> {
            override fun onResponse(response: ApodResponse) {
                textDetail.text = response.explanation
            }

            override fun onFailure(t: Throwable, response: Response<*>?) {
            }
        })
    }

}