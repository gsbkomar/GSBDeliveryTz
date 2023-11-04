package gsbkomar.gsbdelivery.screens.tape

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gsbkomar.data.location.data.CityData
import gsbkomar.data.network.RetrofitFoodApiInstance
import gsbkomar.data.storage.DataCityStorageImpl
import gsbkomar.domain.storage.DataCityStorage
import gsbkomar.gsbdelivery.R
import gsbkomar.gsbdelivery.common.State
import gsbkomar.gsbdelivery.databinding.FragmentMainTapeBinding
import gsbkomar.gsbdelivery.factory.MainTapeViewModelFactory
import gsbkomar.gsbdelivery.screens.tape.adapters.banner.BannerItemListAdapter
import gsbkomar.gsbdelivery.screens.tape.adapters.category.CategoryItemListAdapter
import gsbkomar.gsbdelivery.screens.tape.adapters.product.CardProductItemListAdapter

import gsbkomar.gsbdelivery.screens.tape.models.banner.Banner
import gsbkomar.gsbdelivery.screens.tape.models.category.Category
import gsbkomar.gsbdelivery.screens.tape.models.category.data.CategoryData
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainTapeFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentMainTapeBinding? = null
    private val binding get() = _binding!!

    private var currentCategory = CategoryData.pizza

    private val categoryList = listOf(
        Category( "Пицца", CategoryData.pizza, false),
        Category( "Напитки", CategoryData.beverages, false),
        Category( "Бургеры", CategoryData.burger, false),
        Category( "Десерты",CategoryData.dessert, false)
    )

    @Inject
    lateinit var viewModelFactory: MainTapeViewModelFactory
    private val viewModel: MainTapeViewModel by viewModels { viewModelFactory }

    private lateinit var dataCityStorage: DataCityStorageImpl

    private val bannerListAdapter = BannerItemListAdapter {
        // Логика нажатия на баннер
    }
    private lateinit var categoryItemListAdapter: CategoryItemListAdapter
    private val cardProductItemListAdapter = CardProductItemListAdapter(this) {
        // В дальнейшем планируется отдельный экран по нажатию на товар
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTapeBinding.inflate(layoutInflater, container, false)
        initCategoryAdapterOnClick()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initCategoryAdapterOnClick() {
        categoryItemListAdapter = CategoryItemListAdapter(requireContext()) { position ->
            currentCategory = categoryList[position].categoryToRepository
            categoryList.forEach { category ->
                category.isSelected = false
            }
            categoryList[position].isSelected = true
            categoryItemListAdapter.notifyDataSetChanged()
            refreshCardProductList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataCityStorage = DataCityStorageImpl(requireActivity())
        checkStateInfo()
        initBannerAdapter()
        initCategoryAdapter()
        initCardProductAdapter()
        setCityInfo()
        initListeners()
    }

    private fun checkStateInfo() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is State.Error -> {
                        viewModel.error.collectLatest { message ->
                            Toast.makeText(requireContext(), message , Toast.LENGTH_SHORT).show()
                        }
                    }

                    State.Loading -> {
                        with(binding) {
                            rcCardProduct.isVisible = false
                            with(lottieLoading) {
                                isVisible = true
                                setAnimation(R.raw.lottie_loading)
                                playAnimation()
                            }
                        }
                    }

                    State.Success -> {
                        with(binding) {
                            rcCardProduct.isVisible = true
                            with(lottieLoading) {
                                isVisible = false
                                pauseAnimation()
                            }
                        }
                     }
                }
            }
        }
    }

    private fun initListeners() = with(binding) {
        cvCitySettings.setOnClickListener { findNavController().navigate(R.id.action_bottomNavFragment_to_citySettingsFragment) }
    }

    private fun setCityInfo() {
        viewModel.getCityData(dataCityStorage).let { city ->
            binding.tvCity.text = if (city.isNullOrEmpty()) CityData.moscow else city
        }
    }

    private fun initBannerAdapter() = with(binding.rcBanners) {
        val bannerList = listOf(
            Banner(requireActivity().getDrawable(R.drawable.banner_sales_1)!!),
            Banner(requireActivity().getDrawable(R.drawable.banner_sales_2)!!)
        )
        lifecycleScope.launch {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = bannerListAdapter
            val list = bannerList
            bannerListAdapter.submitList(list)
        }
    }

    private fun initCardProductAdapter() = with(binding.rcCardProduct) {
        lifecycleScope.launch {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = cardProductItemListAdapter
            refreshCardProductList()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshCardProductList()  {
        lifecycleScope.launch {
            cardProductItemListAdapter.submitList(viewModel.getFoodListByCategory(currentCategory).searchResults[0].results)
            cardProductItemListAdapter.notifyDataSetChanged()
        }
    }

    private fun initCategoryAdapter() = with(binding.rcCategory) {
        lifecycleScope.launch {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryItemListAdapter
            categoryItemListAdapter.submitList(categoryList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}