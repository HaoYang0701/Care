package care.com.careOff.shift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import care.com.careOff.R
import care.com.careOff.databinding.ShiftFragmentBinding

class ShiftFragment : ShiftContract.View ,Fragment() {
    private lateinit var presenter: ShiftContract.Presenter
    private lateinit var viewBinding : ShiftFragmentBinding

    override fun setPresenter(presenter: ShiftContract.Presenter) {}

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            viewBinding = DataBindingUtil.inflate(inflater, R.layout.shift_fragment, container, false)
            val view : View = viewBinding.root
            return view
        }

        override fun onResume() {
            super.onResume()
            presenter.subscribe()
        }

        override fun onPause() {
            super.onPause()
            presenter.unsubscribe()
        }

    companion object {
        fun newInstance() : ShiftFragment{
            return ShiftFragment()
        }
    }
}