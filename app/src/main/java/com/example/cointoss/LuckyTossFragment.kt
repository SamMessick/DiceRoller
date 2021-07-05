package com.example.cointoss

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cointoss.R
import cointoss.databinding.FragmentLuckyTossBinding

/**
 * A simple [Fragment] subclass.
 */
class LuckyTossFragment : Fragment() {

    private lateinit var binding: FragmentLuckyTossBinding
    private var tossable: Die = Die(6)
    private var luckyNumber: String = "4"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLuckyTossBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up Die toss button behavior
        binding.tossButton.setOnClickListener {
            // Toss the die
            val rollString: String = tossable.toss()

            // Update the drawable resource based on the roll
            val drawableRes = when (rollString) {
                "1" -> R.drawable.dice_1
                "2" -> R.drawable.dice_2
                "3" -> R.drawable.dice_3
                "4" -> R.drawable.dice_4
                "5" -> R.drawable.dice_5
                "6" -> R.drawable.dice_6
                else -> null
            }

            if (drawableRes == null) { // Hide the drawable if we rolled an illegal number
                binding.dieImageView.visibility = INVISIBLE
            } else {
                binding.dieImageView.apply { // Otherwise, update the resource and contentDescription
                    visibility = VISIBLE
                    setImageResource(drawableRes)
                    contentDescription = rollString
                }
            }

            // Tell the user something about their roll
            binding.userMessageTextView.text = when (rollString) {
                luckyNumber -> "You got the lucky number!"
                "1" -> "Nice try, but you'll get it next time."
                "2" -> "You can do better now"
                "3" -> "Do I have to spell it out to you?"
                "4" -> "Maybe you should try something that you're better at"
                "5" -> "Think again. Think. Again."
                "6" -> "Almost! Keep going. You'll get it."
                else -> "Where did you get this odd die? Use a d6."
            }
        }
    }
}