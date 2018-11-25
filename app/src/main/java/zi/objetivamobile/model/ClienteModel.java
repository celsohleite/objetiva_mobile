package zi.objetivamobile.model;

/**
 * Created by leite on 10/03/18.
 */

public class ClienteModel {

    private Long idCliente;
    private int cdCliente;
    private String nome;
    private String sobreNome;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cep;
    private String rua;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefoneFixo;
    private String telefoneCelular;
    private String email;
    private Integer status;
    private Integer cdTipoCliente;
    private Integer cdUsuario;


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCdTipoCliente() {
        return cdTipoCliente;
    }

    public void setCdTipoCliente(Integer cdTipoCliente) {
        this.cdTipoCliente = cdTipoCliente;
    }

    public Integer getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteModel that = (ClienteModel) o;

        if (cdCliente != that.cdCliente) return false;
        if (idCliente != null ? !idCliente.equals(that.idCliente) : that.idCliente != null)
            return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (sobreNome != null ? !sobreNome.equals(that.sobreNome) : that.sobreNome != null)
            return false;
        if (nomeFantasia != null ? !nomeFantasia.equals(that.nomeFantasia) : that.nomeFantasia != null)
            return false;
        if (razaoSocial != null ? !razaoSocial.equals(that.razaoSocial) : that.razaoSocial != null)
            return false;
        if (cnpj != null ? !cnpj.equals(that.cnpj) : that.cnpj != null) return false;
        if (cep != null ? !cep.equals(that.cep) : that.cep != null) return false;
        if (rua != null ? !rua.equals(that.rua) : that.rua != null) return false;
        if (numero != null ? !numero.equals(that.numero) : that.numero != null) return false;
        if (complemento != null ? !complemento.equals(that.complemento) : that.complemento != null)
            return false;
        if (bairro != null ? !bairro.equals(that.bairro) : that.bairro != null) return false;
        if (cidade != null ? !cidade.equals(that.cidade) : that.cidade != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;
        if (telefoneFixo != null ? !telefoneFixo.equals(that.telefoneFixo) : that.telefoneFixo != null)
            return false;
        if (telefoneCelular != null ? !telefoneCelular.equals(that.telefoneCelular) : that.telefoneCelular != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (cdTipoCliente != null ? !cdTipoCliente.equals(that.cdTipoCliente) : that.cdTipoCliente != null)
            return false;
        return cdUsuario != null ? cdUsuario.equals(that.cdUsuario) : that.cdUsuario == null;

    }

    @Override
    public int hashCode() {
        int result = idCliente != null ? idCliente.hashCode() : 0;
        result = 31 * result + cdCliente;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (sobreNome != null ? sobreNome.hashCode() : 0);
        result = 31 * result + (nomeFantasia != null ? nomeFantasia.hashCode() : 0);
        result = 31 * result + (razaoSocial != null ? razaoSocial.hashCode() : 0);
        result = 31 * result + (cnpj != null ? cnpj.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        result = 31 * result + (rua != null ? rua.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (complemento != null ? complemento.hashCode() : 0);
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (telefoneFixo != null ? telefoneFixo.hashCode() : 0);
        result = 31 * result + (telefoneCelular != null ? telefoneCelular.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cdTipoCliente != null ? cdTipoCliente.hashCode() : 0);
        result = 31 * result + (cdUsuario != null ? cdUsuario.hashCode() : 0);
        return result;
    }
}
