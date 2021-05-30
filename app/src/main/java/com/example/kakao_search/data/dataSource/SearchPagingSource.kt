//package com.example.kakao_search.data.dataSource
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.kakao_search.data.network.KakaoApiService
//import com.example.kakao_search.domain.search.Filter
//import com.example.kakao_search.domain.search.Search
//import com.example.kakao_search.exception.Failure
//import com.example.kakao_search.functional.Either
//import javax.inject.Inject
//
//
//internal class SearchPagingSource @Inject constructor(
//    private val kakaoApiService: KakaoApiService
//) : PagingSource<Int, Either<Failure, Search>>() {
//    /*
//      override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
//    try {
//      // Start refresh at page 1 if undefined.
//      val nextPage = params.key ?: 1
//      val response = movieApiService.getPopularMovies(nextPage)
//
//      return LoadResult.Page(
//        data = response.movies,
//        prevKey = if (nextPage == 1) null else nextPage - 1,
//        nextKey = response.page + 1
//      )
//    } catch (e: Exception) {
//        return LoadResult.Error(e)
//    }
//  }
//}
//     */
//
//    lateinit var filter: Filter
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Either<Failure, Search>>{
//        val nextPage = params.key ?: 1
//        val response = when (filter) {
//            is Filter.All -> kakaoApiService.fetchCafeSearch("")
//            is Filter.Type.Blog -> kakaoApiService.fetchBlogSearch("")
//            is Filter.Type.Cafe -> kakaoApiService.fetchCafeSearch("")
//        }
//
//
//        response.execute().
//
//    }
//
//
//    override fun getRefreshKey(state: PagingState<Int, Either<Failure, Search>>): Int? {
//    }
//
//}