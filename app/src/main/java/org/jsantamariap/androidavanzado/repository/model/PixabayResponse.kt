package org.jsantamariap.androidavanzado.repository.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*

//Aquí está integramente la respuesta de la API, pero únicamente me interesa almacenar en la base
//de datos una parte (aquella que le he puesto la etiqueta Entity)

@Parcelize
data class PixabayResponse(

	@field:SerializedName("hits")
	val hits: List<ItemPixabay?>? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("totalHits")
	val totalHits: Int? = null
) : Parcelable

@Entity(tableName = "pixabay_table")
data class ItemPixabay(

	@PrimaryKey
	var idPrimaryKey: String = UUID.randomUUID().toString(),

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("webformatHeight")
	val webformatHeight: Int? = null,

	@field:SerializedName("imageWidth")
	val imageWidth: Int? = null,

	@field:SerializedName("favorites")
	val favorites: Int? = null,

	@field:SerializedName("previewHeight")
	val previewHeight: Int? = null,

	@field:SerializedName("webformatURL")
	val webformatURL: String? = null,

	@field:SerializedName("userImageURL")
	val userImageURL: String? = null,

	@field:SerializedName("previewURL")
	val previewURL: String? = null,

	@field:SerializedName("comments")
	val comments: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("imageHeight")
	val imageHeight: Int? = null,

	@field:SerializedName("tags")
	val tags: String? = null,

	@field:SerializedName("previewWidth")
	val previewWidth: Int? = null,

	@field:SerializedName("downloads")
	val downloads: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("largeImageURL")
	val largeImageURL: String? = null,

	@field:SerializedName("pageURL")
	val pageURL: String? = null,

	@field:SerializedName("imageSize")
	val imageSize: Int? = null,

	@field:SerializedName("webformatWidth")
	val webformatWidth: Int? = null,

	@field:SerializedName("user")
	val user: String? = null,

	@field:SerializedName("views")
	val views: Int? = null,

	@field:SerializedName("likes")
	val likes: Int? = null
) : Serializable
