package com.chris.ejemplo

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.chris.ejemplo.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.tuLlaveFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val bottomBar = findViewById<ConstraintLayout>(R.id.bottomBar)
        navController.addOnDestinationChangedListener { nc: NavController, destination, args ->
            if (destination.id == R.id.nav_home || destination.id == R.id.tuLlaveFragment) {
                bottomBar.visibility = View.VISIBLE
            } else {
                bottomBar.visibility = View.GONE
            }
        }

        val btnInicio = findViewById<Button>(R.id.btnInicio)
        btnInicio.setOnClickListener {
            if (navController.currentDestination?.id != R.id.nav_home) {
            navController.navigate(R.id.nav_home, null)
            }

        }

        val btnLlave = findViewById<Button>(R.id.btnLlave)
        btnLlave.setOnClickListener {

            if (navController.currentDestination?.id != R.id.tuLlaveFragment) {
                navController.navigate(R.id.tuLlaveFragment)
            }


        }




    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}