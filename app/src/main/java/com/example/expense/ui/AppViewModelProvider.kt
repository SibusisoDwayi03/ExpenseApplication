package com.example.expense.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.expense.ExpenseApplication
import com.example.expense.ui.home.HomeViewModel
import com.example.expense.ui.item.ItemDetailsViewModel
import com.example.expense.ui.item.ExpenseEditViewModel
import com.example.expense.ui.item.ItemEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            ExpenseEditViewModel(
                this.createSavedStateHandle(),
                expenseApplication().container.expensesRepository
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            ItemEntryViewModel(expenseApplication().container.expensesRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                expenseApplication().container.expensesRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(expenseApplication().container.expensesRepository)
        }
    }
}

fun CreationExtras.expenseApplication(): ExpenseApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ExpenseApplication)
