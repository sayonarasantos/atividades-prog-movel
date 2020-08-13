package applogin.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTexPassword;

    private Button buttonLogin;
    private Button buttonRecover;

    private CheckBox checkBoxSave;

    private String password;
    private String userName;

    private String idLogin;
    private String idPassword;

    SharedPreferences userPreferences;
    SharedPreferences.Editor editor;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName = (EditText) findViewById(R.id.editText_userName);
        editTexPassword = (EditText) findViewById(R.id.editText_password);

        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(onClickLogin);
        buttonRecover = (Button) findViewById(R.id.button_recover);
        buttonRecover.setOnClickListener(onClickRecover);

        checkBoxSave = (CheckBox) findViewById(R.id.checkBox_savePassword);

        userName = null;
        password = null;
        idLogin = null;
        idPassword = null;

        intent = new Intent(Intent.ACTION_VIEW);

    }


    //quando o botao "Entar" é clicado, recebe-se os dados do usuario, tenta salva-los e fazer o login
    View.OnClickListener onClickLogin = new View.OnClickListener(){
        public void onClick(View v){
            userName = getDataEditText(editTextUserName);
            password = getDataEditText(editTexPassword);

            if (userName!=null && password!=null) {
                if (checkBoxSave.isChecked()) {
                    saveLogin();
                }
                if (searchUser(userName, password) == true) {
                    doLogin();
                } else {
                    showMessage();
                }
            }
        }
    };


    //quando o botao "Recuperar" é clicado, retorna-se a senha do usuario indicado
    View.OnClickListener onClickRecover = new View.OnClickListener(){
        public void onClick(View v){
            userName = getDataEditText(editTextUserName);

            if(userName!=null){
                recoverLogin();
                editTexPassword.setText(password);
            }
        }
    };


    //salva os dados do usuário da forma chave-valor, onde o username é a chave e a senha é o valor
    private void saveLogin() {
        //int mode= Activity.MODE_PRIVATE; //Quem pode acessar?
        idLogin = userName;
        userPreferences = getSharedPreferences(idLogin, MODE_PRIVATE);
        editor = userPreferences.edit();
        editor.putString(idPassword, password);
        editor.commit();
    }


    //recupera a senha do usuario salvo de acordo com o username indicado
    private void recoverLogin() {
        //int mode = Activity.MODE_PRIVATE ; //Quem pode acessar?
        idLogin = userName;
        userPreferences = getSharedPreferences(idLogin, MODE_PRIVATE);
        password = userPreferences.getString(idPassword, "");
    }


    //mostra mensagem de erro quando o usuário nao esta cadastrado
    private void showMessage() {
        final AlertDialog.Builder message = new AlertDialog.Builder(this);
        message.setTitle("Usuário inválido");
        message.setMessage("O usuário digitado não está cadastrado!");
        message.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = message.create();
        alert.show();
    }


    //pega o dado no edit text e passa para string
    private String getDataEditText(EditText et) {
        String str = et.getText().toString();
        if (str.trim().isEmpty()){
            //o usuario nao preencheu o campo
            et.setError("Preencha este campo!");
            et.requestFocus();
            str = null;
        }
        return str;
    }


    //abstacao para busca no servidor
    //verifica se o usuario esta cadastrado e se a senha esta correta
    private Boolean searchUser(String strUser, String strPassword) {
        Boolean result = false;
        String v[][] = {{"maria", "123"}, {"elis", "456"}, {"chico", "789"}};
        for (int i=0; i<3; i++){
            if (strUser.equals(v[i][0]) && strPassword.equals(v[i][1])){
                result = true;
                break;
            }
            else if (strUser==v[i][0] && strPassword!=v[i][1]){
                editTexPassword.setError("Senha incorreta!");
                editTexPassword.requestFocus();
            }
        }
        return result;
    }


    //abstracao para o login no perfil
    //abre uma página do google em um navegador
    private void doLogin() {
        intent.setData(Uri.parse("http://www.ufc.br/"));
        this.startActivity(intent);
    }

}