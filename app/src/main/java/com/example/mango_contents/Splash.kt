package com.example.mango_contents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Splash : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        auth = Firebase.auth

        if(auth.currentUser?.uid==null){

            Handler().postDelayed({

                startActivity(Intent(this,JoinActiviy::class.java))
                finish()
            },3000)
            //회원가입 안되있는 경우  조인 액티비티
        }else{

            Handler().postDelayed({

                startActivity(Intent(this,MainActivity::class.java))
                finish()
            },3000)

            //회원가압 되어있으므로 메인액티비티
        }


        try{

        }catch (E:Exception){

        }



    }
}