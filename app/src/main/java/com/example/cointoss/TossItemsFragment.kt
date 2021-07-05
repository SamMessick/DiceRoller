package com.example.cointoss

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cointoss.R
import cointoss.databinding.FragmentTossItemsBinding


/**
 * # Package com.example.cointoss
 * A simple [Fragment] subclass.
 */
class TossItemsFragment : Fragment() {

    private lateinit var binding: FragmentTossItemsBinding
    private var tossables: MutableList<Tossable> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTossItemsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toss available items when the toss button is pressed
        binding.tossButton.setOnClickListener { tossItems() }

        // Add or remove coins to toss when the add and remove coin buttons are pressed
        binding.addCoinButton.setOnClickListener { tossables.add(Coin()) }
        binding.removeCoinButton.setOnClickListener { tossables.remove(tossables.findLast { it is Coin }) }

        // Add or remove dice to toss when the add and remove die buttons are pressed
        binding.addDieButton.setOnClickListener { tossables.add(Die()) }
        binding.removeDieButton.setOnClickListener { tossables.remove(tossables.findLast { it is Die }) }
    }

    /**
     * Rolls dice initialized at rendering
     */
    private fun tossItems() {
        var rollResults = ""

        // Roll the dice; creates a roll result string in rollResults
        for (tossable in tossables) {

            // Determine the separator needed for the roll result
            val separator: String = when (tossable) {
                tossables.last() -> "" // comma delimited
                else -> ", "          // No separator needed for last die
            }

            // Append the roll result with separator to the other results
            rollResults = rollResults.plus(tossable.toss().plus(separator))
        }

        // Set the results or a warning to add tossables to the MainActivity's view
        if (rollResults.isEmpty()) binding.resultTextView.text =
            getString(R.string.requestToAddTossableItems)
        else binding.resultTextView.text = rollResults
    }
}