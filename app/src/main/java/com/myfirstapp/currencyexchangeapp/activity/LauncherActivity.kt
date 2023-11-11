package com.myfirstapp.currencyexchangeapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.myfirstapp.currencyexchangeapp.R
import com.myfirstapp.currencyexchangeapp.databinding.ActivityLauncherBinding
import com.myfirstapp.currencyexchangeapp.viewmodel.CurrencyViewModel

class LauncherActivity : AppCompatActivity() {
    lateinit var binding: ActivityLauncherBinding
    lateinit var myViewModel: CurrencyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_launcher)


        myViewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]

        val intent = Intent(this, MainActivity::class.java)
        myViewModel.getCurrency().observe(this) {
            intent.putExtra("arrayList", it as ArrayList<String>)

        }


        myViewModel.isloading.observe(this, Observer {
            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.INVISIBLE
                startActivity(intent)
            }
        })

    }


}
