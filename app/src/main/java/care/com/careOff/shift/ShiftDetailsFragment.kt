package care.com.careOff.shift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
            activity!!.findViewById<TextView>(R.id.toolbar_title).text = "Facilty's Name"
            return view
        }


    override fun setTime(timeString: String) {
        viewBinding.time.text = timeString
    }

    override fun setDistance(distanceString: String) {
        viewBinding.distance.text = distanceString
    }

    override fun setDate(dateString: String) {
        viewBinding.date.text = dateString
    }

    private fun setupClickListener() {
        viewBinding.applyButton.setOnClickListener{v ->
            val sharedPref = SharedPref(context!!)
            val accessToken = "5ffa5cb70c09e6a0a2c21f1bba5211220c32e3e19a82571579cddda57fedb389" //sharedPref.fetch("x-access-token")
            val xID = "46" //sharedPref.fetch("x-id")
            val shiftInterestRequest = ShiftInterestRequest()
            shiftInterestRequest.shiftID = 13
            RemoteDataSource.applyShift(ShiftInterestRequest(), accessToken, xID).subscribe(
                    {
                        viewBinding.applyButton.text = "Applied Successfully"
                    }, {
                viewBinding.applyButton.text = "Error Applying"
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