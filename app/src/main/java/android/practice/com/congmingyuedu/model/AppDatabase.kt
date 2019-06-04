package android.practice.com.congmingyuedu.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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
