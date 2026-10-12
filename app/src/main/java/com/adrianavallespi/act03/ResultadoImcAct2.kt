package com.adrianavallespi.act03

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adrianavallespi.act03.MainActivity.Companion.imcKey

class ResultadoImcAct2 : AppCompatActivity() {

    private lateinit var tvTipo : TextView
    private lateinit var tvImc : TextView
    private lateinit var tvDescripcion : TextView
    private lateinit var btnRecalcular : CardView
    private lateinit var btnVolver : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado_imc_act2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val result:Double = intent.extras?.getDouble(imcKey) ?: -1.0
        iniciarCompo()
        cambioTexto(result)
        iniciarOnClick()

    }

        private fun iniciarOnClick(){
            btnRecalcular.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            btnVolver.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }

    private fun cambioTexto(result: Double){
        tvImc.text = result.toString()
        when(result){
            in 0.00..18.50 ->{ //bajo
                tvTipo.text = getString(R.string.titulo_bajo)
                tvTipo.setTextColor(ContextCompat.getColor(this, R.color.bajo))
                tvDescripcion.text = getString(R.string.desc_bajo)
            }
            in 18.50..24.99 ->{ //normal
                tvTipo.text = getString(R.string.titulo_normal)
                tvTipo.setTextColor(ContextCompat.getColor(this, R.color.normal))
                tvDescripcion.text = getString(R.string.desc_normal)
            }
            in 25.00..29.99 ->{ //sobrepeso
                tvTipo.text = getString(R.string.titulo_sobrepeso)
                tvTipo.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                tvDescripcion.text = getString(R.string.desc_sobrepeso)
            }
            in 30.00..99.00 ->{ //obesidad
                tvTipo.text = getString(R.string.titulo_obesidad)
                tvTipo.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescripcion.text = getString(R.string.desc_obesidad)
            }
            else -> {
                tvTipo.text= getString(R.string.error)
                tvImc.text= getString(R.string.error)
                tvDescripcion.text= getString(R.string.error)
            }
        }
    }

    private fun iniciarCompo(){
        tvTipo = findViewById<TextView>(R.id.tvTipo)
        tvImc = findViewById<TextView>(R.id.tvImc)
        tvDescripcion = findViewById<TextView>(R.id.tvDescripcion)
        btnRecalcular = findViewById<CardView>(R.id.reCalcular)
        btnVolver = findViewById<ImageButton>(R.id.ibVolver)

    }
}