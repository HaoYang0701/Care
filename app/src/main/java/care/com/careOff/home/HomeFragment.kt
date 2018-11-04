package care.com.careOff.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import care.com.careOff.R
import care.com.careOff.databinding.HomeFragmentBinding

class HomeFragment : HomeContract.View, Fragment() {

    private lateinit var presenter: HomeContract.Presenter
    private lateinit var viewBinding : HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        val view = viewBinding.root
        setUpClickListeners()
        return view
    }

    private fun setUpClickListeners() {
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun setPresenter(presenter: HomeContract.Presenter) {
        this.presenter = presenter
    }


    companion object {
        fun newInstance() : HomeFragment{
            return HomeFragment()
        }
    }


}