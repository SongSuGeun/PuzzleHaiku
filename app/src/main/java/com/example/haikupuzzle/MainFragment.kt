package com.example.haikupuzzle

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haikupuzzle.util.JsonResourcesUtils
import kotlinx.android.synthetic.main.fragment_main.*
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException

interface MainFragmentView {
    fun refresh()
    fun showSaveDialog()
    fun completeSaveHaiku(isSaveHaiku: Boolean)
}

class MainFragment : Fragment(), MainFragmentView {

    companion object {
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle()
            }
    }

    private var haikuList = mutableListOf<String>()
    private var correctCount = 0

    private lateinit var presenter: MainPresenterInt

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

        setHaikuWord(parsingToJson())

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) return
                correctCount = 0

                val wordList = firstHaiku.text.toString().plus(secondHaiku.text.toString())
                    .plus(thirdHaiku.text.toString())

                for (i in 0 until haikuList.size) {
                    if (wordList.contains(haikuList[i])) correctCount += 1
                }
                haikuConfirmNotice.text =
                    if (correctCount >= 3) "定義した単語全部使いました。" else "定義した単語を全部使ってください。"
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

        clearButton.setOnClickListener {
            presenter.onClickRefreshButton()
        }

        saveButton.setOnClickListener {
            presenter.onClickSaveButton()
        }
    }

    override fun refresh() {
        setHaikuWord(parsingToJson())
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

    override fun showSaveDialog() {
        AlertDialog.Builder(requireActivity())
            .setTitle("save하겠습니까")
            .setMessage("Save된 하이쿠는 메뉴에서 확인하실수있습니다.")
            .setPositiveButton("OK") { _, _ ->
                val wordList = listOf(
                    firstHaiku.text.toString(),
                    secondHaiku.text.toString(),
                    thirdHaiku.text.toString()
                )
                presenter.saveHaiku(wordList, haikuList, correctCount)
            }
            .setNegativeButton("NO") { _, _ -> }
            .show()
    }

    override fun completeSaveHaiku(isSaveHaiku: Boolean) {
        if (isSaveHaiku) AlertDialog.Builder(requireActivity())
            .setMessage("Haiku가 저장되었습니다. 메뉴 탭에서 확인해주세요")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }

    private fun parsingToJson(jsonFileName: String = "HaikuWordList.json"): MutableList<String> {

        val jsonWordList =
            JsonResourcesUtils().getJsonWordList(requireContext(), jsonFileName)
        val haikuArray = mutableListOf<String>()
        try {
            val obj = JSONObject(jsonWordList)
            val jsonArray = obj.getJSONArray("haikuWord")
            while (haikuArray.size < 3) {
                val randomInt = (0 until jsonArray.length()).random()
                if (!haikuArray.contains(jsonArray[randomInt])) haikuArray.add(jsonArray[randomInt].toString())
            }
        } catch (e: IOException) {
            Timber.e("get json error")
        }
        return haikuArray
    }

    private fun setHaikuWord(randomHaikuList: MutableList<String>) {
        if (randomHaikuList.isNotEmpty()) {
            haikuList.clear()
            haikuList = randomHaikuList
            haikuList.also {
                firstWord.text = it[0]
                secondWord.text = it[1]
                thirdWord.text = it[2]
            }
        }
    }
}