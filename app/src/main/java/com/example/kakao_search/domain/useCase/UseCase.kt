package com.example.kakao_search.domain.useCase

import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import kotlinx.coroutines.*


internal abstract class UseCase<out Result, in Params> {

    abstract suspend fun execute(params: Params): Either<Failure, Result>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope = GlobalScope,
        onResult: (Either<Failure, Result>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) { execute(params) }

            onResult(deferred.await())
        }
    }

    class None
}
