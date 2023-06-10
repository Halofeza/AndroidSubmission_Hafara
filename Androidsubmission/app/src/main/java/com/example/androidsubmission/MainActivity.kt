package com.example.androidsubmission

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var rv_list_tour: RecyclerView
    private var list = ArrayList<Tour>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
        val satufrag = fragment1()
        val duafrag = fragment2()
        setfragment(satufrag)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> setfragment(satufrag)
                R.id.animation -> setfragment(duafrag)
            }
            true
        }
        //

        rv_list_tour = findViewById(R.id.listRV)
        rv_list_tour.setHasFixedSize(true)

        list.addAll(listTours)
        showRecylerview()
    }
    //
    private fun setfragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.Fragment,fragment)
            commit()
        }
    //
    private val listTours: ArrayList<Tour>
        get() {
            val dataTitle = resources.getStringArray(R.array.data_name)
            val datadesk = resources.getStringArray(R.array.data_description)
            val dataimage = resources.obtainTypedArray(R.array.data_photo)
            val datalist = ArrayList<Tour>()

            for (i in dataTitle.indices){
                val tour = Tour(
                    dataTitle[i],
                    datadesk[i],
                    dataimage.getResourceId(i, -1)
                )
                datalist.add(tour)
            }
            return datalist
        }
    fun showRecylerview(){
        rv_list_tour.layoutManager = LinearLayoutManager(this)
        rv_list_tour.adapter=Touradapter(list)
    }

    fun Onclick(view: View) {
        findViewById<ImageView>(R.id.iconplane).animate().apply {
            duration = 1000
            translationY(-2000f)
        }.start()
        findViewById<TextView>(R.id.tvicon).setText("Broommm")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.actionbarmenu->Toast.makeText(this, "Yeyy bisaa",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}