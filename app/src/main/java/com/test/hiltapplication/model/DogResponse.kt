package com.test.hiltapplication.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */

data class DogResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)