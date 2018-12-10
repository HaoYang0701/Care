package care.com.careOff.shift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import care.com.careOff.Network.ShiftInterestRequest
import care.com.careOff.R
import care.com.careOff.Utils.SharedPref
import care.com.careOff.data.database.source.remote.RemoteDataSource
import care.com.careOff.databinding.ShiftFragmentBinding

class ShiftDetailsFragment : ShiftDetailsContract.View ,Fragment() {
    private lateinit var presenter: ShiftDetailsContract.Presenter
    private lateinit var viewBinding : ShiftFragmentBinding

    override fun setPresenter(presenter: ShiftDetailsContract.Presenter) {
        this.presenter = presenter
    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            viewBinding = DataBindingUtil.inflate(inflater, R.layout.shift_fragment, container, false)
            val view : View = viewBinding.root
            setupClickListener()
            return view
        }

    private fun setupClickListener() {
        viewBinding.applyButton.setOnClickListener{v ->
            val sharedPref = SharedPref(context!!)
            val accessToken = sharedPref.fetch("x-access-token")
            val xID = sharedPref.fetch("x-id")
            val shiftInterestRequest = ShiftInterestRequest()
            shiftInterestRequest.shiftID = 62
            RemoteDataSource.applyShift(ShiftInterestRequest(), accessToken, xID).subscribe(
                    {
                        viewBinding.applyButton.text = "Applied Successfully"
                    }, {
                viewBinding.applyButton.text = "Applied Successfully"
                    System.out.println(it.localizedMessage)

            }
            )
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

    companion object {
        fun newInstance() : ShiftDetailsFragment{
            return ShiftDetailsFragment()
        }
    }
}