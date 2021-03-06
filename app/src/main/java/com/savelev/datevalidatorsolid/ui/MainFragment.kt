package com.savelev.datevalidatorsolid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.savelev.datevalidatorsolid.data.DateSharedPreferenceStorage
import com.savelev.datevalidatorsolid.databinding.MainFragmentBinding
import pro.azhidkov.solid.date.use_cases.save_date.SaveDateInteractor
import pro.azhidkov.solid.date.view.DatePresenter
import pro.azhidkov.solid.date.view.DateView
import pro.azhidkov.solid.date.view.FeedbackViewModel
import pro.azhidkov.solid.date.view.SaveResultPresenter
import pro.azhidkov.solid.date.view.init_date.InitDateController
import pro.azhidkov.solid.date.view.save_date.SaveDateClicked
import pro.azhidkov.solid.date.view.save_date.SaveDateController

class MainFragment : Fragment(), DateView {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var saveDateController: SaveDateController
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initConfigDI()

        binding.saveDate.setOnClickListener {
            try {
                val saveDateClicked = SaveDateClicked(day, month, year)
                saveDateController.onSaveClicked(saveDateClicked)
            } catch (e: NumberFormatException) {
                Toast.makeText(requireContext(), "NumberFormatException!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun initConfigDI() {

        val dateStorage = DateSharedPreferenceStorage(requireActivity())

        val datePresenter = DatePresenter(this)

        val initDateController = InitDateController(dateStorage::loadDate, datePresenter)

        val saveDateInteractor = SaveDateInteractor(dateStorage)

        val saveResultPresenter = SaveResultPresenter(this)

        saveDateController = SaveDateController(saveDateInteractor, saveResultPresenter)

        initDateController.initDate()
    }

    override var day: String
        get() = binding.inputDay.text.toString()
        set(day) {
            binding.inputDay.setText(day)
        }
    override var month: String
        get() = binding.inputMonth.text.toString()
        set(month) {
            binding.inputMonth.setText(month)
        }
    override var year: String
        get() = binding.inputYear.text.toString()
        set(year) {
            binding.inputYear.setText(year)
        }

    override fun showSaveFeedback(feedbackViewModel: FeedbackViewModel) {
        Toast.makeText(requireContext(), feedbackViewModel.text, Toast.LENGTH_LONG).show()
    }
}