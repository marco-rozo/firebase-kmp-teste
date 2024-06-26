package data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.PartyMode
import androidx.compose.material.icons.filled.ViewCozy
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.House
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import randomUUID

@Serializable
@SerialName("OwnedProject")
data class Expense(
    val id: String? = randomUUID(),
    val amount: Double,
    val category: ExpenseCategory,
    val description: String
) {
    val icon: ImageVector
        get() = category.icon
}