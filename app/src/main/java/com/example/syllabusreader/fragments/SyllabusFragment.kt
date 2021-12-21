package com.example.syllabusreader.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.syllabusreader.databinding.FragmentSyllabusBinding
import com.example.syllabusreader.model.Syllabus

class SyllabusFragment : Fragment() {

    private var _binding: FragmentSyllabusBinding? = null
    private val binding get() = _binding!!

    private val args: SyllabusFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSyllabusBinding.inflate(inflater, container, false)

        val syllabus: Syllabus = args.syllabusData
        binding.syllabusTitle.text = syllabus.title
        binding.syllabusBody.text = syllabus.body

        return binding.root
    }

}