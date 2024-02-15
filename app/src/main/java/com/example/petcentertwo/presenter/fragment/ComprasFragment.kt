package com.example.petcentertwo.presenter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.petcentertwo.databinding.FragmentComprasBinding

class ComprasFragment : Fragment() {
    private var _binding: FragmentComprasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentComprasBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}