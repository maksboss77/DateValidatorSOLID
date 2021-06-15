package com.savelev.datevalidatorsolid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.savelev.datevalidatorsolid.databinding.MainFragmentBinding
import pro.azhidkov.solid.date.view.DateView
import pro.azhidkov.solid.date.view.FeedbackViewModel
import pro.azhidkov.solid.date.view.save_date.SaveDateClicked

class MainFragment : Fragment(), DateView {

    companion object {
        fun newInstance() = MainFragment()
    }

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

        //TODO: презентер, который подключается в View
        initConfigDI()

        binding.saveDate.setOnClickListener {
            saveDate()
            saveDate()
        }
    }

    private fun initConfigDI() {
        val saveDateClicked = SaveDateClicked(day, month, year)
        //createEvent()
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