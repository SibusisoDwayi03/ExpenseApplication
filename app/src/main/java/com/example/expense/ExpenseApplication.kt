package com.example.expense

import android.app.Application
import com.example.expense.data.AppContainer
import com.example.expense.data.AppDataContainer

class ExpenseApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
