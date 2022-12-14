package com.mauriciolomba.sharedpreferencesexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(firstTime()){
            showWelcomeDialog()

        }
    }

    private fun updateFirstTime() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()){
            putBoolean(KEY_FIRST_TIME_APP, false)
            apply()
        }
    }

    private fun showWelcomeDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage(R.string.dialog_title).setTitle(R.string.dialog_message)
            setPositiveButton(android.R.string.ok) { _, _ -> updateFirstTime() }
            setCancelable(false)
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun firstTime(): Boolean {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getBoolean(KEY_FIRST_TIME_APP, true)
    }

    private companion object {
        private const val KEY_FIRST_TIME_APP = "KEY_FIRST_TIME_APP"
    }
}