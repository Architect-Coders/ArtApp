package com.android.leivacourse.artapp.ui.artgallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.data.*
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.utils.Output
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private fun setResponse(response: Output<SearchResults>) {
        when (response) {
            is Output.Success -> {
                _model.value = UiModel.Content((response.output).toImageDetail())
            }
            is Output.Error -> {
                //mArtGalleryView.errorMessage(response.exception.message)
            }
        }
    }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val artWork: List<ImageDetail>) : UiModel()
    }

    fun getDefaultArt() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                _model.value = UiModel.Loading
                lateinit var response: Output<SearchResults>
                response = getArts.invoke()
                setResponse(response)
            }
        }
    }

    fun loadDataByTitle(query: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                _model.value = UiModel.Loading
                lateinit var response: Output<SearchResults>
                if (query != null)
                    response = getArts.invokeSearch(query)
                else
                    response = getArts.invoke()
                setResponse(response)
            }
        }
    }

    class GetArts(private val mObrasRepository: GalleryArtRepository) {

        suspend fun invoke(): Output<SearchResults> {
            return mObrasRepository.getArtPhotos(
                DEFAULT_QUERY,
                DEFAULT_SEARCH_PAGE,
                QUERY_PAGE,
                DEFAULT_ORDER_BY,
                DEFAULT_ORIENTATION
            )
        }

        suspend fun invokeSearch(query: String): Output<SearchResults> {
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