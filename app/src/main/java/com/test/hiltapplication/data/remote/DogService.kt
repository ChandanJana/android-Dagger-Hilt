package com.test.hiltapplication.data.remote

import com.test.hiltapplication.model.DogResponse
import com.test.hiltapplication.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */

interface DogService {

    @GET(Constants.RANDOM_URL)
    suspend fun getDog(): Response<DogResponse>
}