package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("MYTAG", "Fragment2 : OnAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MYTAG", "Fragment2 : OnCreate")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i("MYTAG", "Fragment2 : OnCreateView")
        return inflater.inflate(R.layout.fragment_my2, container, false)
    }


    override fun onStart() {
        super.onStart()
        Log.i("MYTAG", "Fragment2 : OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MYTAG", "Fragment2 : OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MYTAG", "Fragment2 : OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MYTAG", "Fragment2 : OnStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("MYTAG", "Fragment2 : OnDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MYTAG", "Fragment2 : OnDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("MYTAG", "Fragment2 : OnDetach")
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}