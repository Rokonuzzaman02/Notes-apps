package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.notes.databinding.FragmentHomeBinding

class home_Fragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var databes :roomdata


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)

        databes = Room.databaseBuilder(requireActivity(),roomdata::class.java,"note-db")
            .allowMainThreadQueries().build()

        var notes: List<nootee> = databes.getnotedow().getalldata()

        var adapter = NoteAdapter()
        adapter.submitList(notes)

        binding.reycel.adapter = adapter

        databes.getnotedow().getalldata().forEach{
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }

        binding.addbt.setOnClickListener {

            findNavController().navigate(R.id.action_home_Fragment_to_addnote_Fragment)
        }

        return binding.root
    }

   }