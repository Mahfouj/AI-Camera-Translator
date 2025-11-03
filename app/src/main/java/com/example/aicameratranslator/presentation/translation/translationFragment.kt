package com.example.aicameratranslator.presentation.translation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.aicameratranslator.data.api.Language
import com.example.aicameratranslator.databinding.FragmentTranslationBinding
import dagger.hilt.android.AndroidEntryPoint

// It's good practice to add the Hilt annotation
@AndroidEntryPoint
class translationFragment : Fragment() {

    private var _binding: FragmentTranslationBinding? = null
    private val binding get() = _binding!!

    private val args: TranslationFragmentArgs by navArgs()

    // Correctly inject the ViewModel
    private val vm: TranslationViewModel by viewModels()

    private var languages: List<Language> = emptyList()
    private var selectedLangCode = "en"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) // Call super

        // put extracted text into input if available
        args.extractedText?.let { binding.inputEditText.setText(it) }

        binding.translateButton.setOnClickListener {
            val text = binding.inputEditText.text.toString().trim()
            if (text.isBlank()) {
                binding.resultTextView.text = "Please enter text"
                return@setOnClickListener
            }
            binding.progressBar.visibility = View.VISIBLE
            vm.translate(text, selectedLangCode)
        }
        vm.loadLanguages() // populate spinner

        vm.languages.observe(viewLifecycleOwner) { list ->
            languages = list
            val display = languages.map { "${it.name} (${it.code})" }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, display)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.languageSpinner.adapter = adapter

            // set default selection to English if present
            val defaultIndex = languages.indexOfFirst { it.code == "en" }.coerceAtLeast(0)
            binding.languageSpinner.setSelection(defaultIndex)
            selectedLangCode = languages.getOrNull(defaultIndex)?.code ?: "en"
        }

        binding.languageSpinner.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedLangCode = languages.getOrNull(position)?.code ?: "en"
            }
            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {}
        })

        // --- CORRECTED OBSERVER ---
        vm.translationResult.observe(viewLifecycleOwner) { resultText ->
            binding.progressBar.visibility = View.GONE
            binding.resultTextView.text = resultText

            // Optional: Show a toast for error messages
            if (resultText.startsWith("Translation Error:")) {
                Toast.makeText(requireContext(), resultText, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
