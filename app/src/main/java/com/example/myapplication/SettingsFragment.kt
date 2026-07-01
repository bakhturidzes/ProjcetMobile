package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    // View Binding პარამეტრებისთვის
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // გასვლის ღილაკზე კლიკი
        binding.btnExitApp.setOnClickListener {
            showExitDialog()
        }
    }

    // პატარა დიალოგური ფანჯარა დასტყურისთვის
    private fun showExitDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("აპლიკაციიდან გასვლა")
            .setMessage("ნამდვილად გსურთ პროგრამის დახურვა?")
            .setPositiveButton("დიახ") { _, _ ->
                // ხურავს ყველა აქტივობას და გადის აპლიკაციიდან
                requireActivity().finishAffinity()
            }
            .setNegativeButton("არა") { dialog, _ ->
                dialog.dismiss() // უბრალოდ ხურავს დიალოგს
            }
            .setCancelable(true) // ეკრანზე სხვაგან დაჭერითაც რომ გაუქმდეს
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Memory leak-ის პრევენცია
    }
}