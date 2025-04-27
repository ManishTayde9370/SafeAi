package com.example.safeai

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Incident(
    val id: Int,
    val title: String,
    val description: String,
    val severity: String,
    val reported_at: String
) : Serializable, Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(severity)
        parcel.writeString(reported_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Incident> {
        override fun createFromParcel(parcel: Parcel): Incident {
            return Incident(parcel)
        }

        override fun newArray(size: Int): Array<Incident?> {
            return arrayOfNulls(size)
        }
    }
}
