package com.m_tech.room.mvvm_myatm.view

import MyAtmListAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m_tech.room.mvvm_myatm.R
import com.m_tech.room.mvvm_myatm.viewmodel.AtmViewModel
import com.rbddevs.splashy.Splashy
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.DialogStyle
import com.thecode.aestheticdialogs.DialogType
import com.thecode.aestheticdialogs.OnDialogClickListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var atmViewModel: AtmViewModel
    lateinit var context: Context

    var main_balance: Int = 50000
    var rs100: Int = 20
    var rs200: Int = 10
    var rs500: Int = 20
    var rs2000: Int = 18

    var withdraw_amount: Int = 0
    var w_rs100: Int = 0
    var w_rs200: Int = 0
    var w_rs500: Int = 0
    var w_rs2000: Int = 0

    var withdraw_fix_amount: Int = 0

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    @Suppress("DEPRECATED_IDENTITY_EQUALS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity
        // Call it immediately after any setContentView() for quick launch
        setSplashy()

        // DECLARATION : recyclerView for list
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = MyAtmListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // DECLARATION : view model object
        atmViewModel = ViewModelProvider(this).get(AtmViewModel::class.java)


        // DECLARATION : LIVE DATA : using view model object > get pre data from Live data List
        atmViewModel.getListDetails(context)!!.observe(this, Observer {

            if (it == null || it.isEmpty()) {
                Handler().postDelayed({
                    Toast.makeText(applicationContext, " No transaction yet..", Toast.LENGTH_SHORT)
                        .show()
                }, 2800)
            } else {

                // Fetch data from - Room db

                main_balance = it.get(it.size - 1).Main_balance
                w_rs100 = it.get(it.size - 1).Rs100
                w_rs200 = it.get(it.size - 1).Rs200
                w_rs500 = it.get(it.size - 1).Rs500
                w_rs2000 = it.get(it.size - 1).Rs2000
                rs100 = it.get(it.size - 1).header_Rs100
                rs200 = it.get(it.size - 1).header_Rs200
                rs500 = it.get(it.size - 1).header_Rs500
                rs2000 = it.get(it.size - 1).header_Rs2000

                // SET data for Pre-load

                atm_amount_balance.text = "Rs. " + main_balance
                txt_rs100.text = rs100.toString()
                txt_rs200.text = rs200.toString()
                txt_rs500.text = rs500.toString()
                txt_rs2000.text = rs2000.toString()
                txt_last_atm_amount_balance.text = "Rs. " + it.get(it.size - 1).Withdraw_amount
                txt_last_txt_rs100.text = w_rs100.toString()
                txt_last_txt_rs200.text = w_rs200.toString()
                txt_last_txt_rs500.text = w_rs500.toString()
                txt_last_txt_rs2000.text = w_rs2000.toString()
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })

        btnWithdraw.setOnClickListener {

            // VALIDATION input is Number / String

            fun isNumber(s: String): Boolean {
                return try {
                    s.toInt()
                    true
                } catch (ex: NumberFormatException) {
                    false
                }
            }

            if (isNumber(txt_withdraw_amount.text.toString())) {
                val isDivisibleBy100 = txt_withdraw_amount.text.toString().toInt() % 100 === 0
                if (isDivisibleBy100) {

                    // VALIDATION :  if Input is less then 100 Number

                    if (txt_withdraw_amount.text.toString().toInt() < 100) {
                        txt_withdraw_amount.error = "Please enter valid amount"
                    } else {

                        // Notes :  Array 2000, 500, 200, 100 by order

                        val noteValues = intArrayOf(2000, 500, 200, 100)
                        w_rs100 = 0
                        w_rs200 = 0
                        w_rs500 = 0
                        w_rs2000 = 0

                        // VALIDATION :  if Input is greater then main balance

                        if (txt_withdraw_amount.text.toString().toInt() > main_balance) {
                            txt_withdraw_amount.error = "You have not sufficient balance.."
                        } else {
                            try {
                                withdraw_amount = txt_withdraw_amount.text.toString().toInt()
                                withdraw_fix_amount = withdraw_amount
                                var balance_new: Int = (main_balance - withdraw_amount)
                                atm_amount_balance.text = "Rs. " + "$balance_new"
                                main_balance = balance_new

                                // CHECK NOTES for WITHDRAW :  2000, 500, 200, 100 by order

                                var i = 0
                                while (i < noteValues.size && withdraw_amount != 0) {
                                    if (withdraw_amount >= noteValues[i])
                                    Log.e("MyATM>>", " " + "No of " + noteValues[i] + "'s" + " :" + withdraw_amount / noteValues[i])

                                    var not_deducted_amt: Int = 0

                                        // NOTES deduction :  2000, 500, 200, 100 by order

                                    if (noteValues[i] == 2000) {
                                          w_rs2000 = (withdraw_amount / noteValues[i])
                                            var rs2000_remaining: Int = (rs2000 - w_rs2000)
                                            if (rs2000_remaining < 0) {
                                                not_deducted_amt = noteValues[i] * (Math.abs(rs2000_remaining))
                                                w_rs2000 = rs2000
                                                rs2000 = 0
                                                txt_last_txt_rs2000.text = w_rs2000.toString()
                                                txt_rs2000.text = "0"
                                            }  else {
                                                txt_rs2000.text = "$rs2000_remaining"
                                                rs2000 = rs2000_remaining
                                            }
                                    }
                                    if (noteValues[i] == 500) {
                                        w_rs500 = (withdraw_amount / noteValues[i])
                                        var rs500_remaining: Int = (rs500 - w_rs500)
                                        if (rs500_remaining < 0) {
                                            not_deducted_amt = noteValues[i] * (Math.abs(rs500_remaining))
                                            w_rs500 = rs500
                                            rs500 = 0
                                            txt_last_txt_rs500.text = w_rs500.toString()
                                            txt_rs500.text = "0"
                                        }  else {
                                            txt_rs500.text = "$rs500_remaining"
                                            rs500 = rs500_remaining
                                        }
                                    }
                                    if (noteValues[i] == 200) {
                                        w_rs200 = (withdraw_amount / noteValues[i])
                                        var rs200_remaining: Int = (rs200 - w_rs200)
                                        if (rs200_remaining < 0) {
                                            not_deducted_amt = noteValues[i] * (Math.abs(rs200_remaining))
                                            w_rs200 = rs200
                                            rs200 = 0
                                            txt_last_txt_rs200.text = w_rs200.toString()
                                            txt_rs200.text = "0"
                                        }  else {
                                            txt_rs200.text = "$rs200_remaining"
                                            rs200 = rs200_remaining
                                        }
                                    }
                                    if (noteValues[i] == 100) {
                                        w_rs100 = (withdraw_amount / noteValues[i])
                                        var rs100_remaining: Int = (rs100 - w_rs100)
                                        if (rs100_remaining < 0) {
                                            not_deducted_amt = noteValues[i] * (Math.abs(rs100_remaining))
                                            w_rs100 = rs100
                                            rs100 = 0
                                            txt_last_txt_rs100.text = w_rs100.toString()
                                            txt_rs100.text = "0"
                                        }  else {
                                            txt_rs100.text = "$rs100_remaining"
                                            rs100 = rs100_remaining
                                        }
                                    }

                                    withdraw_amount %= noteValues[i]
                                    withdraw_amount += not_deducted_amt
                                    i++
                                }

                                // NOTES Reset :  if not deducted..

                                if (w_rs100 == rs100) {
                                    w_rs100 = 0
                                }
                                if (w_rs200 == rs200) {
                                    w_rs200 = 0
                                }
                                if (w_rs500 == rs500) {
                                    w_rs500 = 0
                                }
                                if (w_rs2000 == rs2000) {
                                    w_rs2000 = 0
                                }

                                // Error :  Handling..
                            } catch (ex: NumberFormatException) {
                                txt_withdraw_amount.error = "Enter valid number"
                            }
                            txt_withdraw_amount.text?.clear()

                // INSERT ::: NEW TRANSACTION  :  STORE in ROOM DB : send it to View Model
                            try {
                                atmViewModel.insertData(
                                    context,
                                    main_balance,
                                    withdraw_fix_amount,
                                    w_rs100,
                                    w_rs200,
                                    w_rs500,
                                    w_rs2000,
                                    rs100,
                                    rs200,
                                    rs500,
                                    rs2000
                                )

                                AestheticDialog.Builder(
                                    this,
                                    DialogStyle.EMOTION,
                                    DialogType.SUCCESS
                                )
                                    .setTitle("ManekTBank ATM")
                                    .setMessage("Congratulations, Withdraw success..")
                                    .setOnClickListener(object : OnDialogClickListener {
                                        override fun onClick(dialog: AestheticDialog.Builder) {
                                            dialog.dismiss()
                                        }
                                    })
                                    .show()

                            } catch (ex: NumberFormatException) {   // Error :  Handling..
                                txt_withdraw_amount.error = "Try again"
                            }
                        }
                    }
                } else {
                    txt_withdraw_amount.error = "Enter valid Number, that should divisible with 100"
                }
            } else {
                txt_withdraw_amount.error = "Not a valid Number"
            }
        }
    }

    fun setSplashy() {
        Splashy(this)         // For JAVA : new Splashy(this)
            .setLogo(R.drawable.mtatm_logo)
            .setTitle("mtATM")
            .setTitleColor("#FF8048E3")
            .setSubTitle("ATM app by Sagar Raval")
            .setProgressColor("#FF8048E3")
            //  .setBackgroundResource("#000000")
            .setFullScreen(true)
            .setTime(2500)
            .show()
    }
}


