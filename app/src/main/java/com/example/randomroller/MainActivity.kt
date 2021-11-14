package com.example.randomroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.randomroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var participantList = mutableListOf<Participant>()
    var winnerParticipant = Participant(fullName = "", dscName = "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rollbutton.setOnClickListener { rollTheWinner() }
        binding.addparticipantbutton.setOnClickListener { addParticipantToList() }
    }

    fun addParticipantToList() {

        var fullNameString = binding.FullNameSpace.text.toString()
        var dscNameString = binding.DscNameSpace.text.toString()

        if(TextUtils.isEmpty(fullNameString) || TextUtils.isEmpty(dscNameString)) {
            binding.infoText.text = "Enter More information"
        }
        else {
            val participant = Participant(fullNameString, dscNameString)
            participantList.add(participant)
            binding.infoText.text = "${participant.fullName} added to list."
        }

        binding.FullNameSpace.text.clear()
        binding.DscNameSpace.text.clear()
    }

    fun rollTheWinner() {
        if(participantList.count() == 0) {
            binding.infoText.text = "Add more participants before rolling."
        }
        else {
            winnerParticipant = participantList.random()
            binding.winnerfullnametextview.text = winnerParticipant.fullName
            binding.winnerdscnametextview.text = winnerParticipant.dscName
        }
    }

}

class Participant(val fullName: String, val dscName: String) {

}