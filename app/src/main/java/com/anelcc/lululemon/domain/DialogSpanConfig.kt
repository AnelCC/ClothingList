package com.anelcc.lululemon.domain

import android.os.Parcel
import android.os.Parcelable

const val DIALOG_DATA = "dialog_option_data"
data class DialogSpanConfig(
    val onPositiveClick: (position: String) -> Unit = { }
) : Parcelable {

    constructor(parcel: Parcel) : this() {}

    override fun describeContents(): Int = 0

    override fun writeToParcel(p0: Parcel?, p1: Int) {

    }

    companion object CREATOR : Parcelable.Creator<DialogSpanConfig> {
        override fun createFromParcel(parcel: Parcel): DialogSpanConfig {
            return DialogSpanConfig(parcel)
        }

        override fun newArray(size: Int): Array<DialogSpanConfig?> {
            return arrayOfNulls(size)
        }
    }
}