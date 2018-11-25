package zi.objetivamobile.business;

import zi.objetivamobile.evalid.Login;
import zi.objetivamobile.model.AuthVistoriadorModel;
import zi.objetivamobile.model.UsuarioModel;
import zi.objetivamobile.rest.ClientUsuario;
import zi.objetivamobile.util.ValidacaoUtil;

/**
 * Created by leite on 19/09/17.
 */

public class LoginBusiness {

    private AuthVistoriadorModel vistoriador;

    public AuthVistoriadorModel authLogin(String url, String email, String senha) {
        UsuarioModel model = new UsuarioModel();

        String retorno = "";

        vistoriador = new AuthVistoriadorModel();
        vistoriador.setEmailUsuario(email);
        vistoriador.setCpfVistoriador(senha);

        senha = senha.isEmpty() ? "" : ValidacaoUtil.md5passConvert(senha);

        if (email.isEmpty() && senha.isEmpty()) {
            retorno = Login.USUARIO_SENHA_VAZIO;
        } else {
            if (ValidacaoUtil.isValidEmail(email)) {

                try {
                    vistoriador = ClientUsuario.get(url, vistoriador);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(vistoriador!=null ){
                 //&& email.equals(model.getUsuario()) && senha.equals(model.getSenha())){
                    retorno = Login.USUARIO_OK;
                }else{
                    retorno = Login.USUARIO_SENHA_INVALIDA;
                }

            } else {
                retorno = Login.EMAIL_INVALIDO;
            }
        }

        vistoriador.setRetorno(retorno);

        return vistoriador;
    }

}
