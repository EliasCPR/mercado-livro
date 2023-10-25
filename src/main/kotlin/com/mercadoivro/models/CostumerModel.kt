package com.mercadoivro.models

import java.util.UUID

// o que é um dataclass
data class CostumerModel(
    var id: String,
    var name: String,
    var email: String
)