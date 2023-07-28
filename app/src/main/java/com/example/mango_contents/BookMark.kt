package com.example.mango_contents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BookMark : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private val contentModel= mutableListOf<contentsModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_mark)

        auth = Firebase.auth



        val database = Firebase.database
        val myBookmark = database.getReference("bookmark_ref")

        val rVadapter=RVadapter(baseContext,contentModel)


        myBookmark.child(auth.currentUser?.uid.toString())//데이터 베이스에서 계정별로의 저장된 북마크 목록 불러와서 어뎁터에 실시간 최신화
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(dataModel in snapshot.children){
                        contentModel.add(dataModel.getValue(contentsModel::class.java)!!)

                    }
                    rVadapter.notifyDataSetChanged()//데이터 변경 최신화 동기화해주기
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Bookmark","DBerror")
                }

            })

        val recyclerView=findViewById<RecyclerView>(R.id.bookmarkrv)


        rVadapter.itemClick=object :RVadapter.ItemClick{
            override fun OnClick(view: View, position: Int) {
                val intent= Intent(baseContext,ViewActivity::class.java)
                intent.putExtra("URL",contentModel[position].url)
                intent.putExtra("title",contentModel[position].titleText)
                intent.putExtra("imageURL",contentModel[position].ImageURL)

                startActivity(intent)
            }

        }




        recyclerView.adapter=rVadapter
        recyclerView.layoutManager= GridLayoutManager(this,2)



    }
}