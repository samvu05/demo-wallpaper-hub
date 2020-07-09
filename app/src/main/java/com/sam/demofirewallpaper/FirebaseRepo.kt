package com.sam.demofirewallpaper

import android.app.DownloadManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class FirebaseRepo {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var lastVisible: DocumentSnapshot? = null
    private val pageSize:Long = 6

    fun getUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun queryWall(): Task<QuerySnapshot> {
        if (lastVisible == null) {
            //Load First Page
            return firebaseFirestore
                .collection("Wallpapers")
                .orderBy("name", Query.Direction.DESCENDING)
                .limit(6)
                .get()
        }
        else{
            //Load Next Page
            return firebaseFirestore
                .collection("Wallpaper")
                .orderBy("name", Query.Direction.DESCENDING)
                .startAfter(lastVisible!!)
                .limit(pageSize)
                .get()

        }
    }
}