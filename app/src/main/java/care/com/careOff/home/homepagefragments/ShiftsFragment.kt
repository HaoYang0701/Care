package care.com.careOff.home.homepagefragments

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import care.com.careOff.R
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import care.com.careOff.Utils.SharedPref
import care.com.careOff.data.database.source.remote.RemoteDataSource
import androidx.recyclerview.widget.DividerItemDecoration
import care.com.careOff.Model.ShiftDetail
import care.com.careOff.Model.ShiftStatus
import care.com.careOff.Utils.DateUtil
import care.com.careOff.shift.ShiftDetailsActivity
import com.google.android.material.button.MaterialButton


class ShiftsFragment : Fragment() {
    private var title: String? = null
    private var page: Int? = 0
    private lateinit var recycler : RecyclerView
    private val shiftList = ArrayList<ShiftDetail>()
    lateinit var shiftAdapter : ShiftAdapter
    private lateinit var nameTextView : TextView


    companion object {
        fun newInstance(page: Int, title: String): ShiftsFragment {
            val shiftFragment = ShiftsFragment()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", title)
            shiftFragment.arguments = args
            return shiftFragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt("someInt", 0)
        title = arguments?.getString("someTitle")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.shift_pager_fragment, container, false)
        recycler = view.findViewById(R.id.recycler_view)
        shiftAdapter = ShiftAdapter(shiftList)
        nameTextView = view.findViewById(R.id.username)

        shiftAdapter.setOnItemClickListener(object : ShiftAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val shift = shiftList[position]
                val intent = Intent(context, ShiftDetailsActivity::class.java)
                intent.putExtra("shift_id", shift.shift.Id)
                startActivity(intent)
            }
        })
        recycler.adapter = shiftAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        recycler.layoutManager = linearLayoutManager
        recycler.addItemDecoration(DividerItemDecoration(context,
                linearLayoutManager.orientation))
        setUpButtonListeners(view)

        activity!!.findViewById<TextView>(R.id.toolbar_title).text = "Shifts"
        return view
    }

    private fun setUpButtonListeners(inflatedView: View) {
        val availButton = inflatedView.findViewById<MaterialButton>(R.id.availible)
        val assignedButton = inflatedView.findViewById<MaterialButton>(R.id.assigned)
        val interestedButton = inflatedView.findViewById<MaterialButton>(R.id.interested)
        val pastButton = inflatedView.findViewById<MaterialButton>(R.id.past)

        val sharedPref = SharedPref(context!!)
        val accessToken = "5ffa5cb70c09e6a0a2c21f1bba5211220c32e3e19a82571579cddda57fedb389"//sharedPref.fetch("x-access-token")
        val xID = "46" //sharedPref.fetch("x-id")

        availButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.blue))
        availButton.setTextColor(context!!.resources.getColor(R.color.accent))

        availButton.setOnClickListener{v ->
            clearAllButtons(availButton, assignedButton, interestedButton, pastButton)
            availButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.blue))
            availButton.setTextColor(context!!.resources.getColor(R.color.accent))
            loadShifts(accessToken, xID, ShiftStatus.NEW)
        }

        assignedButton.setOnClickListener{v ->
            clearAllButtons(availButton, assignedButton, interestedButton, pastButton)
            assignedButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.blue))
            assignedButton.setTextColor(context!!.resources.getColor(R.color.accent))
            loadShifts(accessToken, xID, ShiftStatus.ASSIGNED)
        }

        interestedButton.setOnClickListener{v ->
            clearAllButtons(availButton, assignedButton, interestedButton, pastButton)
            interestedButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.blue))
            interestedButton.setTextColor(context!!.resources.getColor(R.color.accent))
            loadShifts(accessToken, xID, ShiftStatus.INTERESTED)
        }

        pastButton.setOnClickListener{v ->
            clearAllButtons(availButton, assignedButton, interestedButton, pastButton)
            pastButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.blue))
            pastButton.setTextColor(context!!.resources.getColor(R.color.accent))
            loadShifts(accessToken, xID, ShiftStatus.PAST)
        }
    }

    private fun clearAllButtons(availButton: MaterialButton, assignedButton: MaterialButton, interestedButton: MaterialButton, pastButton: MaterialButton) {
        availButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.accent))
        availButton.setTextColor(context!!.resources.getColor(R.color.primary_text))

        assignedButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.accent))
        assignedButton.setTextColor(context!!.resources.getColor(R.color.primary_text))

        interestedButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.accent))
        interestedButton.setTextColor(context!!.resources.getColor(R.color.primary_text))

        pastButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.accent))
        pastButton.setTextColor(context!!.resources.getColor(R.color.primary_text))
    }


    override fun onResume() {
        super.onResume()
        val sharedPref = SharedPref(context!!)
        val accessToken = "5ffa5cb70c09e6a0a2c21f1bba5211220c32e3e19a82571579cddda57fedb389"//sharedPref.fetch("x-access-token")
        val xID = "46" //sharedPref.fetch("x-id")
        loadShifts(accessToken, xID, ShiftStatus.NEW)
        loadUserDetails(accessToken, xID)
    }

    private fun loadUserDetails(accessToken: String, xID: String) {
        RemoteDataSource.getUserDetails(accessToken, xID).subscribe(
                {
                    nameTextView.text = String.format(getString(R.string.welcome), it.userDetail.firstName)
                }, {

        }
        )
    }

    private fun loadShifts(accessToken: String, xID: String, shiftStatus: ShiftStatus) {
        clearData()
        RemoteDataSource.getAllShifts(10, 0, shiftStatus.status, accessToken, xID).subscribe(
                {
                    val shiftListResponse = it.data.shiftDetailList
                    for (shiftDetail in shiftListResponse.orEmpty()) {
                        shiftList.add(shiftDetail)
                    }
                    shiftAdapter.notifyDataSetChanged()
                },
                { System.out.println(it.localizedMessage) })
    }

    override fun onStop() {
        super.onStop()
        shiftList.clear()
        shiftAdapter.notifyDataSetChanged()
    }

    fun clearData() {
        shiftList.clear()
        shiftAdapter.notifyDataSetChanged()
    }

    class ShiftAdapter(val list : ArrayList<ShiftDetail>) : RecyclerView.Adapter<ShiftAdapter.ViewHolder>(){

        var listener: OnItemClickListener? = null

        interface OnItemClickListener {
            fun onItemClick(position: Int)
        }

        fun setOnItemClickListener(listener: OnItemClickListener) {
            this.listener = listener
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val shift = list[position]
            holder.dateTextView.text = DateUtil.getDate(shift.shift.startDate)
            holder.distanceTextView.text = ".3 Miles Away"
            holder.timeTextView.text = DateUtil.getTime(shift.shift.startDate, shift.shift.lengthInMins)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val itemView = inflater.inflate(R.layout.shift_item, parent, false)
            return ViewHolder(itemView)
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var dateTextView: TextView
            var timeTextView: TextView
            var distanceTextView: TextView
            var linearLayout : LinearLayout

            init {
                dateTextView = itemView.findViewById(R.id.date)
                timeTextView = itemView.findViewById(R.id.time)
                distanceTextView = itemView.findViewById(R.id.distance)
                linearLayout = itemView.findViewById(R.id.shift_item_container)
                itemView.setOnClickListener { listener?.onItemClick(adapterPosition) }
            }

        }
    }

}