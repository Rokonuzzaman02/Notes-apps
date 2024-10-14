package com.example.notes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.notes.databinding.FragmentAddnoteBinding


class addnote_Fragment : Fragment() {
    lateinit var binding: FragmentAddnoteBinding

    var  showtime:String?= null
     var  showdate:String?= null

lateinit var databes :roomdata

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentAddnoteBinding.inflate(inflater,container,false)

        databes = Room.databaseBuilder(requireActivity(),roomdata::class.java,"note-db")
            .allowMainThreadQueries().build()

        binding.deatbt.setOnClickListener {

            pickadeta()

        }
        binding.tiamtbt.setOnClickListener {
            pickatime()
        }

        binding.submitboton.setOnClickListener {
            val titelstr = binding.texttll.text.toString()
            val timestr = showtime ?:"00:00"
            val detastr = showdate ?:"00/00/00"

            val note = nootee(title = titelstr, time = timestr, date = detastr)

            databes.getnotedow().insertdata(note)

            findNavController().navigate(R.id.action_addnote_Fragment_to_home_Fragment)

        }



        return binding.root
    }

    private fun pickatime() {

        val calender = Calendar.getInstance()
        val minute = calender.get(Calendar.MINUTE)
        val hour = calender.get(Calendar.HOUR_OF_DAY)

       val timepiker = TimePickerDialog(requireContext(),TimePickerDialog.OnTimeSetListener { view, hourOfday, minute ->

             showtime =String.format("%02d:%02d",hourOfday,minute)

            binding.tiamtbt.text = showtime

        },hour,minute,false
        )
        timepiker.show()
    }

    private fun pickadeta() {
        val calender = Calendar.getInstance()

        val day = calender.get(Calendar.DAY_OF_MONTH)
        val month = calender.get(Calendar.MONTH)
        val year = calender.get(Calendar.YEAR)

        val showdatepiker = DatePickerDialog(
            requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth, ->

                showdate = "$dayOfMonth/$month/$year"
                binding.deatbt.text = showdate


            }, year, month, day
        )

        showdatepiker.show()

    }
}