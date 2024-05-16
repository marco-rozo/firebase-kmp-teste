package data.repositories.expense

import data.model.Expense
import data.model.ExpenseCategory

interface ExpenseRepository {

    suspend fun getAllExpense(): List<Expense>

   suspend fun addExpense(expense: Expense)

    suspend fun editExpense(expense: Expense)

    suspend fun deleteExpense(expense: Expense)
}
