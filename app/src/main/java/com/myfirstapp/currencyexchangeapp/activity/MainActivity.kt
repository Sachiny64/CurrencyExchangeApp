package com.myfirstapp.currencyexchangeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.myfirstapp.currencyexchangeapp.R
import com.myfirstapp.currencyexchangeapp.databinding.ActivityMainBinding
import com.myfirstapp.currencyexchangeapp.viewmodel.CurrencyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: CurrencyViewModel
    lateinit var from: String
    lateinit var to: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        myViewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]
        val data = intent
        val arrayList = data.getStringArrayListExtra("arrayList")
        val mutableArrayList: MutableList<String> = arrayList as MutableList<String>
        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, mutableArrayList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerFrom.adapter = arrayAdapter
        binding.spinnerTo.adapter = arrayAdapter

        binding.spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, p3: Long
            ) {
                from = parent!!.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        binding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, p3: Long
            ) {
                to = parent!!.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
        binding.btnExchange.setOnClickListener {

            if (binding.etQuantity.text!!.isNotEmpty()) {
                if (from.isNotEmpty() && to.isNotEmpty()) {
                    val quantity = binding.etQuantity.text.toString().toDouble()
                    myViewModel.myRepository.isExchanging.value = true
                    myViewModel.getExchangeData(from, to, quantity).observe(this) {
                        binding.tvExchangeResult.text = "${it * quantity} $to"
                    }
                } else {
                    Toast.makeText(this, "Please Select Currencies", Toast.LENGTH_SHORT).show()
                }
            } else {
                binding.etQuantity.error = " Enter Quantity plz"
            }
        }

        myViewModel.isConverting.observe(this){
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }
    }


    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }



}