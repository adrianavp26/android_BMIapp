package com.adrianavallespi.act03

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var genereSelecc: String? = null
    private var currentWeight:Int = 60
    private var currentAge:Int = 18
    private var currentHeight: Int = 120

    private lateinit var male: CardView
    private lateinit var female: CardView
    private lateinit var height: TextView
    private lateinit var rsHeight: Slider

    private lateinit var btnMenosWeight: FloatingActionButton
    private lateinit var btnMasWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnMenosAge: FloatingActionButton
    private lateinit var btnMasAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var tvCalcular: CardView

    companion object{ const val imcKey = "imcResult"}
    //por si alguna vez se cambia el texto se cambiará en todas partes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //creamos una funcion que inicie todos los componentes
        iniciarCompo()
        iniciarOnClick()

    }
    private fun iniciarCompo(){
        //genero
        male = findViewById<CardView>(R.id.viewMale);
        female = findViewById<CardView>(R.id.viewFemale);
        //height
        height = findViewById<TextView>(R.id.height)
        rsHeight = findViewById<Slider>(R.id.RSheight)
        //weight
        btnMenosWeight = findViewById<FloatingActionButton>(R.id.menosWeight)
        btnMasWeight = findViewById<FloatingActionButton>(R.id.masWeight)
        tvWeight = findViewById<TextView>(R.id.tvWeight)
        //age
        btnMenosAge = findViewById<FloatingActionButton>(R.id.menosAge);
        btnMasAge = findViewById<FloatingActionButton>(R.id.masAge);
        tvAge = findViewById<TextView>(R.id.tvAge)
        tvCalcular = findViewById<CardView>(R.id.calcular)
    }
    private fun iniciarOnClick(){
        male.setOnClickListener { genderSelect("male") }
        female.setOnClickListener { genderSelect("female") }
        //altura
        rsHeight.addOnChangeListener { _, value, _ ->
            //hay que formatear para que no de el decimal
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            height.text = currentHeight.toString()
        }
        //weight
        btnMasWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnMenosWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        //weight i age
        btnMasAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnMenosAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        //calcular
        tvCalcular.setOnClickListener {
            val resultado = calcularIMC()
            resultado(resultado)
        }

    }

    fun resultado(result: Double) {
        val intent = Intent(this, ResultadoImcAct2::class.java)
        intent.putExtra(imcKey, result)
        startActivity(intent)
    }

    private fun calcularIMC(): Double{
        val df = DecimalFormat("#.##")
        //dividir el peso entre
        val imc = currentWeight /(currentHeight.toDouble() /100 * currentHeight.toDouble() /100)
        return df.format(imc).toDouble()
    }
    private fun setWeight(){
        tvWeight.text = currentWeight.toString()
    }
    private fun setAge(){
        tvAge.text = currentAge.toString()
    }
    //hay que hacer click en male y guardar en una variable que se ha clicado el male
    //cambiarlo de color de fondo para que se vea que se ha clicado
    fun genderSelect(genere: String){
        genereSelecc = genere
        male.setCardBackgroundColor(getBackgroundColor(genere == "male"))
        female.setCardBackgroundColor(getBackgroundColor(genere == "female"))
    }
    //para obtener la refeencia del color de fondo para saber a que color tenemos que cambiar
    private fun getBackgroundColor(isSelectedComponent: Boolean): Int{
        val colorRef = if(isSelectedComponent){
            R.color.contActiu
        }else{
            R.color.cont
        }
        return ContextCompat.getColor(this, colorRef)
    }


}