package com.android.leivacourse.artapp.ui.artgallery.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.leivacourse.artapp.R
import com.android.leivacourse.artapp.ui.artgallery.fav.FavoriteFragment

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object{
        val TAG = "ProfileFragment:TAG"
        fun newInstance() = ProfileFragment()

    }


}
