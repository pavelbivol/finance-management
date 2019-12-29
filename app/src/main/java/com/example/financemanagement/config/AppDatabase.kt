package com.example.financemanagement.config

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.financemanagement.dao.ChargeDao
import com.example.financemanagement.domain.db.CategoryIcon
import com.example.financemanagement.domain.db.Charge
import com.example.financemanagement.domain.db.ChargeCategory
import java.util.concurrent.Executors


@Database(entities = [Charge::class, ChargeCategory::class, CategoryIcon::class], version = 1)
public abstract class AppDatabase : RoomDatabase() {



    abstract fun dataDao(): ChargeDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        @Synchronized
        open fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE
        }

        fun buildDatabase(context: Context): AppDatabase? {
            return Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    "finance-management-db")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors
                                    .newSingleThreadScheduledExecutor()
                                    .execute { getInstance(context)!!
                                            .dataDao()
                                            .insertAll(CategoryIcon.populateData()) }
                        }
                    })
                    .build()
        }
    }



    /*companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "word_database"
                ).addCallback(object : Callback() {
                    override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors
                                .newSingleThreadScheduledExecutor()
                                .execute(Runnable { getInstance(context).dataDao().insertAll(CategoryIcon.populateData()) })
                    }
                })


                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }*/

}