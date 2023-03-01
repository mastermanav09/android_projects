package com.example.first_project.models

import android.os.Parcel
import android.os.Parcelable


data class ProductItem(
    val img: Int,
    val id: Int,
    val name: String?,
    val category: String?,
    val price: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(img)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(category)
        parcel.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductItem> {
        override fun createFromParcel(parcel: Parcel): ProductItem {
            return ProductItem(parcel)
        }

        override fun newArray(size: Int): Array<ProductItem?> {
            return arrayOfNulls(size)
        }
    }
}

//):Parcelable {
//    constructor(parcel: ProductItem) : this(
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readInt()
//    ) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(img)
//        parcel.writeInt(id)
//        parcel.writeString(name)
//        parcel.writeString(category)
//        parcel.writeInt(price)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<ProductItem> {
//        override fun createFromParcel(parcel: Parcel): ProductItem {
//            return ProductItem(parcel)
//        }
//
//        override fun newArray(size: Int): Array<ProductItem?> {
//            return arrayOfNulls(size)
//        }
//    }
//}