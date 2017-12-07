package appthread.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity{

    private ImageView imageView;
    private Button button;
//    private ProgressBar progressBar;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(onClickShow);
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);
        Log.i("TESTE", "Inicialidação feita");
    }


    View.OnClickListener onClickShow = new View.OnClickListener(){
        public void onClick (View v) {
            Log.i("TESTE", "botão clicado");
            //String link = "https://logodownload.org/wp-content/uploads/2016/09/ufc-logo-universidade.png";
            DownloaderImage downloaderImage = new DownloaderImage(imageView);
            Log.i("TESTE", "criação di feita");
            String link = "http://inacio.com.br/wpcontent/uploads/2013/02/logo_ufc-virtual.jpg";
            Log.i("TESTE", link);
            textView.setText(link);
            downloaderImage.execute(link);
            Log.i("TESTE", "evento do botão finalizado");
        }
    };




    public class DownloaderImage extends AsyncTask<String, Void, Bitmap> {
        private ImageView iv;

        public DownloaderImage(ImageView iv) {
            this.iv = iv;
            Log.i("TESTE", "Construtor");
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                Log.i("TESTE", "no try");
                String url = params[0];
                Log.i("TESTE", "no back"+url);

                Bitmap bitmap =
                        BitmapFactory.decodeStream((InputStream)
                                new URL(url).getContent());
                return bitmap;
            }
            catch (Exception e) {
                Log.e("ERRO"+String.valueOf(e), e.getMessage());
                return null;
            }
        }

//        protected void onProgressUpdate(Integer... values) {
//            progressBar.setProgress(values[0]);
//        }

        protected void onPostExecute(Bitmap result) {
            Log.i("TESTE", "no portexecute");
            this.iv.setImageBitmap(result);
        }
    }

}