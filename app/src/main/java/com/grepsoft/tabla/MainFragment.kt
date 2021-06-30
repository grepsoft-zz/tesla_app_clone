package com.grepsoft.tabla

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.grepsoft.tabla.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding

    private val list : List<MenuItem> = listOf<MenuItem>(
        MenuItem(id = 1, title = "Media", icon =  R.mipmap.ic_media_64),
        MenuItem(id = 2, title = "Controls", icon = R.drawable.ic_car),
        MenuItem(id = 3, title = "Charging", icon = R.mipmap.ic_charge_64),
        MenuItem(id = 4, title = "Location", icon = R.drawable.ic_location),
        MenuItem(id = 5, title = "Summon", icon = R.mipmap.ic_steering_wheel_64),
        MenuItem(id = 6, title = "Upgrades", icon = R.drawable.ic_shopping),
        MenuItem(id = 6, title = "Service", icon = R.mipmap.ic_wrench_64),
        MenuItem(id = 6, title = "Roadside assistance", icon = R.drawable.ic_warning),

        )

   override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

       var isViewDim = false

        binding.apply {

            val transition = vehicleContainer.background as TransitionDrawable
            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{ _, vOffset ->
                val offset = vOffset * -1;

                val shouldDimView = offset > (toolbar.height + toolbar.height * 1.20f)
                //Log.d("TESLA", "$offset ${toolbar.height + toolbar.height * 1.20f}")
                if(isViewDim != shouldDimView) {
                    isViewDim = shouldDimView

                    if(shouldDimView) {
                        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)
                        topHeader.startAnimation(anim)
                        toolbar.elevation = 4.0f;
                        anim.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(animation: Animation?){}
                            override fun onAnimationRepeat(animation: Animation?) {}
                            override fun onAnimationEnd(animation: Animation?) {topHeader.alpha = 0.0f}
                        })

                        transition.startTransition(500)

                    } else {
                        toolbar.elevation = 0.0f;
                        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
                        topHeader.startAnimation(anim)
                        anim.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(animation: Animation?){}
                            override fun onAnimationRepeat(animation: Animation?) {}
                            override fun onAnimationEnd(animation: Animation?) {topHeader.alpha = 1.0f}
                        })
                        transition.reverseTransition(500)
                    }
                }
            })
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MainMenuAdapter(requireContext(), list)
        binding.mainMenuList.adapter = adapter
    }

}