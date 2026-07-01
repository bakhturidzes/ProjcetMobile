package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentMobilesBinding

class MobilesFragment : Fragment() {

    // View Binding მობილურების ფრაგმენტისთვის
    private var _binding: FragmentMobilesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMobilesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // დიდი უკან დაბრუნების ღილაკის ლოგიკა
        binding.btnBack.setOnClickListener {
            // აბრუნებს მომხმარებელს წინა ფრაგმენტზე (BackStack-იდან იღებს)
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // მეხსიერების გასუფთავება
    }
}