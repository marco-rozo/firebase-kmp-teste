package data
import model.Expense
import model.ExpenseCategory
import domain.ExpenseRepository

class ExpenseRepoImpl(private val expenseManager: ExpenseManager): ExpenseRepository {
//class ExpenseRepoImpl: ExpenseRepository {
    override fun getAllExpense(): List<Expense> {
       return ExpenseManager.fakeExpenseList
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
        ExpenseManager.getCategories();
        return ExpenseManager.deleteExpense(expense)
    }
}
