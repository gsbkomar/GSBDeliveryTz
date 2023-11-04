package gsbkomar.gsbdelivery.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gsbkomar.gsbdelivery.screens.tape.MainTapeViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainTapeViewModelFactory @Inject constructor(private val mainTapeViewModel: MainTapeViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(MainTapeViewModel::class.java)
        ) mainTapeViewModel as T else throw java.lang.IllegalArgumentException("Unknown class name")
}