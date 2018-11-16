package care.com.careOff.login.twoFactorAuth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import care.com.careOff.R
import care.com.careOff.databinding.TwoFactorFragmentBinding
import care.com.careOff.home.HomeActivity

class TwoFactorFragment : TwoFactorContract.View, Fragment() {
    override fun goToHomeScreen() {
        startActivity(Intent(context, HomeActivity::class.java))
    }

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
            presenter.verifyOTP(viewBinding.txtPinEntry.text.toString())
        }
        viewBinding.resendOtp.setOnClickListener{
            presenter.resendOTP()
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