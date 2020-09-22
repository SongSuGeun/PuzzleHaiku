package com.example.haikupuzzle

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

interface MainFragmentView {
    fun refresh()
}

class MainFragment : Fragment(), MainFragmentView {

    companion object {
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle()
            }
    }

    private var wordList = listOf<String>()
    private var haikuList = listOf<String>()
    private lateinit var presenter: MainPresenterInt
    private var correctCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MainPresenter()

        presenter.takeView(this)

        wordList =
            listOf(firstWord.text.toString(), secondWord.text.toString(), thirdWord.text.toString())

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) return
                var number = 0

                haikuList =
                    listOf(
                        firstHaiku.text.toString(),
                        secondHaiku.text.toString(),
                        thirdHaiku.text.toString()
                    )

                if (haikuList.isEmpty()) return

                wordList.forEach {
                    if (haikuList.contains(it)) number += 1
                }
                correctCount = number
                if (correctCount > 3) {
                    haikuConfirmNotice.text = "定義した単語全部使いました。"
                } else {
                    haikuConfirmNotice.text = "定義した単語を全部使ってください。"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }

        firstHaiku.addTextChangedListener(textWatcher)
        secondHaiku.addTextChangedListener(textWatcher)
        thirdHaiku.addTextChangedListener(textWatcher)

        refreshButton.setOnClickListener {
            presenter.onClickRefreshButton()
        }
    }

    override fun refresh() {
        firstHaiku.run {
            this.clearFocus()
            this.text.clear()
        }
        secondHaiku.run {
            this.clearFocus()
            this.text.clear()
        }
        thirdHaiku.run {
            this.clearFocus()
            this.text.clear()
        }
    }
}