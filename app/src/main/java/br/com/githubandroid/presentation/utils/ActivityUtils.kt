package br.com.githubandroid.presentation.utils

import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by pedrohenrique on 19/08/17.
 */

object ActivityUtils {

    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.

     */
    fun addFragmentToActivity(@NonNull fragmentManager: FragmentManager,
                              @NonNull fragment: Fragment, frameId: Int) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }

    fun addFragmentToActivity(@NonNull fragmentManager: FragmentManager,
                              @NonNull fragment: Fragment, frameId: Int, tag: String) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment, tag)
        transaction.commit()
    }

}