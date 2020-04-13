package com.android.developerjobs.model

data class Job (
    val id: String,
    val type: String,
    val url: String,
    val created_at: String,
    val company: String,
    val company_url: String? = null,
    val location: String,
    val title: String,
    val description: String,
    val how_to_apply: String,
    val company_logo: String? = null
)