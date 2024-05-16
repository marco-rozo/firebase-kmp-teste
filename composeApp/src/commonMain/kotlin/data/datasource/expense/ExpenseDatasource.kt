import data.model.Expense
import data.model.ExpenseCategory

interface ExpenseDataSource {
    suspend fun getAllExpenses(): List<Expense>
    suspend fun addExpense(expense: Expense)
    suspend fun editExpense(expense: Expense)
    suspend fun getCategories(): List<ExpenseCategory>
    suspend fun deleteExpense(expenseId: String)
}
