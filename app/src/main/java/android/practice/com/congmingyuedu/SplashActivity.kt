package android.practice.com.congmingyuedu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.practice.com.congmingyuedu.view.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        //TODO: Update the splash screen's placeholder image with the final App's image
    }
}
