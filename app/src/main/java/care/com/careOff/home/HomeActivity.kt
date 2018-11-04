package care.com.careOff.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.careOff.R
import care.com.careOff.Utils.replaceFragmentInActivity

class HomeActivity : AppCompatActivity(){

    private lateinit var homePresenter : HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val homeFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as HomeFragment? ?: HomeFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        homePresenter = HomePresenter(homeFragment)


        homeFragment.setPresenter(homePresenter)
    }

}