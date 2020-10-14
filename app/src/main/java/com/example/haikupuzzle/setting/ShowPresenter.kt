package com.example.haikupuzzle.setting

import com.example.haikupuzzle.util.MySharedPreferences

interface ShowPresenterInt {
    fun takeView(view: ShowFragmentView)
    fun dropView()
    fun initData(mySharedPreferences: MySharedPreferences)
    fun getHaiku()
    fun onClickRemoveButton(position: Int)
    fun removeHaiku(position: Int)
}

class ShowPresenter : ShowPresenterInt {

    private var view: ShowFragmentView? = null
    lateinit var mySharedPreferences: MySharedPreferences

    override fun takeView(view: ShowFragmentView) {
        this.view = view
        view.initModel(mySharedPreferences.getSharedPreference())
    }

    override fun dropView() {
        this.view = null
    }

    override fun initData(mySharedPreferences: MySharedPreferences) {
        this.mySharedPreferences = mySharedPreferences
        getHaiku()
    }

    override fun getHaiku() {
        val haikuModels = mySharedPreferences.getSharedPreference()
        view?.notifyDataChanged(haikuModels)
    }

    override fun onClickRemoveButton(position: Int) {
        view?.showRemoveMessageDialog(position)
    }

    override fun removeHaiku(position: Int) {
        val removeHaikuModel = mySharedPreferences.removeSharedPreference(
            mySharedPreferences.getSharedPreference(),
            position
        )
        view?.notifyDataChanged(removeHaikuModel)
    }
}