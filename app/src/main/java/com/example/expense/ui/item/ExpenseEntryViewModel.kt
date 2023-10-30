package com.example.expense.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expense.data.Expense
import com.example.expense.data.ExpensesRepository
import java.text.NumberFormat

class ItemEntryViewModel(private val itemsRepository: ExpensesRepository) : ViewModel() {


    var itemUiState by mutableStateOf(ItemUiState())
        private set


    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }


    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertExpense(itemUiState.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && amount.isNotBlank() && category.isNotBlank()
        }
    }
}


data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val amount: String = "",
    val category: String = "",
    val date: String = "",
    val description: String= ""
)

fun ItemDetails.toItem(): Expense = Expense(
    id = id,
    name = name,
    amount = amount.toDoubleOrNull() ?: 0.0,
    category = category,
    date = date,
    description = description
)

fun Expense.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(amount)
}


fun Expense.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)


fun Expense.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    amount = amount.toString(),
    category = category.toString(),
    date = date,
    description = description

)
