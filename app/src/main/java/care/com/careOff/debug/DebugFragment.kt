package care.com.careOff.debug

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import care.com.careOff.Model.ServerType
import care.com.careOff.R
import care.com.careOff.databinding.DebugFragmentBinding
import care.com.careOff.registration.RegistrationFragment

class DebugFragment : DebugContract.View, Fragment() {

    private lateinit var presenter: DebugContract.Presenter
    private lateinit var viewBinding : DebugFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.debug_fragment, container, false)
        val view : View = viewBinding.root
        setUpClickListeners()
        return view
    }

    private fun setUpClickListeners() {
        viewBinding.devServer.setOnClickListener{
            presenter.setServerPreference(ServerType.DEV)
        }
        viewBinding.itServer.setOnClickListener{
            presenter.setServerPreference(ServerType.IT)
        }
    }


    override fun setDebugServerText(serverType: ServerType) {
        viewBinding.textEnviroment.text = "The server is currently " + serverType.name
    }

    override fun setPresenter(presenter: DebugContract.Presenter) {
        this.presenter = presenter
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
        fun newInstance() : DebugFragment {
            return DebugFragment()
        }
    }
}