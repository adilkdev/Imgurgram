package adil.app.imgurgram

import android.app.Application
import android.os.Build.VERSION.SDK_INT
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.VideoFrameDecoder

class ImgurgramApp : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        Coil.setImageLoader(this)
    }

    override fun newImageLoader() = ImageLoader.Builder(this@ImgurgramApp)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
            add(VideoFrameDecoder.Factory())
        }.build()

}