package ru.zatsoft.coffeeMachine

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ru.zatsoft.coffeeMachine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var milk = ""
    private var sugar = ""
    private var volume = ""
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolBar: Toolbar
    private var coffee = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolBar = binding.toolbarMain
        setSupportActionBar(toolBar)
        title = " "

        binding.rgCoffee.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                if (checkedId == binding.rbCappuccino.id) {
                    binding.tvMilk.visibility = View.VISIBLE
                    binding.rgMilk.visibility = View.VISIBLE
                } else {
                    binding.tvMilk.visibility = View.GONE
                    binding.rgMilk.visibility = View.GONE
                }
                when (findViewById<RadioButton>(checkedId).text) {
                    "Americano" -> coffee = "Americano"
                    "Cappuccino" -> coffee = "Cappuccino"
                    "Latte" -> coffee = "Latte"
                    "Expresso" -> coffee = "Expresso"
                    else -> {
                        println("Error in choise coffee")
                        System.exit(1)
                    }
                }
            }
        }

        binding.rgVolume.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                when (findViewById<RadioButton>(checkedId)) {
                    binding.rb100 -> volume = "0.1 л"
                    binding.rb200 -> volume = "0.2 л"
                    else -> {
                        println("Error in choise volume")
                        System.exit(1)
                    }
                }
            }
        }

        binding.rgSugar.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                when (findViewById<RadioButton>(checkedId)) {
                    binding.rb5gr -> sugar = "5 гр"
                    binding.rb10gr -> sugar = "10 гр"
                    binding.rbEmpty -> sugar = "Без сахара"
                    else -> {
                        println("Error in choise sugar")
                        System.exit(1)
                    }
                }
            }
        }

        binding.rgMilk.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                when (findViewById<RadioButton>(checkedId)) {
                    binding.rb10ml -> milk = "10 мл"
                    binding.rb20ml -> milk = "20 мл"
                    else -> {
                        println("Error in choise milk $checkedId")
                        System.exit(1)
                    }
                }
            }
        }

        binding.btnMake.setOnClickListener {
            var isReady = true
            if (binding.rgCoffee.checkedRadioButtonId == -1) {
                coffee = ""
                isReady = false
                Toast.makeText(this, "Не выбрано кофе", Toast.LENGTH_LONG).show()
            } else {
                coffee =
                    findViewById<RadioButton>(binding.rgCoffee.checkedRadioButtonId).text.toString()
                if (binding.rgVolume.checkedRadioButtonId == -1) {
                    volume = ""
                    isReady = false
                    Toast.makeText(this, "Не выбран размер чашки", Toast.LENGTH_LONG).show()
                } else {
                    volume =
                        findViewById<RadioButton>(binding.rgVolume.checkedRadioButtonId).text.toString()

                    if (binding.rgSugar.checkedRadioButtonId == -1) {
                        sugar = ""
                        isReady = false
                        Toast.makeText(this, "Не выбран сахар", Toast.LENGTH_LONG).show()
                    } else {
                        sugar =
                            findViewById<RadioButton>(binding.rgSugar.checkedRadioButtonId).text.toString()

                        if (coffee.equals("Cappuccino")) {
                            if (binding.rgMilk.checkedRadioButtonId == -1) {
                                milk = ""
                                isReady = false
                                Toast.makeText(this, "Не выбрано молоко", Toast.LENGTH_LONG).show()
                            } else {
                                milk =
                                    findViewById<RadioButton>(binding.rgMilk.checkedRadioButtonId).text.toString()
                            }
                        }
                    }
                }
            }

            if (isReady)
                when (coffee) {
                    "Americano" -> SelectedMenu(Coffee.Americano(volume, sugar))
                    "Cappuccino" -> SelectedMenu(Coffee.Cappuccino(volume, sugar, milk))
                    "Latte" -> SelectedMenu(Coffee.Latte(volume, sugar))
                    "Expresso" -> SelectedMenu(Coffee.Expresso(volume, sugar))
                    else -> Toast.makeText(this, "Не выбрано кофе", Toast.LENGTH_LONG).show()
                }
        }
    }

    fun SelectedMenu(menu: Coffee) {
        val intent = Intent(this, Prepare::class.java)
        intent.putExtra("coffee", menu)
        startActivity(intent)
// очищение radioButtons
        binding.rgCoffee.clearCheck()
        binding.rgVolume.clearCheck()
        binding.rgSugar.clearCheck()
        binding.rgMilk.clearCheck()
        binding.rgMilk.visibility = View.GONE
        binding.tvMilk.visibility = View.GONE
    }
}