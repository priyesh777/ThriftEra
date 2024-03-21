package com.example.thriftera.fragments.loginRegister

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.thriftera.R

public class IntroductionFragmentDirections private constructor() {
  public companion object {
    public fun actionIntroductionFragmentToAccountOptionsFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_introductionFragment_to_accountOptionsFragment)
  }
}
