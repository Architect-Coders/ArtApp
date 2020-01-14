package com.android.leivacourse.artapp.ui.artgallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.leivacourse.artapp.api.models.ResultsItem
import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.data.*
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ArtGalleryViewModel(
    private val getArts: GetArts
) : ViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null)
                getDefaultArt()
            return _model
        }

    init{
    }

    private fun setResponse(response: SearchResults) {
        _model.value = UiModel.Content(response.toImageDetail())
    }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val artWork: List<ImageDetail>) : UiModel()
    }

    fun getDefaultArt() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                _model.value = UiModel.Loading
                handleResponse(getArts.invoke())
            }
        }
    }

    fun loadDataByTitle(query: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                _model.value = UiModel.Loading
                handleResponse(getArts.invokeSearch(query))
            }
        }
    }

    private fun handleResponse(response: ResultWrapper<Response<SearchResults>>) {
        when (response) {
            is ResultWrapper.Success -> {
                response.value.body()?.let { it }?.let { searchResults -> setResponse(searchResults) }
                response.value.errorBody()?.let { setResponse(SearchResults(0,0, listOf<ResultsItem>())) }
            }
            is ResultWrapper.GenericError -> setResponse(response.error?.errors as SearchResults)
            is ResultWrapper.NetworkError -> setResponse(SearchResults(0,0, listOf<ResultsItem>()))
        }
    }

    class GetArts(private val mObrasRepository: GalleryArtRepository) {

        suspend fun invoke(): ResultWrapper<Response<SearchResults>> {
            return mObrasRepository.getArtPhotos(
                DEFAULT_QUERY,
                DEFAULT_SEARCH_PAGE,
                QUERY_PAGE,
                DEFAULT_ORDER_BY,
                DEFAULT_ORIENTATION
            )
        }

        suspend fun invokeSearch(query: String): ResultWrapper<Response<SearchResults>> {
            return mObrasRepository.getArtPhotos(
                query,
                DEFAULT_SEARCH_PAGE,
                QUERY_PAGE,
                DEFAULT_ORDER_BY,
                DEFAULT_ORIENTATION
            )
        }
    }
}