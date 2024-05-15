package domain.repositories

import data.model.Expense
import data.model.ExpenseCategory

interface ExpenseRepository {

    suspend fun getAllExpense(): List<Expense>

   suspend fun addExpense(expense: Expense)

    fun editExpense(expense: Expense)

    fun getCategories(): List<ExpenseCategory>

    suspend fun deleteExpense(expense: Expense)

    //fun deleteExpense(expense: Expense): List<Expense>
}
