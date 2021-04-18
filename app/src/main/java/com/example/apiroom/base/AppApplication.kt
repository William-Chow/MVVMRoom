package com.example.apiroom.base

import android.app.Application
import com.example.apiroom.data.local.AppDatabase
import com.example.apiroom.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AppApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val dataBase by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { UserRepository(dataBase.userDao()) }
}
