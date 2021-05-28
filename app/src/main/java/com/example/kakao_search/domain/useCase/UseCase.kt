package com.example.kakao_search.domain.useCase

import com.example.kakao_search.exception.Failure
import com.example.kakao_search.functional.Either
import kotlinx.coroutines.*

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means that any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
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
