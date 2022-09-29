package com.m_tech.room.mvvm_myatm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.m_tech.room.mvvm_myatm.model.ATMTableModel
import com.m_tech.room.mvvm_myatm.repository.ManekTRepository


class AtmViewModel() : ViewModel() {
    lateinit var liveDataATM: LiveData<List<ATMTableModel>>

    fun insertData(context: Context, main_balance: Int, withdraw_amount: Int, rs100: Int, rs200: Int, rs500: Int, rs2000: Int, header_rs100: Int, header_rs200: Int, header_rs500: Int, header_rs2000: Int) {
       ManekTRepository.insertData(context, main_balance, withdraw_amount, rs100, rs200, rs500, rs2000, header_rs100, header_rs200, header_rs500, header_rs2000)
    }

    fun getListDetails(context: Context) : LiveData<List<ATMTableModel>> {
        liveDataATM = ManekTRepository.getListDetails(context).asLiveData()
        return liveDataATM
    }

}