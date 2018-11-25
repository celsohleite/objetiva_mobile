package zi.objetivamobile.model;

/**
 * Created by leite on 02/10/17.
 */

public class FotoModel {

    private Long idFoto;
    private Long idCliente;
    private String nomeFoto;
    private String pathArquivo;
    private String fotoArquivo;
    private String descricao;
    private Long idLaudo;
    private Long codLaudo;
    private String status;
    //ARQUIVO TEMPORARIO
    private String tmpFoto;
    private String tipoLaudo;
    private String sync;

    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeFoto() {
        return nomeFoto;
    }

    public void setNomeFoto(String nomeFoto) {
        this.nomeFoto = nomeFoto;
    }

    public String getPathArquivo() {
        return pathArquivo;
    }

    public void setPathArquivo(String pathArquivo) {
        this.pathArquivo = pathArquivo;
    }

    public String getFotoArquivo() {
        return fotoArquivo;
    }

    public void setFotoArquivo(String fotoArquivo) {
        this.fotoArquivo = fotoArquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdLaudo() {
        return idLaudo;
    }

    public void setIdLaudo(Long idLaudo) {
        this.idLaudo = idLaudo;
    }

    public Long getCodLaudo() {
        return codLaudo;
    }

    public void setCodLaudo(Long codLaudo) {
        this.codLaudo = codLaudo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTmpFoto() {
        return tmpFoto;
    }

    public void setTmpFoto(String tmpFoto) {
        this.tmpFoto = tmpFoto;
    }

    public String getTipoLaudo() {
        return tipoLaudo;
    }

    public void setTipoLaudo(String tipoLaudo) {
        this.tipoLaudo = tipoLaudo;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FotoModel fotoModel = (FotoModel) o;

        if (idFoto != null ? !idFoto.equals(fotoModel.idFoto) : fotoModel.idFoto != null)
            return false;
        if (idCliente != null ? !idCliente.equals(fotoModel.idCliente) : fotoModel.idCliente != null)
            return false;
        if (nomeFoto != null ? !nomeFoto.equals(fotoModel.nomeFoto) : fotoModel.nomeFoto != null)
            return false;
        if (pathArquivo != null ? !pathArquivo.equals(fotoModel.pathArquivo) : fotoModel.pathArquivo != null)
            return false;
        if (fotoArquivo != null ? !fotoArquivo.equals(fotoModel.fotoArquivo) : fotoModel.fotoArquivo != null)
            return false;
        if (descricao != null ? !descricao.equals(fotoModel.descricao) : fotoModel.descricao != null)
            return false;
        if (idLaudo != null ? !idLaudo.equals(fotoModel.idLaudo) : fotoModel.idLaudo != null)
            return false;
        if (codLaudo != null ? !codLaudo.equals(fotoModel.codLaudo) : fotoModel.codLaudo != null)
            return false;
        if (status != null ? !status.equals(fotoModel.status) : fotoModel.status != null)
            return false;
        if (tmpFoto != null ? !tmpFoto.equals(fotoModel.tmpFoto) : fotoModel.tmpFoto != null)
            return false;
        if (tipoLaudo != null ? !tipoLaudo.equals(fotoModel.tipoLaudo) : fotoModel.tipoLaudo != null)
            return false;
        return sync != null ? sync.equals(fotoModel.sync) : fotoModel.sync == null;

    }

    @Override
    public int hashCode() {
        int result = idFoto != null ? idFoto.hashCode() : 0;
        result = 31 * result + (idCliente != null ? idCliente.hashCode() : 0);
        result = 31 * result + (nomeFoto != null ? nomeFoto.hashCode() : 0);
        result = 31 * result + (pathArquivo != null ? pathArquivo.hashCode() : 0);
        result = 31 * result + (fotoArquivo != null ? fotoArquivo.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (idLaudo != null ? idLaudo.hashCode() : 0);
        result = 31 * result + (codLaudo != null ? codLaudo.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (tmpFoto != null ? tmpFoto.hashCode() : 0);
        result = 31 * result + (tipoLaudo != null ? tipoLaudo.hashCode() : 0);
        result = 31 * result + (sync != null ? sync.hashCode() : 0);
        return result;
    }
}
