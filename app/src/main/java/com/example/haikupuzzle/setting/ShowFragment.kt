package com.example.haikupuzzle.setting

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.haikupuzzle.R
import com.example.haikupuzzle.data.HaikuModels
import com.example.haikupuzzle.util.MySharedPreferences
import kotlinx.android.synthetic.main.fragment_setting.*

interface ShowFragmentView {
    fun initModel(haikuModels: MutableList<HaikuModels>)
    fun showRemoveMessageDialog(position: Int)
    fun notifyDataChanged(haikuModels: MutableList<HaikuModels>)
}

class ShowFragment : Fragment(), ShowFragmentView {

    companion object {
        fun nweInstance() =
            ShowFragment().apply {
                arguments = Bundle()
            }
    }

    lateinit var presenter: ShowPresenterInt
    private var haikuModels: MutableList<HaikuModels> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ShowPresenter()
        presenter.initData(MySharedPreferences(requireContext()))
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
        recyclerShowView.adapter ?: initAdapter()
    }

    override fun onPause() {
        presenter.dropView()
        super.onPause()
    }

    override fun initModel(haikuModels: MutableList<HaikuModels>) {
        this.haikuModels = haikuModels
    }

    override fun notifyDataChanged(haikuModels: MutableList<HaikuModels>) {
        this.haikuModels.clear()
        this.haikuModels.addAll(haikuModels)
        recyclerShowView.adapter?.notifyDataSetChanged()
    }

    override fun showRemoveMessageDialog(position: Int) {
        AlertDialog.Builder(requireActivity())
            .setTitle(getString(R.string.REMOVE))
            .setMessage(getString(R.string.remove_haiku_model))
            .setNegativeButton(getString(R.string.NO)) { _, _ -> }
            .setPositiveButton(getString(R.string.OK)) { _, _ ->
                presenter.removeHaiku(position)
            }
            .show()
    }

    private fun initAdapter() {
        recyclerShowView.setHasFixedSize(true)
        recyclerShowView.adapter =
            ShowAdapter(requireContext(), haikuModels, object : ShowAdapter.ItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    presenter.onClickRemoveButton(position)
                }
            })
    }
}