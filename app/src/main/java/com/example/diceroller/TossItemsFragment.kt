package com.example.diceroller

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.diceroller.databinding.ActivityMainBinding
import com.example.diceroller.databinding.FragmentTossItemsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * # Package com.example.diceroller
 * A simple [Fragment] subclass.
 */
class TossItemsFragment : Fragment() {

    private lateinit var binding: FragmentTossItemsBinding
    private var tossables: MutableList<Tossable> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    /**
     * ## onCreateOptionsMenu
     * Creates the [BottomNavigationView] for the fragment
     * @param menu the [Menu] to be presented in the bottom app bar
     * @param inflater the [Inflater] used to inflate the bottom app bar
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bottom_navigation_items, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTossItemsBinding.inflate(layoutInflater)
        return binding.getRoot()
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
            var separator = " "

            // Determine the separator needed for the roll result
            when (tossable) {
                tossables.last() -> separator = "" // comma delimited
                else -> separator = ", "          // No separator needed for last die
            }

            // Append the roll result with separator to the other results
            rollResults = rollResults.plus(tossable.toss().plus(separator))
        }

        // Set the results or a warning to add tossables to the MainActivity's view
        if (rollResults.isEmpty()) binding.textView.text =
            getString(R.string.requestToAddTossableItems)
        else binding.textView.text = rollResults

        Toast.makeText(requireContext(), "Items tossed!", Toast.LENGTH_SHORT).show()
    }
}