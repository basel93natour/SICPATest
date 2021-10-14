package com.basel.nytimesapp

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * this custom empty activity will be used for testing purposes where we need an activity to attach
 * our fragments and since we are using hilt we need to provide an activity with @AndroidEntryPoint
 * annotation
 * 1- we dont want to include this activity in the main application manifest because its only for testing
 * so we will copy/paste our androidmanifest.xml into debug package
 */
@AndroidEntryPoint
class TestHiltActivity :AppCompatActivity(){
}