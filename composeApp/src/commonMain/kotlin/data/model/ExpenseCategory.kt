package data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.PartyMode
import androidx.compose.material.icons.filled.ViewCozy
import androidx.compose.ui.graphics.vector.ImageVector

enum class ExpenseCategory(val icon: ImageVector){
    PARTY(Icons.Default.PartyMode),
    SNACKS(Icons.Default.Fastfood),
    COFFEE(Icons.Default.Coffee),
    CAR(Icons.Default.ElectricCar),
    HOUSE(Icons.Default.House),
    OTHER(Icons.Default.ViewCozy);

    companion object {
        fun fromString(category: String): ExpenseCategory {
            return when (category.uppercase()) {
                "PARTY" -> PARTY
                "SNACKS" -> SNACKS
                "COFFEE" -> COFFEE
                "CAR" -> CAR
                "HOUSE" -> HOUSE
                else -> OTHER
            }
        }
    }

}