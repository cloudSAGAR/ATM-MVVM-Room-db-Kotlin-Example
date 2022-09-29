package com.m_tech.room.mvvm_myatm.repository

import android.content.Context
import com.m_tech.room.mvvm_myatm.model.ATMTableModel
import com.m_tech.room.mvvm_myatm.room.Mtech_Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ManekTRepository() {

    companion object {

        var mtechDatabase: Mtech_Database? = null

        lateinit var ATMTableModel: Flow<List<ATMTableModel>>

        val applicationScope = CoroutineScope(SupervisorJob())

        fun initializeDB(context: Context) : Mtech_Database {
            return Mtech_Database.getDataseClient(context, applicationScope)
        }

        fun insertData(context: Context, main_balance: Int, withdraw_amount: Int, rs100: Int, rs200: Int, rs500: Int, rs2000: Int, header_rs100: Int, header_rs200: Int, header_rs500: Int, header_rs2000: Int) {

            mtechDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val ListDetails = ATMTableModel(main_balance, withdraw_amount, rs100, rs200, rs500, rs2000, header_rs100, header_rs200, header_rs500, header_rs2000)
                mtechDatabase!!.RoomDao().InsertData(ListDetails)
            }
        }

        fun getListDetails(context: Context) : Flow<List<ATMTableModel>> {

            mtechDatabase = initializeDB(context)

            ATMTableModel = mtechDatabase!!.RoomDao().getListDetails()

            return ATMTableModel
        }

    }
}
