package data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import dev.gitlive.firebase.firestore.firestore
import model.Expense
import model.ExpenseCategory

object ExpenseManager {

    private var currentId = 1L
    private val firestore = Firebase.firestore
    private val expenseCollection = firestore.collection("EXPENSE")
    val fakeExpenseList = mutableListOf(
        Expense(
            id = currentId++,
            amount = 70.0,
            category = ExpenseCategory.COFFEE,
            description = "3 Corações"
        ),
        Expense(
            id = currentId++,
            amount = 3000.0,
            category = ExpenseCategory.PARTY,
            description = "Bloco Camaleão - Bell Marques"
        ),
        Expense(
            id = currentId++,
            amount = 8.20,
            category = ExpenseCategory.SNACKS,
            description = "Biscoito"
        ),
        Expense(
            id = currentId++,
            amount = 10.2,
            category = ExpenseCategory.PARTY,
            description = "Farofa da Gkay"
        ),
        Expense(
            id = currentId++,
            amount = 1000.0,
            category = ExpenseCategory.CAR,
            description= "BYD DOLPHIN"
        ),
        Expense(
            id = currentId++,
            amount = 100.0,
            category = ExpenseCategory.HOUSE,
            description= "LIMPEZA"
        ),
        Expense(
            id = currentId++,
            amount = 1000.0,
            category = ExpenseCategory.OTHER,
            description= "SERVIÇOS PISCINA"
        ),

    )

    suspend fun getUsers(): List<Expense> {
        try {
            val response =
                this.firestore.collection("categories").get()

            println(response)

            println(response)
            return response.documents.map {
                it.data()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun addNewExpense(expense: Expense)
    {
            // Adiciona uma nova despesa à coleção "EXPENSE" no Firestore
        try {

        expenseCollection.add(expense)
        }catch (e: Exception) {
            println(e)
            println(e)
        } finally {
        fakeExpenseList.add(expense.copy(id = currentId++))
        }


    }

    fun  deleteExpense(expense: Expense)
    {
        val index = fakeExpenseList.indexOfFirst {
            it.id == expense.id
        }
        fakeExpenseList.removeAt(index)
    }

    fun editExpense(expense: Expense){
        val index = fakeExpenseList.indexOfFirst {
            it.id == expense.id
        }
        if (index != -1){
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description
            )
        }
    }

    fun getCategories(): List<ExpenseCategory>{
        return listOf(
            ExpenseCategory.PARTY,
            ExpenseCategory.COFFEE,
            ExpenseCategory.SNACKS,
            ExpenseCategory.CAR,
            ExpenseCategory.HOUSE,
            ExpenseCategory.OTHER,
        )
    }

}

