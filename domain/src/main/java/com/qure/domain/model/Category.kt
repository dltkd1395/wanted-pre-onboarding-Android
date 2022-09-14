package com.qure.domain.model

import com.qure.domain.R

enum class Category(val img: Int, val category : String) {

    Business(R.drawable.ic_businessplan,"business"),
    Entertainment(R.drawable.ic_music,"entertainment"),
    Genernal(R.drawable.ic_people,"genernal"),
    Health(R.drawable.ic_health,"health"),
    Science(R.drawable.ic_science,"science"),
    Sports(R.drawable.ic_sports,"sports"),
    Technology(R.drawable.ic_industrial,"technology")

}