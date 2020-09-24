package com.example.haikupuzzle

interface MainPresenterInt {
    fun takeView(view: MainFragmentView)
    fun onClickRefreshButton()
}

class MainPresenter : MainPresenterInt {

    lateinit var view: MainFragmentView

    override fun takeView(view: MainFragmentView) {
        this.view = view
    }

    override fun onClickRefreshButton() {
        view.refresh()
    }
}