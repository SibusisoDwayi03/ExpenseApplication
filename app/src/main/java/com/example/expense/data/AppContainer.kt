package com.example.expense.data

import android.content.Context

interface AppContainer {
    val expensesRepository: ExpensesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val expensesRepository: ExpensesRepository by lazy {
        OfflineExpensesRepository(ExpenseDatabase.getDatabase(context).expenseDAO())
    }
}
