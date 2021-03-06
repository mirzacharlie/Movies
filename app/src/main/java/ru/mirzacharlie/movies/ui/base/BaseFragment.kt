package ru.mirzacharlie.movies.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import ru.mirzacharlie.movies.di.ViewModelInjection
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding>(open val bindingFactory: (LayoutInflater) -> VB) :
    DaggerFragment() {

    @Inject
    @ViewModelInjection
    lateinit var viewModel: VM

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingFactory(layoutInflater)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
