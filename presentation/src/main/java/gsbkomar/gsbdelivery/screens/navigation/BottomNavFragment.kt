package gsbkomar.gsbdelivery.screens.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import gsbkomar.gsbdelivery.R
import gsbkomar.gsbdelivery.databinding.FragmentNavBinding
import gsbkomar.gsbdelivery.extensions.loadFragment
import gsbkomar.gsbdelivery.screens.basket.BasketFragment
import gsbkomar.gsbdelivery.screens.profile.ProfileFragment
import gsbkomar.gsbdelivery.screens.tape.MainTapeFragment
import javax.inject.Inject

@AndroidEntryPoint
class BottomNavFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentNavBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFragment(MainTapeFragment())
        initListeners()
    }

    private fun initListeners() = with(binding) {
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu -> {
                    loadFragment(MainTapeFragment())
                    true
                }

                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> {
                    loadFragment(BasketFragment())
                    true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}