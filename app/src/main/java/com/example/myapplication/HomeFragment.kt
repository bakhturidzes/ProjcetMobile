package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as? MainActivity

        // 1. კლიკი სისტემურ ბლოკებზე
        binding.cardPcBlocks.setOnClickListener {
            mainActivity?.replaceFragment(PcBlocksFragment(), "pc_blocks")
        }

        // 2. კლიკი აქსესუარებზე
        binding.cardAccessories.setOnClickListener {
            mainActivity?.replaceFragment(AccessoriesFragment(), "accessories")
        }

        // 3. კლიკი სივრცეზე (Workspace)
        binding.cardWorkspace.setOnClickListener {
            // თუ ამ ფრაგმენტის კლასს სხვა სახელი ქვია, ჩაწერე ის
            mainActivity?.replaceFragment(WorkspaceFragment(), "workspace")
        }

        // 4. კლიკი მობილურებზე
        binding.cardMobiles.setOnClickListener {
            mainActivity?.replaceFragment(MobilesFragment(), "mobiles")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}