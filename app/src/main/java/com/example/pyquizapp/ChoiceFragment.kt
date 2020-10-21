package com.example.pyquizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.pyquizapp.databinding.FragmentChoiceBinding
import kotlinx.android.synthetic.main.fragment_choice.*


class ChoiceFragment : Fragment() {
    private var numOfQuestions = 5
    private var levelD = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentChoiceBinding>(
            inflater, R.layout.fragment_choice, container, false)

        binding.easyButton.setOnClickListener(){
            easyButton.setBackgroundResource(R.drawable.rounded_corners2)
            mediumButton.setBackgroundResource(R.drawable.rounded_corners)
            hardButton.setBackgroundResource(R.drawable.rounded_corners)
        }
        binding.mediumButton.setOnClickListener(){
            mediumButton.setBackgroundResource(R.drawable.rounded_corners2)
            hardButton.setBackgroundResource(R.drawable.rounded_corners)
            easyButton.setBackgroundResource(R.drawable.rounded_corners)
        }
        binding.hardButton.setOnClickListener(){
            hardButton.setBackgroundResource(R.drawable.rounded_corners2)
            easyButton.setBackgroundResource(R.drawable.rounded_corners)
            mediumButton.setBackgroundResource(R.drawable.rounded_corners)
        }

        binding.okButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->

            val level = binding.levelRadioGroup.checkedRadioButtonId

            when(level){

                R.id.easyButton -> levelD = 1
                R.id.mediumButton -> levelD = 2
                R.id.hardButton -> levelD = 3
            }
            numOfQuestions = numOfQ.text.toString().toInt()
            view.findNavController().navigate(ChoiceFragmentDirections
                .actionChoiceFragmentToGameFragment(numOfQuestions, levelD))

        }


        return binding.root
    }


}


