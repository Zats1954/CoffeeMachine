package ru.zatsoft.coffeeMachine

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.zatsoft.coffeeMachine.databinding.ActivityPrepareBinding

class Prepare : AppCompatActivity() {
    private lateinit var binding : ActivityPrepareBinding
    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrepareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val coffee = intent.extras?.get("coffee") as Coffee
        toolBar = binding.toolbarMain
        setSupportActionBar(toolBar)
        title = " "

        val str = StringBuffer("")
        GlobalScope.launch(Main) {
            for(i in 10 .. 100 step 10){
                str.append("$i%")
                binding.tvProgress.text = str.toString()
                delay(500L)
               }
              var text = "Ваш кофе готов:\n " +
                      "${coffee.name}\n" +
                      " сахар:${coffee.sugar}\n" +
                      " объем:${coffee.volume}\n" +
                      " "
              if(coffee.name == "Cappuccino" )
                  text +="молоко:${coffee.milk}"
               binding.tvProgress.text = text
        }
    }
}