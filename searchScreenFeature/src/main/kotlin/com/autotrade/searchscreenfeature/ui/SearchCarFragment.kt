package com.autotrade.searchscreenfeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.autotrade.common.Deeplink
import com.autotrade.common.theme.AutoTradeTheme
import com.autotrade.di.injectedViewModel
import com.autotrade.searchscreenfeature.di.SearchScreenFeatureComponentProvider

class SearchCarFragment : Fragment() {

    private val searchCarViewModel by injectedViewModel { stateHandle ->
        (requireActivity() as SearchScreenFeatureComponentProvider)
            .provideSearchCarFeatureComponent().viewModelFactory().create(stateHandle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            val request = NavDeepLinkRequest.Builder
                .fromUri(Deeplink.NAVIGATE_TO_REDACT_CAR_FRAGMENT.deepLink)
                .build()
            setContent {
                AutoTradeTheme {
                    SearchFragmentScreen(
                        searchCarViewModel
                    ) {
                        findNavController().navigate(
                            request
                        )
                    }
                }
            }
        }
    }
}