package com.android.leivacourse.artapp.ui.artgallery.gallery

import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion

class ManageOnSearchListener(
    private val presenter: ArtGalleryPresenter
) : FloatingSearchView.OnSearchListener {
    override fun onSearchAction(currentQuery: String?) {
        currentQuery?.let {
            presenter.setQuery(it)
            presenter.getArtList()
        }
    }

    override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
        //do nothing
    }
}