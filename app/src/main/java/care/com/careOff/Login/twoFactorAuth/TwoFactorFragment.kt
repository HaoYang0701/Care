package care.com.careOff.login.twoFactorAuth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import care.com.careOff.R
import care.com.careOff.databinding.TwoFactorFragmentBinding

class TwoFactorFragment : TwoFactorContract.View, Fragment() {

    private lateinit var presenter: TwoFactorContract.Presenter
    private lateinit var viewBinding: TwoFactorFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.two_factor_fragment, container, false)
        val view = viewBinding.root
        setUpClickListeners()
        return view
    }

    private fun setUpClickListeners() {
        viewBinding.pinEntryButton.setOnClickListener{v ->
            System.out.println("PLEASE")
            System.out.println(viewBinding.txtPinEntry.text)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun setPresenter(presenter: TwoFactorContract.Presenter) {
        this.presenter = presenter
    }


    companion object {
        fun newInstance() : TwoFactorFragment {
            return TwoFactorFragment()
        }
    }
}