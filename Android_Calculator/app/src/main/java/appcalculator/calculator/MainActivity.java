package appcalculator.calculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Criando as variaveis para armazenar os numeros da operacao
    Double result;
    Double number1;
    Double number2;

    //Criando o arrayadapter para armazenar o array de strings do spinner (usando o layout padrão do spinner)
    ArrayAdapter<CharSequence> adapterOptions;

    //Criando objetos para relaciona-los com os objetos do arquivo activy_main.xml
    Spinner spinnerOptions;
    TextView texViewResult;
    EditText editTextNumber1;
    EditText editTextNumber2;
    Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //Inicializando as variaveis
            result = null;
            number1 = null;
            number2 = null;

            //Instanciando o objeto arraydapter e relacionando-o com array das opcoes de operacao, presente no arrays.xml
            adapterOptions = ArrayAdapter.createFromResource(this,R.array.options_array,android.R.layout.simple_spinner_item);

            //Instanciando o objeto spinner e relacionando-o com spinner_operation do activity_main.xml
            spinnerOptions = (Spinner) findViewById(R.id.spinner_operations);

            //Carregando os objetos
            adapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerOptions.setAdapter(adapterOptions);

            //Instanciando estes objetos e relacionando-o com os objetos do activity_main.xml
            texViewResult = (TextView) findViewById(R.id.textview_result);
            editTextNumber1 = (EditText) findViewById(R.id.edittext_number1);
            editTextNumber2 = (EditText) findViewById(R.id.edittext_number2);
            buttonCalculate = (Button) findViewById(R.id.button_calculate);

            //Associando o evento de clique no botao a funcao que realiza a operacao
            buttonCalculate.setOnClickListener(onClickOperation);

        }
        catch (Exception e){
            exceptionProcess("ErrorInicialization", e);

        }

    }



    //Funcao para realizar operacoes
    View.OnClickListener onClickOperation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Salvando numeros em double
            //number1 = Double.parseDouble(editTextNumber1.getText().toString());
            number1 = getNumber(editTextNumber1);
            Log.i("TESTE", "ok1");
            number2 = getNumber(editTextNumber2);
            Log.i("TESTE", "ok2");

            //Verificando operacao escolhida
            switch (spinnerOptions.getSelectedItemPosition()) {
                case 0:
                    showMessage("invalidOption");
                    break;

                case 1:
                    division();
                    break;

                case 2:
                    multiplication();
                    break;

                case 3:
                    addition();
                    break;

                case 4:
                    subtraction();
                    break;

            }
        }
    };


    //Metodo para receber o valor do edittext e passar de strig para double
    private Double getNumber(EditText et) {
        Double d = null;
        //d = Double.parseDouble(editTextNumber1.getText().toString());
        String str = String.valueOf(et.getText());

        if (str==null || str.length()==0){
            et.setError("Digite um número");
            et.requestFocus();
        }
        else if (str.equals(".") || str.equals("-") ){
            et.setError("Número inválido");
            et.requestFocus();
        }
        else if(str.length()>1 && str.substring(0,2).equals("-.")) {
            et.setError("Número inválido");
            et.requestFocus();
        }
        else{
            d = Double.parseDouble(str);

        }

        return d;

    }


    //Metodo para realizar divisao
    private void division() {
        try{
            if(number2 == 0){
                //saveError("invalidOperation");
                editTextNumber2.setError("Digite um número válido!");
                editTextNumber2.requestFocus();
                number2 = null;

            }
            else {
                result = number1 / number2;
                showResult();
            }

        }
        catch (NullPointerException e){
            exceptionProcess("InvalidNumber", e);

        }
    }


    //Metodo para realizar multiplicacao
    private void multiplication() {
        try{
            result = number1 * number2;
            showResult();

        }
        catch (NullPointerException e){
            exceptionProcess("InvalidNumber", e);

        }
    }


    //Metodo para realizar soma
    private void addition() {
        try{
            result = number1 + number2;
            showResult();

        }
        catch (NullPointerException e){
            exceptionProcess("InvalidNumber", e);

        }
    }


    //Metodo para realizar subtracao
    private void subtraction() {
        try{
            result = number1 - number2;
            showResult();

        }
        catch (NullPointerException e){
            exceptionProcess("InvalidNumber", e);

        }
    }


    //Metodo para mostrar resultado
    private void showResult() {
        DecimalFormat df = new DecimalFormat("#.###");
        String strResult = df.format(result);
        //String strResult = NumberFormat.getInstance().format(result);
        texViewResult.setText(strResult);

    }


    //Metodo para tratar erro
    private void exceptionProcess(String strError, Exception e) {
        Log.e(strError, e.getMessage());

    }


    //Metodo para apresentar mensagem de erro ao usuario
    private void showMessage(String strError) {
        final AlertDialog.Builder message = new AlertDialog.Builder(this);

        /*if(strError.equals("invalidOperation")){
            message.setTitle("Operação inválida");
            message.setMessage("Digite um número diferente de zero no segundo campo.");
        }
        if(strError.equals("InvalidNumber")){
            message.setTitle("Numero inválido");
            message.setMessage("Digite números válidos nos campos.");

        }*/
        if (strError.equals("invalidOption")) {
            message.setTitle("Opção inválida");
            message.setMessage("Escolha uma operação.");

        }
        /*if (strError.equals("ErrorInicialization")){
            message.setTitle("Problema de inicialização da Calculadora");
            message.setMessage("Escolha uma operação.");

        }*/

        message.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });

        AlertDialog alert = message.create();
        alert.show();

    }


}
