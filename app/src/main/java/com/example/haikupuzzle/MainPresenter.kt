package com.example.haikupuzzle

import com.example.haikupuzzle.data.HaikuModel
import com.example.haikupuzzle.data.HaikuModels
import com.example.haikupuzzle.data.WordModel
import java.time.LocalDate

interface MainPresenterInt {
    fun takeView(view: MainFragmentView)
    fun onClickRefreshButton()
    fun onClickSaveButton()
    fun saveHaiku(wordList: List<String>, haikuList: List<String>, correctCount: Int)
}

class MainPresenter : MainPresenterInt {

    lateinit var view: MainFragmentView

    override fun takeView(view: MainFragmentView) {
        this.view = view
    }

    override fun onClickRefreshButton() {
        view.refresh()
    }

    override fun onClickSaveButton() {
        view.showSaveDialog()
    }

    override fun saveHaiku(wordList: List<String>, haikuList: List<String>, correctCount: Int) {
        // TODO shared Preference에 저장할것
        val haikuModel = HaikuModel(haikuList[0], haikuList[1], haikuList[2])
        val wordModel = WordModel(wordList[0], wordList[1], wordList[2])
        val currentDate = LocalDate.now().toString()

        val haikuModels = HaikuModels(currentDate, wordModel, haikuModel, correctCount)

        view.refresh()
        view.completeSaveHaiku(true)
    }
}