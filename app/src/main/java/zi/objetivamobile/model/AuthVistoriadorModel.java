package zi.objetivamobile.model;

/**
 * Created by leite on 21/09/17.
 */

public class AuthVistoriadorModel {

    private int codVistoriador;
    private String emailUsuario;
    private String emailVistoriador;
    private String cpfVistoriador;
    private String nomeVistoriador;
    private String sobrenomeVistoriador;
    private String retorno;
    private String status;
    private int codUsuario;

    public int getCodVistoriador() {
        return codVistoriador;
    }

    public void setCodVistoriador(int codVistoriador) {
        this.codVistoriador = codVistoriador;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getEmailVistoriador() {
        return emailVistoriador;
    }

    public void setEmailVistoriador(String emailVistoriador) {
        this.emailVistoriador = emailVistoriador;
    }

    public String getCpfVistoriador() {
        return cpfVistoriador;
    }

    public void setCpfVistoriador(String cpfVistoriador) {
        this.cpfVistoriador = cpfVistoriador;
    }

    public String getNomeVistoriador() {
        return nomeVistoriador;
    }

    public void setNomeVistoriador(String nomeVistoriador) {
        this.nomeVistoriador = nomeVistoriador;
    }

    public String getSobrenomeVistoriador() {
        return sobrenomeVistoriador;
    }

    public void setSobrenomeVistoriador(String sobrenomeVistoriador) {
        this.sobrenomeVistoriador = sobrenomeVistoriador;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthVistoriadorModel that = (AuthVistoriadorModel) o;

        if (codVistoriador != that.codVistoriador) return false;
        if (codUsuario != that.codUsuario) return false;
        if (emailUsuario != null ? !emailUsuario.equals(that.emailUsuario) : that.emailUsuario != null)
            return false;
        if (emailVistoriador != null ? !emailVistoriador.equals(that.emailVistoriador) : that.emailVistoriador != null)
            return false;
        if (cpfVistoriador != null ? !cpfVistoriador.equals(that.cpfVistoriador) : that.cpfVistoriador != null)
            return false;
        if (nomeVistoriador != null ? !nomeVistoriador.equals(that.nomeVistoriador) : that.nomeVistoriador != null)
            return false;
        if (sobrenomeVistoriador != null ? !sobrenomeVistoriador.equals(that.sobrenomeVistoriador) : that.sobrenomeVistoriador != null)
            return false;
        if (retorno != null ? !retorno.equals(that.retorno) : that.retorno != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;

    }

    @Override
    public int hashCode() {
        int result = codVistoriador;
        result = 31 * result + (emailUsuario != null ? emailUsuario.hashCode() : 0);
        result = 31 * result + (emailVistoriador != null ? emailVistoriador.hashCode() : 0);
        result = 31 * result + (cpfVistoriador != null ? cpfVistoriador.hashCode() : 0);
        result = 31 * result + (nomeVistoriador != null ? nomeVistoriador.hashCode() : 0);
        result = 31 * result + (sobrenomeVistoriador != null ? sobrenomeVistoriador.hashCode() : 0);
        result = 31 * result + (retorno != null ? retorno.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + codUsuario;
        return result;
    }
}
