package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    // View Binding ფრაგმენტისთვის
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 🟢 გასწორდა: მოშორდა ზედმეტი ".com"
        super.onViewCreated(view, savedInstanceState)

        // პირველი კითხვის ჩამოშლა/დამალვა კლიკზე
        binding.layoutFaq1.setOnClickListener {
            toggleLayout(binding.tvAnswer1)
        }

        // მეორე კითხვის ჩამოშლა/დამალვა კლიკზე
        binding.layoutFaq2.setOnClickListener {
            toggleLayout(binding.tvAnswer2)
        }
    }

    // ფუნქცია, რომელიც მართავს FAQ პასუხების გამოჩენა/დამალვას
    private fun toggleLayout(view: View) {
        if (view.visibility == View.VISIBLE) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // მეხსიერების გაჟონვის (Memory Leak) პრევენციისთვის
        _binding = null
    }
}