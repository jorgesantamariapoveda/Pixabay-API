package org.jsantamariap.androidavanzado.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GhibliResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("director")
	val director: String? = null,

	@field:SerializedName("producer")
	val producer: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("rt_score")
	val rtScore: String? = null,

	@field:SerializedName("people")
	val people: List<String?>? = null,

	@field:SerializedName("species")
	val species: List<String?>? = null,

	@field:SerializedName("locations")
	val locations: List<String?>? = null,

	@field:SerializedName("vehicles")
	val vehicles: List<String?>? = null,

	@field:SerializedName("url")
	val url: String? = null

) : Parcelable
