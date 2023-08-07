package com.autotrade.fullscreencarfeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.autotrade.common.theme.AutoTradeTheme
import com.autotrade.di.injectedViewModel
import com.autotrade.fullscreencarfeature.di.FullScreenCarFeatureComponentProvider

class CarRedactFragment : Fragment() {

    private val viewModel by injectedViewModel { stateHandle ->
        (requireActivity() as FullScreenCarFeatureComponentProvider)
            .provideFullScreenFeatureComponent()
            .viewModelFactory().create(
                stateHandle
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AutoTradeTheme {
                    CarRedactScreen(viewModel = viewModel) {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }
}