package data.repositories.category
import data.datasource.category.CategoryDataSource
import data.model.ExpenseCategory

class CategoryRepositoryImpl(private val categoryDataSource: CategoryDataSource): CategoryRepository {
    override suspend fun getCategories(): List<ExpenseCategory> {
      return categoryDataSource.getAllCategories()
    }
}
