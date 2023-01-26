package com.android.hashtagdiary.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.hashtagdiary.R
import com.android.hashtagdiary.RecordActivity
import com.android.hashtagdiary.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var btnRecord : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        btnRecord = view.findViewById(R.id.btnRecord)

        // RecordActivity 화면으로 전환
        btnRecord.setOnClickListener {
            var intentRecord = Intent(getActivity(), RecordActivity::class.java)
            startActivity(intentRecord)
        }

        return view

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}