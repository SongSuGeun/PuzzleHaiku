package com.example.haikupuzzle.data

data class HaikuModels(
    val saveDate: String,
    val dailyHaiku: WordModel,
    val haikuModel: HaikuModel,
    val correctCount: Int
)

data class WordModel(
    val firstWord: String,
    val secondWord: String,
    val thirdWord: String
)

data class HaikuModel(
    val firstHaiku: String,
    val secondHaiku: String,
    val thirdHaiku: String
)