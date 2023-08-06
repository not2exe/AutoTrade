package com.autotrade.searchscreenfeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.autotrade.common.theme.AutoTradeTheme
import com.autotrade.di.injectedViewModel
import com.autotrade.searchscreenfeature.di.SearchScreenFeatureComponentProvider

class SearchCarFragment : Fragment() {

    private val searchCarViewModel by injectedViewModel { stateHandle ->
        (requireActivity() as SearchScreenFeatureComponentProvider)
            .provideComponent().viewModelFactory().create(stateHandle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AutoTradeTheme {
                    SearchFragmentScreen(searchCarViewModel)
                }
            }
        }
    }
}