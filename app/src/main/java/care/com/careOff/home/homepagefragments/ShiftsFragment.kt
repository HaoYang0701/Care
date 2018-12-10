package care.com.careOff.home.homepagefragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import care.com.careOff.R
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import care.com.careOff.Model.Shift
import care.com.careOff.Utils.SharedPref
import care.com.careOff.data.database.source.remote.RemoteDataSource
import androidx.recyclerview.widget.DividerItemDecoration
import care.com.careOff.shift.ShiftDetailsActivity


class ShiftsFragment : Fragment() {
    private var title: String? = null
    private var page: Int? = 0
    private lateinit var recycler : RecyclerView
    private val shiftList = ArrayList<Shift>()
    lateinit var shiftAdapter : ShiftAdapter


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

        shiftAdapter.setOnItemClickListener(object : ShiftAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val shift = shiftList[position]
                val intent = Intent(context, ShiftDetailsActivity::class.java)
                intent.putExtra("shift_id", shift.Id)
                startActivity(intent)
            }
        })
        recycler.adapter = shiftAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        recycler.layoutManager = linearLayoutManager
        recycler.addItemDecoration(DividerItemDecoration(context,
                linearLayoutManager.orientation))
        return view
    }

    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()
        val sharedPref = SharedPref(context!!)
//        val accessToken = sharedPref.fetch("x-access-token")
//        val xID = sharedPref.fetch("x-id")
//        RemoteDataSource.getAllShifts(10, 0, "NEW", accessToken, xID).subscribe(
//                {
//                    for (shiftDetail in it.shiftList) {
//                        shiftList.add(shiftDetail.shift)
//                    }
//                },
//                {System.out.println(it.localizedMessage)})
        val shift1 = Shift(3,62, "2018-10-27T01:35:08.000Z", 480, 10,
                "Looking someone friendly and nice", true, "","UNPAID"
        ,"UNPAID", "NEW", "", "", "2018-11-28T02:52:22.000Z",
                "2018-11-28T02:52:22.000Z")

        val shift2 = Shift(4,61, "2018-10-27T01:35:08.000Z", 1180, 10,
                "Looking someone friendly and nice", true, "","UNPAID"
                ,"UNPAID", "NEW", "", "", "2018-11-28T02:52:22.000Z",
                "2018-11-28T02:52:22.000Z")

        shiftList.add(shift1)
        shiftList.add(shift2)
        shiftAdapter.notifyDataSetChanged()
    }

    class ShiftAdapter(val list : ArrayList<Shift>) : RecyclerView.Adapter<ShiftAdapter.ViewHolder>(){

        var listener: OnItemClickListener? = null

        interface OnItemClickListener {
            fun onItemClick(position: Int)
        }

        fun setOnItemClickListener(listener: OnItemClickListener) {
            this.listener = listener
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val shift = list[position]
            holder.dateTextView.text = shift.startDate
            holder.distanceTextView.text = ".3 Miles Away"
            holder.timeTextView.text = "6pm - 8 pm"
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
                itemView.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(v: View?) {
                        listener?.onItemClick(adapterPosition)
                    }
                })
            }

        }
    }

}