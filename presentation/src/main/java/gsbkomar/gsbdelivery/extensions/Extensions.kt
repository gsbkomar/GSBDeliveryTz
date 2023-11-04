package gsbkomar.gsbdelivery.extensions

import androidx.fragment.app.Fragment
import gsbkomar.gsbdelivery.R

fun Fragment.loadFragment(fragment: Fragment) {
    this.requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.container, fragment)
        .commit()
}