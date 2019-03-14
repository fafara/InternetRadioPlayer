package io.github.vladimirmi.internetradioplayer.presentation.favoritelist

import android.view.View
import com.google.android.material.tabs.TabLayout
import io.github.vladimirmi.internetradioplayer.R
import io.github.vladimirmi.internetradioplayer.di.Scopes
import io.github.vladimirmi.internetradioplayer.extensions.visible
import io.github.vladimirmi.internetradioplayer.presentation.base.BaseFragment
import io.github.vladimirmi.internetradioplayer.presentation.favoritelist.records.RecordsFragment
import io.github.vladimirmi.internetradioplayer.presentation.favoritelist.stations.FavoriteStationsFragment
import io.github.vladimirmi.internetradioplayer.utils.SimpleOnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import toothpick.Toothpick


/**
 * Created by Vladimir Mikhalev 30.09.2017.
 */

class FavoriteListFragment : BaseFragment<FavoriteListPresenter, FavoriteListView>(),
        FavoriteListView {

    override val layout = R.layout.fragment_favorite_list

    private val onTabSelectedListener = object : SimpleOnTabSelectedListener() {
        override fun onTabSelected(tab: TabLayout.Tab) {
            presenter.selectTab(tab.position)
        }
    }

    override fun providePresenter(): FavoriteListPresenter {
        return Toothpick.openScopes(Scopes.ROOT_ACTIVITY, this)
                .getInstance(FavoriteListPresenter::class.java).also {
                    Toothpick.closeScope(this)
                }
    }

    override fun setupView(view: View) {
        navigationTl.addTab(navigationTl.newTab().apply { text = getString(R.string.tab_stations) })
        navigationTl.addTab(navigationTl.newTab().apply { text = getString(R.string.tab_records) })
        navigationTl.selectTab(null)
        navigationTl.addOnTabSelectedListener(onTabSelectedListener)
    }

    override fun showTabs(visible: Boolean) {
        navigationTl.visible(visible)
        if (!visible) presenter.selectTab(0)
    }

    override fun showPage(position: Int) {
        val fragment = when (position) {
            0 -> FavoriteStationsFragment()
            1 -> RecordsFragment()
            else -> return
        }
        fragment.setUserVisibleHint(userVisibleHint)
        childFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()

        selectTab(position)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isAdded) childFragmentManager.fragments.forEach { it.userVisibleHint = isVisibleToUser }
    }

    override fun selectTab(position: Int) {
        navigationTl.removeOnTabSelectedListener(onTabSelectedListener)
        navigationTl.getTabAt(position)?.select()
        navigationTl.addOnTabSelectedListener(onTabSelectedListener)
    }
}
