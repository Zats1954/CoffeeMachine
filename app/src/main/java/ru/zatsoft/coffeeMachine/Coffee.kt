package ru.zatsoft.coffeeMachine

import java.io.Serializable

sealed class Coffee(
    val name: String,
    val volume: String,
    val sugar: String,
    val milk: String = ""
) : Serializable {

    class Americano(volume: String, sugar: String) : Coffee("Americano", volume, sugar)
    class Cappuccino(volume: String, sugar: String, milk: String) :
        Coffee("Cappuccino", volume, sugar, milk)

    class Latte(volume: String, sugar: String) : Coffee("Latte", volume, sugar)
    class Expresso(volume: String, sugar: String) : Coffee("Expresso", volume, sugar)
}
