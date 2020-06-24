package com.abunayla.sharedprefsdatasave

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

private const val PREF_NAME = "pref_save_001"
private const val key_name = "name"
private const val key_email = "key"
private const val key_switch = "switch"
private const val key_cbox = "cbox"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        btn_save.setOnClickListener {

            val name = et_personName.text.toString()

            val email = et_Email.text.toString()

            val cbox = cb_checkBox.isChecked

            val switch = sw_switch.isChecked

            editor.apply {
                if (name.isNotEmpty() && name.isNotBlank()) {
                    putString(key_name, name)
                } else {
                    Toast.makeText(
                        applicationContext, "Please enter a name",
                        Toast.LENGTH_LONG
                    ).show()
                }

                if (email.isNotEmpty() && email.isNotBlank()) {
                    putString(key_email, email)
                } else {
                    Toast.makeText(
                        applicationContext, "Please enter a email",
                        Toast.LENGTH_LONG
                    ).show()
                }

                putBoolean(key_cbox, cbox)
                putBoolean("switch", switch)

                // apply is async process and does'nt block the main thread thus
                // it is more suitable than commit() in this scenario.
                apply()
            }


        }//btn_save.setOnClickListener

        btn_load.setOnClickListener{
            et_personName.setText(sharedPref.getString(key_name, "name"))
            et_Email.setText(sharedPref.getString(key_email,"email"))
            cb_checkBox.isChecked = sharedPref.getBoolean(key_cbox, false)
            sw_switch.isChecked = sharedPref.getBoolean(key_switch, true)
        }

    }
}