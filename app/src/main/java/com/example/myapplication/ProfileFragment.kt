package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    // View Binding ფრაგმენტისთვის
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    // SharedPreferences ბაზისთვის
    private lateinit var sharedPreferences: SharedPreferences

    // ცვლადი რედაქტირების რეჟიმის თრექინგისთვის
    private var isEditMode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ინიციალიზაცია SharedPreferences (ბაზა, რომელსაც ქვია "UserProfileData")
        sharedPreferences = requireActivity().getSharedPreferences("UserProfileData", Context.MODE_PRIVATE)

        // ბაზიდან ძველი მონაცემების წაკითხვა და ველებში ჩაწერა
        loadUserData()

        // "შეცვლა / შენახვა" ღილაკზე კლიკი
        binding.btnEditSave.setOnClickListener {
            if (!isEditMode) {
                // თუ რედაქტირების რეჟიმში არ ვართ, გადავიყვანოთ რედაქტირებაზე
                enableEditing(true)
                binding.btnEditSave.text = "შენახვა"
                isEditMode = true
            } else {
                // თუ რედაქტირების რეჟიმში ვართ, შევინახოთ მონაცემები ბაზაში
                saveUserData()
                enableEditing(false)
                binding.btnEditSave.text = "შეცვლა"
                isEditMode = false
            }
        }
    }

    // ველების ჩაკეტვა ან გააქტიურება
    private fun enableEditing(enable: Boolean) {
        binding.etFirstName.isEnabled = enable
        binding.etLastName.isEnabled = enable
        binding.etMobile.isEnabled = enable
        binding.etLocation.isEnabled = enable
    }

    // მონაცემების შენახვა SharedPreferences-ში
    private fun saveUserData() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val mobile = binding.etMobile.text.toString().trim()
        val location = binding.etLocation.text.toString().trim()

        val editor = sharedPreferences.edit()
        editor.putString("KEY_FIRST_NAME", firstName)
        editor.putString("KEY_LAST_NAME", lastName)
        editor.putString("KEY_MOBILE", mobile)
        editor.putString("KEY_LOCATION", location)
        editor.apply() // მონაცემები ინახება ასინქრონულად და უსაფრთხოდ
    }

    // მონაცემების წაკითხვა ბაზიდან და ველების შევსება
    private fun loadUserData() {
        val firstName = sharedPreferences.getString("KEY_FIRST_NAME", "")
        val lastName = sharedPreferences.getString("KEY_LAST_NAME", "")
        val mobile = sharedPreferences.getString("KEY_MOBILE", "")
        val location = sharedPreferences.getString("KEY_LOCATION", "")

        binding.etFirstName.setText(firstName)
        binding.etLastName.setText(lastName)
        binding.etMobile.setText(mobile)
        binding.etLocation.setText(location)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Memory leak-ის თავიდან ასაცილებლად ფრაგმენტებში binding-ს ყოველთვის ვასუფთავებთ
        _binding = null
    }
}