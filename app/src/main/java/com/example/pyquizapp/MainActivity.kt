package com.example.pyquizapp

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.pyquizapp.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var langBtn : Button
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        loadLocate()
        super.onStart()

    }



   /*override fun onStart() {
        loadLocate()
        langBtn = findViewById(R.id.languageButton)
        langBtn.setOnClickListener{
            changeLang()
        }
        super.onStart()
    }

    override fun onResume() {
        loadLocate()
        langBtn = findViewById(R.id.languageButton)
        langBtn.setOnClickListener{
            changeLang()
        }
        super.onResume()
    }*/

     private fun changeLang() {
        val en: String = getString(R.string.english)
        val bs: String = getString(R.string.bosnian)
        val listitems = arrayOf(en, bs)

        val mBuilder = AlertDialog.Builder(this@MainActivity)
        val string: String = getString(R.string.chooseLang)
        mBuilder.setTitle(string)
        mBuilder.setSingleChoiceItems(listitems, -1){
                dialog, which ->
            if(which==0) {
                setLocate("en")
                recreate()
            }
            else if(which==1){
                setLocate("bs")
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()

    }
    private fun setLocate(Lang: String) {
        val locale =  Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_lang", Lang)
        editor.apply()
    }
     private fun loadLocate(){
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_lang", "")
        if (language != null) {
            setLocate(language)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    /*fun showPopUp(view: View) {
        val popupMenu = PopupMenu(this, view, Gravity.NO_GRAVITY, R.attr.actionOverflowMenuStyle, 0)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.header_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.header1 -> {
                    Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show();
                    //setContentView(R.layout.fragment_game)
                }
                R.id.header2 -> {
                    Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show();
                }
                R.id.header3 -> {
                    Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show();
                }
            }
            true
        }
    }*/

}
