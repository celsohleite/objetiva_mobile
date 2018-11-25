package zi.objetivamobile.model;

/**
 * Created by leite on 02/11/17.
 */

public class CategoriaModel {

    private Long id;
    private Long numLaudo;
    private int codCategoria;
    private String descCategoria;
    private int ordemCategoria;
    private String tipoVistoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumLaudo() {
        return numLaudo;
    }

    public void setNumLaudo(Long numLaudo) {
        this.numLaudo = numLaudo;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }

    public int getOrdemCategoria() {
        return ordemCategoria;
    }

    public void setOrdemCategoria(int ordemCategoria) {
        this.ordemCategoria = ordemCategoria;
    }

    public String getTipoVistoria() {
        return tipoVistoria;
    }

    public void setTipoVistoria(String tipoVistoria) {
        this.tipoVistoria = tipoVistoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriaModel that = (CategoriaModel) o;

        if (codCategoria != that.codCategoria) return false;
        if (ordemCategoria != that.ordemCategoria) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (numLaudo != null ? !numLaudo.equals(that.numLaudo) : that.numLaudo != null)
            return false;
        if (descCategoria != null ? !descCategoria.equals(that.descCategoria) : that.descCategoria != null)
            return false;
        return tipoVistoria != null ? tipoVistoria.equals(that.tipoVistoria) : that.tipoVistoria == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numLaudo != null ? numLaudo.hashCode() : 0);
        result = 31 * result + codCategoria;
        result = 31 * result + (descCategoria != null ? descCategoria.hashCode() : 0);
        result = 31 * result + ordemCategoria;
        result = 31 * result + (tipoVistoria != null ? tipoVistoria.hashCode() : 0);
        return result;
    }
}
