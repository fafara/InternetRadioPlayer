package io.github.vladimirmi.internetradioplayer.presentation.data

import io.github.vladimirmi.internetradioplayer.domain.model.Media
import io.github.vladimirmi.internetradioplayer.presentation.base.BaseView

/**
 * Created by Vladimir Mikhalev 21.03.2019.
 */

interface DataView : BaseView {

    fun setData(data: List<Media>)

    fun selectMedia(media: Media)

    fun showLoading(loading: Boolean)
}