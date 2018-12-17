package care.com.careOff.home

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
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

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

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
        tabLayout.setSelectedTabIndicator(R.color.text_color)
        setTabIcons(tabLayout)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab : TabLayout.Tab?) {
                tab?.getIcon()?.setColorFilter(resources.getColor(R.color.text_color), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab : TabLayout.Tab?) {
                tab?.getIcon()?.setColorFilter(resources.getColor(R.color.gray), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
    }

    private fun setTabIcons(tabLayout: TabLayout) {
        for (i in 0 .. 4) {
            tabLayout.getTabAt(i)?.setIcon(tabImages[i])
        }
        tabLayout.getTabAt(0)?.icon?.setColorFilter(resources.getColor(R.color.text_color), PorterDuff.Mode.SRC_IN)
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