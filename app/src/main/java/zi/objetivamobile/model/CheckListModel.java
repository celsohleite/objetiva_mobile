package zi.objetivamobile.model;

/**
 * Created by leite on 27/12/17.
 */

public class CheckListModel {

    private Long idCheckList;
    private Long idLaudo;
    private Long cdLaudo;
    private int cdCategoria;
    private int idUsuario;
    private int idTitulo;
    private int idSubTitulo;
    private int idResposta;
    private String descricao;
    private String categoria;
    private String status;

    public Long getIdCheckList() {
        return idCheckList;
    }

    public void setIdCheckList(Long idCheckList) {
        this.idCheckList = idCheckList;
    }

    public Long getIdLaudo() {
        return idLaudo;
    }

    public void setIdLaudo(Long idLaudo) {
        this.idLaudo = idLaudo;
    }

    public Long getCdLaudo() {
        return cdLaudo;
    }

    public void setCdLaudo(Long cdLaudo) {
        this.cdLaudo = cdLaudo;
    }

    public int getCdCategoria() {
        return cdCategoria;
    }

    public void setCdCategoria(int cdCategoria) {
        this.cdCategoria = cdCategoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(int idTitulo) {
        this.idTitulo = idTitulo;
    }

    public int getIdSubTitulo() {
        return idSubTitulo;
    }

    public void setIdSubTitulo(int idSubTitulo) {
        this.idSubTitulo = idSubTitulo;
    }

    public int getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(int idResposta) {
        this.idResposta = idResposta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckListModel that = (CheckListModel) o;

        if (cdCategoria != that.cdCategoria) return false;
        if (idUsuario != that.idUsuario) return false;
        if (idTitulo != that.idTitulo) return false;
        if (idSubTitulo != that.idSubTitulo) return false;
        if (idResposta != that.idResposta) return false;
        if (idCheckList != null ? !idCheckList.equals(that.idCheckList) : that.idCheckList != null)
            return false;
        if (idLaudo != null ? !idLaudo.equals(that.idLaudo) : that.idLaudo != null) return false;
        if (cdLaudo != null ? !cdLaudo.equals(that.cdLaudo) : that.cdLaudo != null) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null)
            return false;
        if (categoria != null ? !categoria.equals(that.categoria) : that.categoria != null)
            return false;
        return status != null ? status.equals(that.status) : that.status == null;

    }

    @Override
    public int hashCode() {
        int result = idCheckList != null ? idCheckList.hashCode() : 0;
        result = 31 * result + (idLaudo != null ? idLaudo.hashCode() : 0);
        result = 31 * result + (cdLaudo != null ? cdLaudo.hashCode() : 0);
        result = 31 * result + cdCategoria;
        result = 31 * result + idUsuario;
        result = 31 * result + idTitulo;
        result = 31 * result + idSubTitulo;
        result = 31 * result + idResposta;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
