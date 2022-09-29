package com.m_tech.room.mvvm_myatm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ManekBANK")
data class ATMTableModel (

    @ColumnInfo(name = "main_balance")
    var Main_balance: Int,

    @ColumnInfo(name = "withdraw_amount")
    var Withdraw_amount: Int,

    @ColumnInfo(name = "rs100")
    var Rs100: Int,

    @ColumnInfo(name = "rs200")
    var Rs200: Int,

    @ColumnInfo(name = "rs500")
    var Rs500: Int,

    @ColumnInfo(name = "rs2000")
    var Rs2000: Int,

    @ColumnInfo(name = "header_rs100")
    var header_Rs100: Int,

    @ColumnInfo(name = "header_rs200")
    var header_Rs200: Int,

    @ColumnInfo(name = "header_rs500")
    var header_Rs500: Int,

    @ColumnInfo(name = "header_rs2000")
    var header_Rs2000: Int

) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}