package com.m_tech.room.mvvm_myatm.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.m_tech.room.mvvm_myatm.model.ATMTableModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(ATMTableModel: ATMTableModel)

    @Query("SELECT * FROM ManekBANK WHERE Withdraw_amount =:withdraw_amount")
    fun getLoginDetails(withdraw_amount: Int?) : LiveData<ATMTableModel>

    @Query("SELECT * FROM ManekBANK ORDER BY Id ASC")
    fun getListDetails() : Flow<List<ATMTableModel>>

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.

}