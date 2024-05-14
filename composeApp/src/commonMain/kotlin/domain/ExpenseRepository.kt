package domain

import model.Expense
import model.ExpenseCategory

interface ExpenseRepository {

    fun getAllExpense(): List<Expense>

   suspend fun addExpense(expense: Expense)

    fun editExpense(expense: Expense)

    fun getCategories(): List<ExpenseCategory>

    suspend fun deleteExpense(expense: Expense)

    //fun deleteExpense(expense: Expense): List<Expense>
}
