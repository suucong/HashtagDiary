package com.android.hashtagdiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SettingFragment : Fragment() {
    lateinit var nickName : EditText
    lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        nickName = view.findViewById(R.id.nickName)
        saveButton = view.findViewById(R.id.saveButton__)

        loadData()

        saveButton.setOnClickListener {
            saveData(nickName.text.toString())
            Toast.makeText(requireContext(), "닉네임이 저장되었습니다.", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun saveData(nickname: String) {
        var pref = requireActivity().getPreferences(0)
        var editor = pref.edit()

        editor.putString("KEY_NAME", nickName.text.toString()).apply()
    }

    private fun loadData() {
        var pref = requireActivity().getPreferences(0)
        var name = pref.getString("KEY_NAME", "")

        if(name != null) {
            nickName.setText(name.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SettingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}