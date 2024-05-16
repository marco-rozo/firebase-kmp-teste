package data.repositories.expense
import ExpenseDataSource
import data.model.Expense

class ExpenseRepositoryImpl(private val expenseDataSource: ExpenseDataSource): ExpenseRepository {
    override suspend fun getAllExpense(): List<Expense> {
        return expenseDataSource.getAllExpenses();
    }
    override suspend fun addExpense(expense: Expense) {
        return expenseDataSource.addExpense(expense);
    }
    override suspend fun editExpense(expense: Expense) {
        return expenseDataSource.editExpense(expense);
    }
    override suspend fun deleteExpense(expense: Expense) {
        return expenseDataSource.deleteExpense(expense.id.toString());
    }
}
