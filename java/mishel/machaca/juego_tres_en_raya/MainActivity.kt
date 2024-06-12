package mishel.machaca.juego_tres_en_raya

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // Declaramos nueve variables para cada una de las casillas del tablero.
    var imageView1: ImageView? = null
    var imageView2: ImageView? = null
    var imageView3: ImageView? = null
    var imageView4: ImageView? = null
    var imageView5: ImageView? = null
    var imageView6: ImageView? = null
    var imageView7: ImageView? = null
    var imageView8: ImageView? = null
    var imageView9: ImageView? = null

    // Dos variables para mostrar el turno actual, X o O.
    var imgTurnoX: ImageView? = null
    var imgTurnoO: ImageView? = null

    // Esta variable mantiene el turno actual, empezamos con "x".
    var turno = "x"

    // Esta matriz contiene todas las combinaciones posibles para ganar.
    val matrizGanadora = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(3, 6, 9),
        intArrayOf(1, 5, 9),
        intArrayOf(3, 5, 7)
    )

    // Arrays para guardar las posiciones seleccionadas por los jugadores.
    var posicionesX = IntArray(5)
    var posicionesO = IntArray(5)

    // Contadores para llevar la cuenta de las posiciones seleccionadas.
    var contadorX = 0
    var contadorO = 0

    // Variable para guardar el nombre del ganador.
    var ganador = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializamos todas las casillas del tablero.
        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)
        imageView4 = findViewById(R.id.imageView4)
        imageView5 = findViewById(R.id.imageView5)
        imageView6 = findViewById(R.id.imageView6)
        imageView7 = findViewById(R.id.imageView7)
        imageView8 = findViewById(R.id.imageView8)
        imageView9 = findViewById(R.id.imageView9)

        // Inicializamos las imágenes que muestran el turno actual.
        imgTurnoX = findViewById(R.id.imgTurnoX)
        imgTurnoO = findViewById(R.id.imgTurnoO)

        // Resaltamos el turno de X al inicio del juego.
        imgTurnoX?.setBackgroundColor(Color.parseColor("#86E3CE"))
    }

    // Esta función se llama cuando se hace clic en una casilla del tablero.
    fun clickImagen(view: View) {
        // Obtenemos el nombre del recurso de la vista (la casilla clickeada).
        val rutaNombre = resources.getResourceName(view.id)
        val nombreClick = rutaNombre.substring(rutaNombre.lastIndexOf("/") + 1)

        // Verificamos cuál casilla fue seleccionada y actualizamos las posiciones.
        for (i in 1..9) {
            val nombreConcatenado = "imageView$i"
            if (nombreClick == nombreConcatenado) {
                // Dependiendo del turno, guardamos la posición en el array correspondiente.
                if (turno == "x") {
                    posicionesX[contadorX] = i
                    contadorX++
                } else {
                    posicionesO[contadorO] = i
                    contadorO++
                }
            }
        }

        // Cambiamos la imagen y el turno después de un clic.
        if (turno == "x") {
            view.setBackgroundResource(R.drawable.cruz)
            imgTurnoX?.setBackgroundColor(Color.WHITE)
            imgTurnoO?.setBackgroundColor(Color.parseColor("#86E3CE"))
            turno = "o"
        } else {
            view.setBackgroundResource(R.drawable.redondo)
            imgTurnoX?.setBackgroundColor(Color.parseColor("#86E3CE"))
            imgTurnoO?.setBackgroundColor(Color.WHITE)
            turno = "x"
        }

        // Ordenamos las posiciones antes de verificar si alguno de los jugadores ha ganado.
        posicionesX.sort(0, contadorX)
        posicionesO.sort(0, contadorO)

        // Verificamos si alguno de los jugadores ha ganado.
        val altoMatriz = matrizGanadora.size
        for (i in 0 until contadorX) {
            var gano = true
            for (j in 0 until altoMatriz) {
                gano = coincideGanador(matrizGanadora[j], posicionesX)
                if (gano) {
                    ganador = "x"
                    Toast.makeText(this, "Gano X", Toast.LENGTH_LONG).show()
                    dibujaLinea()
                    deshabilitar()
                    break
                }
            }
            if (gano) {
                break
            }
        }
        for (i in 0 until contadorO) {
            var gano = true
            for (j in 0 until altoMatriz) {
                gano = coincideGanador(matrizGanadora[j], posicionesO)
                if (gano) {
                    ganador = "o"
                    Toast.makeText(this, "Gano O", Toast.LENGTH_LONG).show()
                    dibujaLinea()
                    deshabilitar()
                    break
                }
            }
            if (gano) {
                break
            }
        }

        // Deshabilitamos la casilla seleccionada.
        view.isEnabled = false
    }

    // Esta función verifica si un jugador ha ganado.
    // Esta función verifica si un jugador ha ganado.
    fun coincideGanador(registroGanador: IntArray, posiciones: IntArray): Boolean {
        for (combination in matrizGanadora) {
            if (posiciones.contains(registroGanador[0]) &&
                posiciones.contains(registroGanador[1]) &&
                posiciones.contains(registroGanador[2])) {
                return true
            }
        }
        return false
    }


    // Esta función dibuja una línea cuando hay un ganador.
    fun dibujaLinea() {
        if (ganador == "x") {
            for (i in 0 until contadorX) {
                when (posicionesX[i]) {
                    1 -> imageView1?.setBackgroundColor(Color.parseColor("#FA897B"))
                    2 -> imageView2?.setBackgroundColor(Color.parseColor("#FA897B"))
                    3 -> imageView3?.setBackgroundColor(Color.parseColor("#FA897B"))
                    4 -> imageView4?.setBackgroundColor(Color.parseColor("#FA897B"))
                    5 -> imageView5?.setBackgroundColor(Color.parseColor("#FA897B"))
                    6 -> imageView6?.setBackgroundColor(Color.parseColor("#FA897B"))
                    7 -> imageView7?.setBackgroundColor(Color.parseColor("#FA897B"))
                    8 -> imageView8?.setBackgroundColor(Color.parseColor("#FA897B"))
                    9 -> imageView9?.setBackgroundColor(Color.parseColor("#FA897B"))
                }
            }
        } else {
            for (i in 0 until contadorO) {
                when (posicionesO[i]) {
                    1 -> imageView1?.setBackgroundColor(Color.parseColor("#FA897B"))
                    2 -> imageView2?.setBackgroundColor(Color.parseColor("#FA897B"))
                    3 -> imageView3?.setBackgroundColor(Color.parseColor("#FA897B"))
                    4 -> imageView4?.setBackgroundColor(Color.parseColor("#FA897B"))
                    5 -> imageView5?.setBackgroundColor(Color.parseColor("#FA897B"))
                    6 -> imageView6?.setBackgroundColor(Color.parseColor("#FA897B"))
                    7 -> imageView7?.setBackgroundColor(Color.parseColor("#FA897B"))
                    8 -> imageView8?.setBackgroundColor(Color.parseColor("#FA897B"))
                    9 -> imageView9?.setBackgroundColor(Color.parseColor("#FA897B"))
                }
            }
        }
    }

    // Esta función deshabilita todas las casillas cuando hay un ganador.
    fun deshabilitar() {
        imageView1?.isEnabled = false
        imageView2?.isEnabled = false
        imageView3?.isEnabled = false
        imageView4?.isEnabled = false
        imageView5?.isEnabled = false
        imageView6?.isEnabled = false
        imageView7?.isEnabled = false
        imageView8?.isEnabled = false
        imageView9?.isEnabled = false
    }

    // Esta función reinicia el juego cuando se presiona el botón de reinicio.
    fun reiniciar(view: View) {
        val intento = intent
        finish()  // Cierra la actividad actual
        startActivity(intento)  // Reinicia la actividad
    }
}
