package com.m_tech.room.mvvm_myatm.room

import android.content.Context
import androidx.room.*
import com.m_tech.room.mvvm_myatm.model.ATMTableModel
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(ATMTableModel::class), version = 2, exportSchema = false)
abstract class Mtech_Database : RoomDatabase() {

    abstract fun RoomDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: Mtech_Database? = null

        fun getDataseClient(context: Context, scope: CoroutineScope) : Mtech_Database {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, Mtech_Database::class.java, "MTECH_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

/*        private class DatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            *//**
             * Override the onCreate method to populate the database.
             *//*
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                Mtech_Database.INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                       populateDatabase(database.loginDao())
                    }
                }
            }
        }

        *//**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         *//*
        suspend fun populateDatabase(DaoA: DAOAccess) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            DaoA.deleteAll()

            var word = Word("Hello")
            DaoA.InsertData(word)
            word = Word("World!")
            DaoA.insert(word)
        }*/

    }

}