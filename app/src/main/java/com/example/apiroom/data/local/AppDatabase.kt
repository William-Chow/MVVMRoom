package com.example.apiroom.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.apiroom.di.DATABASE_NAME
import com.example.apiroom.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, applicationScope: CoroutineScope): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).addCallback(AppDatabaseCallback(applicationScope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.userDao())
                }
            }
        }

        suspend fun populateDatabase(users: UserDao) {
            // Delete all content here.
            users.deleteAll()

            // Add sample words.
            var user = User(1, "Vivian", "vivian@gmail.com", "Vivian Lee", "", "")
            users.insert(user)
            user = User(2, "Cindy", "cindy@gmail.com", "Cindy Wang", "", "")
            users.insert(user)
            user = User(3, "Karen", "karen@gmail.com", "Karen Wee", "", "")
            users.insert(user)
            user = User(4, "Mel", "mel@gmail.com", "Mel Wong", "", "")
            users.insert(user)
            user = User(5, "Kitty", "kitty@gmail.com", "Kitty Wong", "", "")
            users.insert(user)
            user = User(6, "Selina", "selina@gmail.com", "Selina Poh", "", "")
            users.insert(user)
            user = User(7, "May", "may@gmail.com", "May Chong", "", "")
            users.insert(user)
            user = User(8, "Celina", "celina@gmail.com", "Celina Ng", "", "")
            users.insert(user)
            user = User(9, "Amy", "amy@gmail.com", "Amy Pang", "", "")
            users.insert(user)
            user = User(10, "Lina", "lina@gmail.com", "Lina Ann", "", "")
            users.insert(user)
            user = User(11, "Mei Xin", "meixin@gmail.com", "Lee Mei Xin", "", "")
            users.insert(user)
        }
    }
}