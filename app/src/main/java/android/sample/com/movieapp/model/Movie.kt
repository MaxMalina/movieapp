package android.sample.com.movieapp.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("title")
    val title: String,

    @SerializedName("year")
    val year: Int,

    @SerializedName("genre")
    val genre: List<String>,

    @SerializedName("director")
    val director: String,

    @SerializedName("desription")
    val description: String,

    @SerializedName("image")
    val imageUrl: String

) : Parcelable {
    constructor(parcel: Parcel) : this (
        parcel.readString(),
        parcel.readInt(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(year)
        parcel.writeStringList(genre)
        parcel.writeString(director)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
