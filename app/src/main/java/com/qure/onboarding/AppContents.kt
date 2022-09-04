package com.qure.onboarding

class AppContents {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val COUNTRY = "us"
        const val API_KEY = "69993a0a075146f78b24463c2ec09650"
        val CATEGORY = mapOf<String, String>("business" to "business", "entertainment" to "entertainment",
            "general" to "general", "health" to "health", "science" to "science", "sports" to "sports", "technology" to "technology")
        const val BUSINESS = "business"
        const val ENTERTAINMENT = "entertainment"
        const val GENERAL = "general"
        const val HEALTH = "health"
        const val SCIENCE = "science"
        const val SPORTS = "sports"
        const val TECHNOLOGY = "technology"

    }
}