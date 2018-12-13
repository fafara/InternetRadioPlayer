package io.github.vladimirmi.internetradioplayer.presentation.main

import io.github.vladimirmi.internetradioplayer.presentation.base.BaseView

/**
 * Created by Vladimir Mikhalev 23.10.2017.
 */

interface MainView : BaseView {

    fun setPageId(pageId: Int)

    fun setMetadata(metadata: String)

    fun showStopped()

    fun showBuffering()

    fun showPlaying()
}
