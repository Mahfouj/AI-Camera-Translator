package com.example.aicameratranslator.presentation.history

 import android.os.Bundle
 import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import androidx.fragment.app.viewModels
 import androidx.recyclerview.widget.LinearLayoutManager
 import com.example.aicameratranslator.R
 import com.example.aicameratranslator.databinding.FragmentHistoryBinding
 import dagger.hilt.android.AndroidEntryPoint


 @AndroidEntryPoint
 class HistoryFragment : Fragment() {



    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val vm: HistoryViewModel by viewModels()
    private lateinit var adapter: HistoryAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       _binding = FragmentHistoryBinding.inflate(inflater,container,false)

        return binding.root
    }


     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       adapter = HistoryAdapter{item-> vm.delete(item)}

     binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())

     binding.historyRecyclerView.adapter= adapter

         vm.history.observe(viewLifecycleOwner){ list->
             adapter.submitList(list)
         }
         vm.lodeHistory()
     }

     override fun onDestroyView() {
         super.onDestroyView()

     _binding = null
     }


}