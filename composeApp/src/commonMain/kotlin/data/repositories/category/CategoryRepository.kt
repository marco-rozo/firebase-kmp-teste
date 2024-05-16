package data.repositories.category

import data.model.Expense
import data.model.ExpenseCategory

interface CategoryRepository {
    suspend fun getCategories(): List<ExpenseCategory>
}
