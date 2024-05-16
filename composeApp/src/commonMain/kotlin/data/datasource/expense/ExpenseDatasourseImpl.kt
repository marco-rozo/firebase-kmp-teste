import data.model.Expense
import data.model.ExpenseCategory
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
//import kotlinx.coroutines.tasks.await


object ExpenseDataSourceImpl : ExpenseDataSource {
    private val firestore = Firebase.firestore
    private val expenseCollection = firestore.collection("EXPENSES")

    override suspend fun getAllExpenses(): List<Expense> {
        // Implementação para obter todas as despesas do Firestore
        return try {
            val snapshot = expenseCollection.get()
            snapshot.documents.map {document ->
                val expense = document.data(Expense.serializer())
                val categoryString = document.get<String>("category")

                expense.copy(category = ExpenseCategory.fromString(categoryString))
                expense.copy(id = document.id)
            }
        } catch (exception: Exception) {
            println("Error fetching expenses: ${exception.message}")
            emptyList()
        }
    }

    override suspend fun addExpense(expense: Expense) {
        try {
            expenseCollection.add(expense)
            println("Expense added successfully")
        } catch (exception: Exception) {
            println("Error adding expense: ${exception.message}")
        }
    }

    override suspend fun editExpense(expense: Expense) {
        try {
            val docRef = expenseCollection.document(expense.id.toString())
            docRef.set(expense)
            println("Expense updated successfully")
        } catch (exception: Exception) {
            println("Error updating expense: ${exception.message}")
        }
    }

    override suspend fun getCategories(): List<ExpenseCategory> {
        // Implementação para obter as categorias de despesas
        return ExpenseCategory.values().toList()
    }

    override suspend fun deleteExpense(expenseId: String) {
        try {
            val docRef = expenseCollection.document(expenseId)
            docRef.delete()
            println("Expense deleted successfully")
        } catch (exception: Exception) {
            println("Error deleting expense: ${exception.message}")
        }
    }
}
