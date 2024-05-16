package data.datasource.category

import data.model.ExpenseCategory

interface CategoryDataSource {
    suspend fun getAllCategories(): List<ExpenseCategory>
}
