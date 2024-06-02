package com.feroxdev.closetApp.ui.fragments.head

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.feroxdev.closetApp.databinding.FragmentHeadBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HeadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeadFragment : Fragment() {

    private var binding: FragmentHeadBinding? = null
    private val unbinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHeadBinding.inflate(inflater, container, false)
        return unbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Ahora puedes usar el binding para acceder a las vistas

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}