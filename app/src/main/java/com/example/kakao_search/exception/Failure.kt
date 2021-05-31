package com.example.kakao_search.exception


sealed class Failure {
    object NoNetworkConnection : Failure()
    object ServerError : Failure()
    object NotFoundInCache : Failure()

    abstract class FeatureFailure : Failure()
}
