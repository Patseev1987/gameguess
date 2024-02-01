package com.example.gamesdasdasd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.gamesdasdasd.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    var _binding: FragmentResultBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = ResultViewModelFactory(ResultFragmentArgs.fromBundle(requireArguments()).result)
        val viewModel = ViewModelProvider(this, viewModelFactory)[ResultViewModel::class.java]
        binding.wonLost.text = viewModel.result

        binding.newGameButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_resultFragment_to_gameFragment)
        }



        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}