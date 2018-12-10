package care.com.careOff.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import care.com.CareOffAppCompatActivity
import care.com.careOff.R
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import care.com.careOff.home.homepagefragments.*
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout

class HomeActivity : CareOffAppCompatActivity(){
    private val tabImages : IntArray = intArrayOf(R.drawable.fa_medkit,
            R.drawable.fa_hospital,
            R.drawable.fa_bell,
            R.drawable.fa_user,
            R.drawable.fa_bell)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val viewPager = findViewById<ViewPager>(R.id.viewPager) as ViewPager
        val adapterViewPager = HomePagePagerAdapter(supportFragmentManager)
        viewPager.adapter = adapterViewPager

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        val tabLayout = findViewById<TabLayout>(R.id.sliding_tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

        setTabIcons(tabLayout)
    }

    private fun setTabIcons(tabLayout: TabLayout) {
        for (i in 0 .. 4) {
            tabLayout.getTabAt(i)?.setIcon(tabImages[i])
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val extras = intent?.extras
        System.out.println(extras)
    }

    class HomePagePagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        override fun getCount(): Int {
            return NUM_ITEMS
        }

        override fun getItem(position: Int): Fragment? {
            return when (position) {
                0 -> ShiftsFragment.newInstance(0, "Page # 1")
                1 -> FacilitiesFragment.newInstance(1, "Page # 2")
                2 -> PaymentsFragment.newInstance(2, "Page # 3")
                3 -> AccountFragment.newInstance(3, "Page # 4")
                4 -> AlertsFragment.newInstance(4, "Page # 5")
                else -> null
            }
        }

        companion object {
            private const val NUM_ITEMS = 5
        }
    }
}