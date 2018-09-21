package care.com.care.Utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId : Int) {
    supportFragmentManager.transact { replace(frameId, fragment) }
}

fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, frameId: Int){
    supportFragmentManager.transact { add(frameId, fragment) }
}

fun AppCompatActivity.setupActrionBar(toolbarId : Int, action : ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run(action)
}

private inline fun FragmentManager.transact(action : FragmentTransaction.() -> Unit) {
    beginTransaction().apply(action).commit()
}


