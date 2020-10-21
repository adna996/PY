package com.example.pyquizapp

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pyquizapp.databinding.FragmentEndBinding
import kotlinx.android.synthetic.main.fragment_end.*


class EndFragment : Fragment() {
    var corrAns = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentEndBinding>(inflater,
            R.layout.fragment_end,container,false)

        binding.playAgain.setOnClickListener{
                view: View -> view.findNavController().navigate(R.id.action_endFragment_to_choiceFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = EndFragmentArgs.fromBundle(requireArguments())
        corrAns = args.correctAnswers
        Toast.makeText(context, "Correct answers: ${args.correctAnswers}", Toast.LENGTH_LONG).show()
        val s = args.correctAnswers.toString() + "/" + args.numOfQ
        correct.text = s


        if(args.correctAnswers==args.numOfQ)
        {
            messageText.text = getString(R.string.message)
            quesText.visibility = View.INVISIBLE
        }
        else if (args.correctAnswers>0){
            messageText.text = getString(R.string.message2)
        }
        else{
            messageText.text = getString(R.string.message3)
        }
        if(args.jokerUsed)
        {
            jokerUsedText.text = getString(R.string.jokerUsed)
        }
        else{
            jokerUsedText.text = getString(R.string.jokerNotUsed)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    // Intent za share
    private fun getShareIntent() : Intent {

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, "I answered $corrAns on my byePY quiz app")
        return shareIntent
    }

    // start activity za intent
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.end_menu, menu)

        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            // sakrij ako nije resolv-ana
            menu.findItem(R.id.share)?.setVisible(false)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }



}
