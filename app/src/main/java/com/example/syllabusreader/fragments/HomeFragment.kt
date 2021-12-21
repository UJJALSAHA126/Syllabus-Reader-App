package com.example.syllabusreader.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.syllabusreader.R
import com.example.syllabusreader.databinding.FragmentHomeBinding
import com.example.syllabusreader.model.Syllabus
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var courseList: ArrayList<String>
    private lateinit var streamList: ArrayList<String>
    private lateinit var semesterList: ArrayList<String>

    private lateinit var courseAdapter: ArrayAdapter<String>
    private lateinit var streamAdapter: ArrayAdapter<String>
    private lateinit var semesterAdapter: ArrayAdapter<String>

    lateinit var syllabys: Syllabus

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // INIt Variables
        courseList = ArrayList()
        streamList = ArrayList()
        semesterList = ArrayList()

        changeCourse()
        binding.course.setOnItemClickListener { _, _, _, _ ->
//            Toast.makeText(this, "${binding.course.text} Selected", Toast.LENGTH_SHORT).show()
            val crs = binding.course.text.toString()
            changeStream(crs)
            changeSemester(crs)
        }
//
        binding.showButton.setOnClickListener {
            val crs = binding.course.text.toString()
            val strm = binding.stream.text.toString()
            val sem = binding.semester.text.toString()

            val fileName = "$crs/$strm/$sem.txt"
            val title = "$crs $strm $sem"
            val body = getTextFromTXT(fileName)

            body?.let {
                syllabys = Syllabus(title, it)
                val action = HomeFragmentDirections.homeToSyllabusFragment(syllabys)
                findNavController().navigate(action)
            }

        }
        return binding.root
    }

    private fun getTextFromTXT(name: String): String? {
        var text = ""
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(InputStreamReader(requireContext().assets.open(name)))
            text = reader.readLines().joinToString("\n")
//
        } catch (e: Exception) {
            Log.d("Reader", "Error In Loading")
            Toast.makeText(requireContext(), "Sorry This Syllabus Is Not Yet Available", Toast.LENGTH_SHORT).show()
            return null
        } finally {
            try {
                reader?.close()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error Closing", Toast.LENGTH_SHORT).show()
            }
        }
        return text
    }

    override fun onResume() {
        super.onResume()
        changeCourse()
    }

    private fun changeCourse() {
        courseList.clear()
        courseList.add("BTech")
        courseList.add("BSc")
        courseList.add("BPharma")
        courseList.add("BA")
        courseAdapter = ArrayAdapter(requireContext(), R.layout.custom_dropdown_menu, courseList)

        "BTech".also { binding.course.setText(it) }
        binding.course.setAdapter(courseAdapter)
        changeStream("BTech")
        changeSemester("BTech")
    }

    private fun changeStream(course: String) {
//        Toast.makeText(this, "Course = $course", Toast.LENGTH_SHORT).show()
        streamList.clear()
        when (course) {

            "BTech" -> {
                streamList.add("CSE")
                streamList.add("ECE")
                streamList.add("EE")
                streamList.add("ME")
            }

            "BPharma" -> {
                streamList.add("Pharma")
            }

            "BSc" -> {
                streamList.add("MATH")
                streamList.add("PHY")
                streamList.add("CHEM")
                streamList.add("BIO")
            }

            "BA" -> {
                streamList.add("BEN")
                streamList.add("ENG")
                streamList.add("HIS")
                streamList.add("GEO")
            }
        }
        (streamList[0]).also { binding.stream.setText(it) }

        streamAdapter = ArrayAdapter(requireContext(), R.layout.custom_dropdown_menu, streamList)
        binding.stream.setAdapter(streamAdapter)
    }

    private fun changeSemester(course: String) {
        semesterList.clear()
        val count = when (course) {
            "BTech", "BPharma" -> 8
            else -> 6
        }
//        Toast.makeText(this, "Semester Number = $count", Toast.LENGTH_SHORT).show()
        for (i in 1..count) {
            val sem = "Semester $i"
            semesterList.add(sem)
        }

        if (binding.semester.text.toString().takeLast(1).toInt() > count)
            ("Semester $count").also { binding.semester.setText(it) }

        semesterAdapter =
            ArrayAdapter(requireContext(), R.layout.custom_dropdown_menu, semesterList)
        binding.semester.setAdapter(semesterAdapter)
    }

}