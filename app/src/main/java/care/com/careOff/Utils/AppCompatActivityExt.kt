package care.com.careOff.Utils

import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity

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


