package com.test.hiltapplication.data.remote

import javax.inject.Inject

/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
class RemoteDataSource @Inject constructor(private val dogService: DogService) {

    suspend fun getDog() =
        dogService.getDog()

}