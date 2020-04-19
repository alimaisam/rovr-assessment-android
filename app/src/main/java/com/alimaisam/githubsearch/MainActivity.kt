package com.alimaisam.githubsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLanguage = findViewById<Button>(R.id.btnLanguage);

        btnLanguage.setOnClickListener {
            val intent = Intent(this, RepoListActivity::class.java);
            intent.putExtra("Type", 0);
            startActivity(intent);
        }

        val btnTopic = findViewById<Button>(R.id.btnTopic);

        btnTopic.setOnClickListener {
            val intent = Intent(this, RepoListActivity::class.java);
            intent.putExtra("Type", 1);
            startActivity(intent);
        }

        val btnReport = findViewById<Button>(R.id.btnReport);

        btnReport.setOnClickListener {
            val intent = Intent(this, RepoListActivity::class.java);
            intent.putExtra("Type", 2);
            startActivity(intent);
        }
    }
}
