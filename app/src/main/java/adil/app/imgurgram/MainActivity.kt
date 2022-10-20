package adil.app.imgurgram

import adil.app.libimgur.ImgurAPI
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgurAPI = ImgurAPI()
        imgurAPI.a
    }
}