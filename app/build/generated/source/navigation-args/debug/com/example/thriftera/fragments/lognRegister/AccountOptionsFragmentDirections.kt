package com.example.thriftera.fragments.lognRegister

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.thriftera.R

public class AccountOptionsFragmentDirections private constructor() {
  public companion object {
    public fun actionAccountOptionsFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_accountOptionsFragment_to_loginFragment)

    public fun actionAccountOptionsFragmentToRegisterFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_accountOptionsFragment_to_registerFragment)
  }
}
