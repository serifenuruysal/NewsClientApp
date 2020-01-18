package com.androidapp.thenews.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by S.Nur Uysal on 2019-11-03.
 */
@Parcelize
data class Category(val imageResource: Int, val categoryName: String) : Parcelable