package com.example.testsdk.base.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.testsdk.R

open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun replaceFragment(
        withFragment: Fragment,
    ) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.fragment_slide_in,
                R.anim.fragment_fade_out,
                R.anim.fragment_fade_in,
                R.anim.fragment_slide_out
            )
            replace(R.id.fragment_container, withFragment)
            addToBackStack(null)
        }
    }

    fun addFragment(
        withFragment: Fragment,
    ) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.fragment_slide_in,
                R.anim.fragment_fade_out,
                R.anim.fragment_fade_in,
                R.anim.fragment_slide_out
            )
            add(R.id.fragment_container, withFragment)
            addToBackStack(null)
        }
    }


    fun replaceActivity(withActivity: BaseActivity) {
        val activityIntent = Intent(this, withActivity::class.java)
        startActivity(activityIntent)
        finishAfterTransition()
    }

    fun push(activity: BaseActivity) {
        val activityIntent = Intent(this, activity::class.java)
        startActivity((activityIntent))
    }

    fun showErrorDialog(withMessage: String) {
        runOnUiThread {
            AlertDialog.Builder(this)
                .setMessage(withMessage)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }
    }


    fun showSuccessDialog(withMessage: String) {
        runOnUiThread {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.alert_success_title))
                .setMessage(withMessage)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }
    }
}