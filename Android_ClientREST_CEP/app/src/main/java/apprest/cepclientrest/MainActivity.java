package apprest.cepclientrest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(onClickB);
    }

    View.OnClickListener onClickB = new View.OnClickListener(){
        public void onClick (View v) {
            String cep = editText.getText().toString();
            if (cep.trim().isEmpty()) {
                editText.setError("Digite o CEP!");
                editText.requestFocus();
            }
            else if(cep.trim().length()<8 && cep.trim().length()>0){
                editText.setError("CEP incompleto!");
                editText.requestFocus();
            }
            else {
                String link = "http://viacep.com.br/ws/" + cep + "/json/";
                AccessCEP accessCEP = new AccessCEP(textView);
                accessCEP.execute(link);
            }
        }
    };

}
