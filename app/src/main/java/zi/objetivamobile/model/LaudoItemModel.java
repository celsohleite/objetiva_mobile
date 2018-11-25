package zi.objetivamobile.model;

/**
 * Created by leite on 23/10/17.
 */

public class LaudoItemModel {

    private Long idLaudo;
    private Long cdLaudo;
    private Integer imageID;
    private String identificacao;
    private String vistoria;
    private String numeracao;
    private String observacao;
    private Double lat;
    private Double lng;
    private String status;
    private String nome;
    private String veiculo;
    private String placa;
    private String modelo;


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

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getVistoria() {
        return vistoria;
    }

    public void setVistoria(String vistoria) {
        this.vistoria = vistoria;
    }

    public String getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(String numeracao) {
        this.numeracao = numeracao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LaudoItemModel that = (LaudoItemModel) o;

        if (idLaudo != null ? !idLaudo.equals(that.idLaudo) : that.idLaudo != null) return false;
        if (cdLaudo != null ? !cdLaudo.equals(that.cdLaudo) : that.cdLaudo != null) return false;
        if (imageID != null ? !imageID.equals(that.imageID) : that.imageID != null) return false;
        if (identificacao != null ? !identificacao.equals(that.identificacao) : that.identificacao != null)
            return false;
        if (vistoria != null ? !vistoria.equals(that.vistoria) : that.vistoria != null)
            return false;
        if (numeracao != null ? !numeracao.equals(that.numeracao) : that.numeracao != null)
            return false;
        if (observacao != null ? !observacao.equals(that.observacao) : that.observacao != null)
            return false;
        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        if (lng != null ? !lng.equals(that.lng) : that.lng != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (veiculo != null ? !veiculo.equals(that.veiculo) : that.veiculo != null) return false;
        if (placa != null ? !placa.equals(that.placa) : that.placa != null) return false;
        return modelo != null ? modelo.equals(that.modelo) : that.modelo == null;

    }

    @Override
    public int hashCode() {
        int result = idLaudo != null ? idLaudo.hashCode() : 0;
        result = 31 * result + (cdLaudo != null ? cdLaudo.hashCode() : 0);
        result = 31 * result + (imageID != null ? imageID.hashCode() : 0);
        result = 31 * result + (identificacao != null ? identificacao.hashCode() : 0);
        result = 31 * result + (vistoria != null ? vistoria.hashCode() : 0);
        result = 31 * result + (numeracao != null ? numeracao.hashCode() : 0);
        result = 31 * result + (observacao != null ? observacao.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (veiculo != null ? veiculo.hashCode() : 0);
        result = 31 * result + (placa != null ? placa.hashCode() : 0);
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        return result;
    }
}
