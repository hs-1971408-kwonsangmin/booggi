package com.example.mango_contents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private val items= mutableListOf<contentsModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bookmarkbtn=findViewById<TextView>(R.id.bookmarkbtn)

        bookmarkbtn.setOnClickListener{
            intent=Intent(this,BookMark::class.java)
            startActivity(intent)
        }

        val logout=findViewById<TextView>(R.id.logoutBTN)
        logout.setOnClickListener{

            Firebase.auth.signOut()
            intent=Intent(this,JoinActiviy::class.java)
            startActivity(intent)
        }





        items.add(
            contentsModel("https://www.hansung.ac.kr/hansung/8385/subview.do",
                "https://www.hansung.ac.kr/sites/hansung/images/common/NEW_Logo_2.png"
            ,"한성대학교 공지사항")
        )


        items.add(
            contentsModel("http://cse.hansung.ac.kr/",
                "http://cse.hansung.ac.kr/resources/images/track.png"
                ,"컴퓨터공학부")
        )
        items.add(
            contentsModel("https://dorm.hansung.ac.kr/kor/student/noti.do",
                "https://dorm.hansung.ac.kr/kor/_Img/Content/pano/sangsang_02.jpg"
                ,"상상빌리지")
        )






        val recyclerView=findViewById<RecyclerView>(R.id.recyclelerView)

        val rVadapter=RVadapter(baseContext,items)

        recyclerView.adapter=rVadapter





        rVadapter.itemClick=object :RVadapter.ItemClick{
            override fun OnClick(view: View, position: Int) {
               val intent= Intent(baseContext,ViewActivity::class.java)
                intent.putExtra("URL",items[position].url)
                intent.putExtra("title",items[position].titleText)
                intent.putExtra("imageURL",items[position].ImageURL)

                startActivity(intent)
            }

        }

        recyclerView.layoutManager=GridLayoutManager(this,2)



    }
}