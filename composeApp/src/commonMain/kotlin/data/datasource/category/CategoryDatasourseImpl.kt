package data.datasource.category

import data.model.ExpenseCategory
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

object CategoryDataSourceImpl : CategoryDataSource {
    private val firestore = Firebase.firestore
    private val categoriesCollection = firestore.collection("CATEGORIES")
    override suspend fun getAllCategories(): List<ExpenseCategory> {
        return try {
            val snapshot = categoriesCollection.get()
            snapshot.documents.map { document ->
                val categoryName = document.get<String>("name")
                categoryName.let { ExpenseCategory.fromString(categoryName) }
            }
        } catch (exception: Exception) {
            println("Error fetching categories: ${exception.message}")
            emptyList()
        }
    }

}
