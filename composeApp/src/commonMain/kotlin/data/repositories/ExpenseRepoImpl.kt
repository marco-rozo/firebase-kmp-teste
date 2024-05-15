package data.repositories
import ExpenseDataSource
import data.ExpenseManager
import data.model.Expense
import data.model.ExpenseCategory
import domain.repositories.ExpenseRepository

//class ExpenseRepoImpl(private val expenseManager: ExpenseManager): ExpenseRepository {
class ExpenseRepoImpl(private val expenseDataSource: ExpenseDataSource): ExpenseRepository {
    override suspend fun getAllExpense(): List<Expense> {
        return expenseDataSource.getAllExpenses();
    }
    override suspend fun addExpense(expense: Expense) {
        ExpenseManager.addNewExpense(expense)
    }
    override fun editExpense(expense: Expense) {
        ExpenseManager.editExpense(expense)
    }
    override fun getCategories(): List<ExpenseCategory> {
      return ExpenseManager.getCategories()
    }
    override suspend fun deleteExpense(expense: Expense) {
        return ExpenseManager.deleteExpense(expense)
    }
}
