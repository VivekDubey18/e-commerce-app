package com.example.e_commerceplatform

data class MyData(
    val limit: Int,
    val products: List<ProductX>,
    val skip: Int,
    val total: Int
)