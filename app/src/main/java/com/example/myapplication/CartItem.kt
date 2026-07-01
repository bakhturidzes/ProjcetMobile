package com.example.myapplication

// ინახავს პროდუქტის სახელს, ფასს და drawable სურათის რესურსს
data class CartItem(
    val name: String,
    val price: String,
    val imageResId: Int
)