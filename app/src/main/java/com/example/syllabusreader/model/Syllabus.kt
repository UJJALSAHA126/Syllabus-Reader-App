package com.example.syllabusreader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Syllabus(
    val title: String,
    val body: String
) : Parcelable
