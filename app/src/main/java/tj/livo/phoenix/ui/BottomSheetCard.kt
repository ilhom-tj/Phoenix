package tj.livo.phoenix.ui

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomSheetCard(

    val icon: ImageVector,
    val title: String,
    val type : String = "",
)
