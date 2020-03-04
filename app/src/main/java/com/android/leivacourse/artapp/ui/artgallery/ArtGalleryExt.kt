package com.android.leivacourse.artapp.ui.artgallery

import com.android.leivacourse.artapp.api.models.SearchResults
import com.android.leivacourse.artapp.api.models.Urls
import com.android.leivacourse.artapp.data.local.model.ArtDetail

import com.android.leivacourse.artapp.api.models.User
import com.android.leivacourse.artapp.api.models.ProfileImage

fun SearchResults.toImageDetail(): List<ArtDetail> {
    val items = mutableListOf<ArtDetail>()
    return with(this) {
        this.results?.forEach { item ->
            items.add(
                ArtDetail(
                    item?.urls?.toUrl(),
                    0.toDouble(),
                    item?.description,
                    item?.id,
                    item?.user?.toUser()
                )
            )
        }
        return items
    }
}

fun Urls.toUrl(): com.android.leivacourse.artapp.data.local.model.Urls {
    return with(this) {
        com.android.leivacourse.artapp.data.local.model.Urls(small, thumb, raw, regular, full)
    }
}

fun User.toUser(): com.android.leivacourse.artapp.data.local.model.User {
    return with(this) {
        com.android.leivacourse.artapp.data.local.model.User(
            this.profileImage?.toProfileImage(),
            this.name,
            this.lastName,
            this.location,
            this.firstName
        )
    }
}

fun ProfileImage.toProfileImage(): com.android.leivacourse.artapp.data.local.model.ProfileImage {
    return with(this) {
        com.android.leivacourse.artapp.data.local.model.ProfileImage(
            small, large, medium
        )
    }
}

// return user type com.android.leivacourse.artapp.data.local.model
// map from user com.android.leivacourse.artapp.api.models

// profileImage return com.android.leivacourse.artapp.data.local.model
// profile image convert com.android.leivacourse.artapp.api.models