package br.com.githubandroid.presentation.utils

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope



/**
 * Created by pedrohenrique on 19/08/17.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class FragmentScoped