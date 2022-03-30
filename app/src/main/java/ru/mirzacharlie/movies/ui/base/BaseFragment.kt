package ru.mirzacharlie.movies.ui.base

import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment
import ru.mirzacharlie.movies.di.ViewModelInjection
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {

    @Inject
    @ViewModelInjection
    lateinit var viewModel: VM

//    private var _binding: VB? = null
//    protected val binding: VB get() = _binding!!
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        _binding = bindingFactory(layoutInflater)
//        return _binding?.root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}
