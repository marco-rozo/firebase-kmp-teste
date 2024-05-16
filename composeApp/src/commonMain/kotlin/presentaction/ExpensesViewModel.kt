package presentaction

import data.model.Expense
import data.model.ExpenseCategory
import data.repositories.category.CategoryRepository
import data.repositories.expense.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

sealed class ExpensesUiState {
    object Loading : ExpensesUiState()
    data class Success(val expenses: List<Expense>, val total: Double) : ExpensesUiState()
    data class Error(val message: String) : ExpensesUiState()
}

class ExpensesViewModel(private val expenseRepository: ExpenseRepository, private val categoryRepository: CategoryRepository,) : ViewModel() {

    private val _uiState = MutableStateFlow<ExpensesUiState>(ExpensesUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _categories = MutableStateFlow<List<ExpenseCategory>>(emptyList())
    val categories: StateFlow<List<ExpenseCategory>> = _categories


    private fun loadCategories() {
        viewModelScope.launch {
            _categories.value = categoryRepository.getCategories()
        }
    }

    init {
        loadCategories()
        viewModelScope.launch {
            updateExpenseList()
        }
    }

    private suspend fun updateExpenseList() {
        try {
            val expenses = expenseRepository.getAllExpense()
            _uiState.value = ExpensesUiState.Success(expenses, expenses.sumOf { it.amount })
        } catch (e: Exception) {
            _uiState.value = ExpensesUiState.Error(e.message ?: "Ocurrio un error")
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                expenseRepository.addExpense(expense)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Ocurrio un error")
            }
        }
    }

    fun editExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                expenseRepository.editExpense(expense)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Ocurrio un error")
            }
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                expenseRepository.deleteExpense(expense)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Ocorreu um erro")
            }
        }
    }

    fun getExpenseWithID(id: String): Expense? {
        return (_uiState.value as? ExpensesUiState.Success)?.expenses?.firstOrNull { it.id == id }
    }

     suspend fun getCategories(): List<ExpenseCategory> {
        return categoryRepository.getCategories()
    }
}
