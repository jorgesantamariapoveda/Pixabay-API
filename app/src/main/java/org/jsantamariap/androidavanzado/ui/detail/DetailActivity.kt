package org.jsantamariap.androidavanzado.ui.detail

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*
import org.jsantamariap.androidavanzado.R
import org.jsantamariap.androidavanzado.repository.model.ItemPixabay
import org.jsantamariap.androidavanzado.repository.model.PixabayResponse
import org.jsantamariap.androidavanzado.repository.network.PixabayService
import org.jsantamariap.androidavanzado.utils.Common
import org.jsantamariap.androidavanzado.utils.CustomViewModelFactory
import retrofit2.Response
import kotlin.random.Random

class DetailActivity : AppCompatActivity() {

    // MARK: - Properties

    private val viewModel: DetailViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    private var pixabayResponse: PixabayResponse? = null
    private var itemPixabay: ItemPixabay? = null
    private var isLocalPod = true

    // MARK: - Lifecycle functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        init()
        listeners()
    }

    // MARK: - Private functions

    private fun init() {
        //#pragma Si me da tiempo hacer editable la categoria para que la pueda escoger el usuario
        toolbar.subtitle = Common.CATEGORY_PIXABAY

        intent?.let {
            if (it.getStringExtra(Common.ORIGEN_PIXABAY) == Common.ORIGIN_PIXABAY_LOCAL) {
                btnActionDetail.text = getString(R.string.Delete)
                btnReintentDetail.visibility = View.INVISIBLE
                isLocalPod = true

                itemPixabay = intent.extras!!.getSerializable(Common.EXTRA_ITEM_PIXABAY) as ItemPixabay?

                setDataToView()

            } else {
                btnActionDetail.text = getString(R.string.Save)
                btnReintentDetail.visibility = View.VISIBLE
                isLocalPod = false

                getPixabayFromServer()
            }
        } ?: getPixabayFromServer()
    }

    private fun getPixabayFromServer() {
        viewModel.getServerPixabay(object : PixabayService.CallbackResponse<PixabayResponse> {
            override fun onResponse(response: PixabayResponse) {
                pixabayResponse = response
                setPixabayToView(response)
            }

            override fun onFailure(t: Throwable, response: Response<*>?) {
                print(t.message)
            }
        })
    }

    private fun setPixabayToView(data: PixabayResponse) {
        if (data.hits!!.isNotEmpty()) {
            val random = Random.Default
            val valor = random.nextInt(0, data.hits.size - 1)
            itemPixabay = data.hits[valor]
            setDataToView()
        }
    }

    private fun setDataToView() {
        Glide.with(this@DetailActivity)
            .load(itemPixabay!!.largeImageURL)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
            )
            .into(imageDetail)
    }

    private fun listeners() {

        btnActionDetail.setOnClickListener {
            if (isLocalPod) {
                viewModel.deleteItemPixabay(itemPixabay!!)
            } else {
                viewModel.insertItemPixabay(itemPixabay!!)
            }
            finish()
        }

        btnCancelDetail.setOnClickListener {
            finish()
        }

        btnReintentDetail.setOnClickListener {
            getPixabayFromServer()
        }


    }
}