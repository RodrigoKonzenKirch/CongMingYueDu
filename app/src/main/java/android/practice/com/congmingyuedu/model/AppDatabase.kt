package android.practice.com.congmingyuedu.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(Text::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun textDao(): TextDao

    companion object {
        var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "texts-db.db"
                    ).build()
                }
                return INSTANCE
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}


//@Database(entities = [(Contact::class)], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun contactDao(): ContactDao
//
//    companion object {
//        var INSTANCE : AppDatabase? = null
//
//        fun getInstance(context: Context) : AppDatabase? {
//            if (INSTANCE == null) {
//                synchronized(AppDatabase::class) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        AppDatabase::class.java,
//                        "mvvmsample.db"
//                    ).build()
//                }
//
//                return INSTANCE
//            }
//
//            return INSTANCE
//        }
//
//        fun destroyInstance() {
//            INSTANCE = null
//        }
//    }
//}