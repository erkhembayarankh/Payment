package com.example.testsdk.base.fragment

import androidx.fragment.app.Fragment
import com.example.testsdk.base.activity.BaseActivity


open class BaseFragment: Fragment() {
    fun getBaseActivity() : BaseActivity {
        return activity as BaseActivity
    }
}