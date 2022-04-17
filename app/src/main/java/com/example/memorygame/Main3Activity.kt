package com.example.memorygame

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main2.*
import kotlinx.android.synthetic.main.content_main2.button1
import kotlinx.android.synthetic.main.content_main2.button10
import kotlinx.android.synthetic.main.content_main2.button11
import kotlinx.android.synthetic.main.content_main2.button12
import kotlinx.android.synthetic.main.content_main2.button13
import kotlinx.android.synthetic.main.content_main2.button14
import kotlinx.android.synthetic.main.content_main2.button15
import kotlinx.android.synthetic.main.content_main2.button16
import kotlinx.android.synthetic.main.content_main2.button2
import kotlinx.android.synthetic.main.content_main2.button3
import kotlinx.android.synthetic.main.content_main2.button4
import kotlinx.android.synthetic.main.content_main2.button5
import kotlinx.android.synthetic.main.content_main2.button6
import kotlinx.android.synthetic.main.content_main2.button7
import kotlinx.android.synthetic.main.content_main2.button8
import kotlinx.android.synthetic.main.content_main2.button9

class Main3Activity : AppCompatActivity() {
    var point=0;
    var hamle=0;
    var saniye=0;
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
     var   countDownTimer = object : CountDownTimer(
            1500000, 1000
        ) {
            override fun onTick(millisUntilFinished: Long) {
                    saniye=saniye+1
                //Milisaniyeyi 1000 e böldüğümüzde saniyeyi buluyoruz.Yani 15 saniye.
            }


            override fun onFinish() {

            }
        }.start()
        val images: MutableList<Int> =
            mutableListOf(
                country1,
                country2,
                country3,
                country4,
                country5,
                country6,
                country7,
                country8,
                country9,
                country10,
                country11,
                country12,
                country13,
                country14,
                country15,
                country16,
                country17,
                country18,
                country1,
                country2,
                country3,
                country4,
                country5,
                country6,
                country7,
                country8,
                country9,
                country10,
                country11,
                country12,
                country13,
                country14,
                country15,
                country16,
                country17,
                country18


            );

        val buttons = arrayOf(
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button10,
            button11,
            button12,
            button13,
            button14,
            button15,
            button16,
            button17,
            button18,
            button19,
            button20,
            button21,
            button22,
            button23,
            button24,
            button25,
            button26,
            button27,
            button28,
            button29,
            button30,
            button31,
            button32,
            button33,
            button34,
            button35,
            button36
        )


        val cardBack = code
        var clicked = 0
        var turnOver = false
        var lastClicked = -1
        var sayac =0
        val text = findViewById<TextView>(R.id.sayac) as TextView
        val puan = findViewById<TextView>(R.id.puan) as TextView
        val hamletext = findViewById<TextView>(R.id.hamle) as TextView


        images.shuffle()
        for(i in 0..35){
            buttons[i].setBackgroundResource(cardBack)
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                hamle=hamle+1
                hamletext.setText("hamle "+hamle.toString())
                if (buttons[i].text == "cardBack" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (clicked == 0) {
                        lastClicked = i

                    }

                    clicked++
                } else if (buttons[i].text !in "cardBack") {
                    buttons[i].setBackgroundResource(cardBack)
                    buttons[i].text = "cardBack"
                    clicked--

                }

                if (clicked == 2) {
                    turnOver = true
                    if (buttons[i].text == buttons[lastClicked].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        sayac ++
                        text.setText(sayac.toString()+"/18")

                        point=point+15
                        puan.setText("puan "+point.toString())
                        text.setText(sayac.toString()+"/16")
                        turnOver = false
                        clicked = 0

                    }

                    if (sayac ==18)

                    {


                        bitis(View(this))

                    }



                }



                if (buttons[i].text != buttons[lastClicked].text){
                    point=point-2
                    puan.setText("puan "+point.toString())
                    Handler().postDelayed({

                        buttons[lastClicked].setBackgroundResource(cardBack)
                        buttons[lastClicked].text = "cardBack"
                        buttons[i].setBackgroundResource(cardBack)
                        buttons[i].text = "cardBack"
                        turnOver = false
                        clicked = 0


                    }, 600)

                }





                else if (clicked == 0) {

                    turnOver = false



                }
            }
        }


}









    fun bitis(view: View)
    {
        val alert = AlertDialog.Builder(this)

        alert.setTitle("Oyun Bitti!")

        alert.setMessage("Süre : " +saniye+
                "\n"+
                "Puan : "+point.toString()
        )

        alert.setCancelable(false);

        alert.setPositiveButton("Tekrar Oyna") { dialogInterface: DialogInterface, i: Int ->
            finish();
            startActivity(getIntent());
        }

        alert.setNegativeButton("Ana menü") {dialogInterface: DialogInterface, i: Int ->

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        alert.show()
    }


}




