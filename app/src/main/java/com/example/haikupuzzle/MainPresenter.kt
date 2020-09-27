package com.example.haikupuzzle

interface MainPresenterInt {
    fun takeView(view: MainFragmentView)
    fun onClickRefreshButton()
    fun onClickSaveButton()
    fun saveHaiku(wordList: List<String>, haikuList: List<String>)
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

    override fun saveHaiku(wordList: List<String>, haikuList: List<String>) {
         // TODO shared Preference에 저장할것
        view.refresh()
        view.completeSaveHaiku(true)
    }
}