package com.example.memorygame

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    var point = 0;
    var hamle = 0;
    var saniye=0;
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                country1,
                country2,
                country3,
                country4,
                country5,
                country6,
                country7,
                country8


            );

        val buttons = arrayOf(
            button1, button2, button3, button4, button5, button6, button7, button8,
            button9, button10, button11, button12, button13, button14, button15, button16
        )

        val cardBack = code
        var clicked = 0
        var turnOver = false
        var lastClicked = -1
        var sayac = 0


        val text = findViewById<TextView>(R.id.sayac) as TextView
        val puan = findViewById<TextView>(R.id.puan) as TextView
        val hamletext = findViewById<TextView>(R.id.hamle) as TextView




        images.shuffle()
        for (i in 0..15) {
            buttons[i].setBackgroundResource(cardBack)
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                hamle = hamle + 1
                hamletext.setText("hamle " + hamle.toString())
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
                        sayac++
                        text.setText("eşleşme " + sayac.toString() + "/8")
                        point = point + 15
                        puan.setText("puan " + point.toString())
                        turnOver = false
                        clicked = 0

                    }

                    if (sayac == 8) {


                        bitis(View(this))

                    }


                }



                if (buttons[i].text != buttons[lastClicked].text) {
                    point = point - 2
                    puan.setText("puan " + point.toString())
                    Handler().postDelayed({

                        buttons[lastClicked].setBackgroundResource(cardBack)
                        buttons[lastClicked].text = "cardBack"
                        buttons[i].setBackgroundResource(cardBack)
                        buttons[i].text = "cardBack"
                        turnOver = false
                        clicked = 0


                    }, 600)

                } else if (clicked == 0) {

                    turnOver = false


                }
            }
        }

    }


    fun bitis(view: View) {
        val database = this@MainActivity.openOrCreateDatabase("Puan", MODE_PRIVATE, null)
        database.execSQL("CREATE TABLE IF NOT EXISTS Puan (id INTEGER PRIMARY KEY, puan INT, sure INT)")
        database.execSQL("INSERT INTO Puan (puan, sure) VALUES ($point,$saniye)")

        val cursor: Cursor = database.rawQuery("SELECT * FROM Puan", null)

        val puanIx: Int = cursor.getColumnIndex("puan")
        val sureIx: Int = cursor.getColumnIndex("sure")


        while (cursor.moveToNext()) {
            Log.i("puanlar", "puan : " + cursor.getString(puanIx))
            Log.i("puanlar", "sure : " + cursor.getString(sureIx))

        }

        cursor.close()



        val alert = AlertDialog.Builder(this)



        alert.setTitle("Oyun Bitti!")

        alert.setMessage(
            "Süre : " +saniye+
                    "\n" +
                    "Puan : " + point.toString()

        )

        alert.setCancelable(false);

        alert.setPositiveButton("Tekrar Oyna") { dialogInterface: DialogInterface, i: Int ->
            finish();
            startActivity(getIntent());
        }

        alert.setNegativeButton("Ana menü") { dialogInterface: DialogInterface, i: Int ->

            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }
        alert.show()
    }




}













