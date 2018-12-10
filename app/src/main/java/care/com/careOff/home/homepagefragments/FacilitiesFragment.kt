package care.com.careOff.home.homepagefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import care.com.careOff.R

class FacilitiesFragment : Fragment() {
    private var title: String? = null
    private var page: Int? = 0

    companion object {
        fun newInstance(page: Int, title: String): FacilitiesFragment {
            val facilitiesFragment = FacilitiesFragment()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", title)
            facilitiesFragment.arguments = args
            return facilitiesFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt("someInt", 0)
        title = arguments?.getString("someTitle")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.facilities_pager_fragment, container, false)
    }
}