package com.basel.nytimesapp.network
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: () -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch()) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            println("articles ${query().toString()}")
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}