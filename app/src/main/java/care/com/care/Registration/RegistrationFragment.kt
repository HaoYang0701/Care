package care.com.care.Registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import care.com.care.R
import care.com.care.databinding.RegistrationFragmentBinding

class RegistrationFragment : RegistrationContract.View, Fragment(){
    private lateinit var presenter: RegistrationContract.Presenter

    override fun setPresenter(presenter: RegistrationContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : RegistrationFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)
        val view : View = binding.root
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
        fun newInstance() : RegistrationFragment{
            return RegistrationFragment()
        }
    }
}