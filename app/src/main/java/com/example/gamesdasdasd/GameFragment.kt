package com.example.gamesdasdasd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.gamesdasdasd.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    var _binding: FragmentGameBinding? = null
    val binding get() = _binding!!

    lateinit var viewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, conatainer: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, conatainer, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        updateScreen()
        binding.guessButton.setOnClickListener {
           viewModel. makeGuess(binding.guess.text.toString().uppercase())
            binding.guess.text = null
            updateScreen()
            if (viewModel.isWon() || viewModel.isLost()) {
                val action = GameFragmentDirections
                    .actionGameFragmentToResultFragment(viewModel.wonLostMessage())
                view.findNavController().navigate(action)
            }
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateScreen() {
        binding.word.text = viewModel.secretWordDisplay
        binding.lives.text = "You have ${viewModel.livesLeft} lives left."
        binding.incorrectGuesses.text = "Incorrect guesses: ${viewModel.incorrectGuesses}"
    }




}