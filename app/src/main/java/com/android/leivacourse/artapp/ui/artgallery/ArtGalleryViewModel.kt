package com.android.leivacourse.artapp.ui.artgallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.leivacourse.artapp.data.*
import com.android.leivacourse.artapp.data.local.model.ImageDetail
import com.android.leivacourse.artapp.utils.Output
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtGalleryViewModel(
    private val mObrasRepository: GalleryArtRepository) : ViewModel(){

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null)
                    getArtList(DEFAULT_QUERY, DEFAULT_SEARCH_PAGE, QUERY_PAGE, DEFAULT_ORDER_BY, DEFAULT_ORIENTATION)
            return _model
        }

    init {
    }

    sealed class  UiModel {
        object Loading : UiModel()
        class Content(val artWork : List<ImageDetail>) : UiModel()

    }

    private fun getArtList(query:String , page: Int, queryPage: Int, orderBy: String, orientation: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                _model.value = UiModel.Loading
            }

            val response = mObrasRepository.getArtPhotos(query, page, queryPage, orderBy, orientation)

            withContext(Dispatchers.Main) {
                when (response){
                    is Output.Success -> {
                  //      mArtGalleryView.populateArts(response.output.toImageDetail())
                        _model.value = UiModel.Content(response.output.toImageDetail())
                    }
                    is Output.Error ->{
                        //mArtGalleryView.errorMessage(response.exception.message)
                    }
                }
            }
        }
    }


    @Suppress("UNCHECKED_CAST")
    class ArtGalleryModelFactory(var repo : GalleryArtRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            ArtGalleryViewModel(repo) as T


    }
}