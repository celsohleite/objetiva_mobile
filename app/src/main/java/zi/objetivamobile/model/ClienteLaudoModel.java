package zi.objetivamobile.model;

import java.util.Date;

/**
 * Created by leite on 21/02/18.
 */

public class ClienteLaudoModel {

    private Long idCliente;
    private Long cdLaudo;
    private Long numLaudo;
    private int cdUsuario;
    private String placa;
    private String cidade;
    private String uf;
    private String chassi;
    private String identificacaoMotor;
    private String identificacaoCambio;
    private String identificacaoCarroceria;
    private int leilao;
    private String cor;
    private int anoModelo;
    private int anoFabricacao;
    private String modelo;
    private String marca;
    private int cdVistoriador;
    private int cdCliente;
    private String tipoLaudo;
    private Date criadoEm;
    private String observacao;
    private String observacaoAdicional;
    private int status;
    private int tipoCliente;
    private String nomeCliente;
    private String hodometro;
    private int showHodemetro;
    private int cdCancelamento;
    private Date dataCancelamento;
    private Long idLaudoItem;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getCdLaudo() {
        return cdLaudo;
    }

    public void setCdLaudo(Long cdLaudo) {
        this.cdLaudo = cdLaudo;
    }

    public Long getNumLaudo() {
        return numLaudo;
    }

    public void setNumLaudo(Long numLaudo) {
        this.numLaudo = numLaudo;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getIdentificacaoMotor() {
        return identificacaoMotor;
    }

    public void setIdentificacaoMotor(String identificacaoMotor) {
        this.identificacaoMotor = identificacaoMotor;
    }

    public String getIdentificacaoCambio() {
        return identificacaoCambio;
    }

    public void setIdentificacaoCambio(String identificacaoCambio) {
        this.identificacaoCambio = identificacaoCambio;
    }

    public String getIdentificacaoCarroceria() {
        return identificacaoCarroceria;
    }

    public void setIdentificacaoCarroceria(String identificacaoCarroceria) {
        this.identificacaoCarroceria = identificacaoCarroceria;
    }

    public int getLeilao() {
        return leilao;
    }

    public void setLeilao(int leilao) {
        this.leilao = leilao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCdVistoriador() {
        return cdVistoriador;
    }

    public void setCdVistoriador(int cdVistoriador) {
        this.cdVistoriador = cdVistoriador;
    }

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getTipoLaudo() {
        return tipoLaudo;
    }

    public void setTipoLaudo(String tipoLaudo) {
        this.tipoLaudo = tipoLaudo;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getHodometro() {
        return hodometro;
    }

    public void setHodometro(String hodometro) {
        this.hodometro = hodometro;
    }

    public int getShowHodemetro() {
        return showHodemetro;
    }

    public void setShowHodemetro(int showHodemetro) {
        this.showHodemetro = showHodemetro;
    }

    public int getCdCancelamento() {
        return cdCancelamento;
    }

    public void setCdCancelamento(int cdCancelamento) {
        this.cdCancelamento = cdCancelamento;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getObservacaoAdicional() {
        return observacaoAdicional;
    }

    public void setObservacaoAdicional(String observacaoAdicional) {
        this.observacaoAdicional = observacaoAdicional;
    }


    public Long getIdLaudoItem() {
        return idLaudoItem;
    }

    public void setIdLaudoItem(Long idLaudoItem) {
        this.idLaudoItem = idLaudoItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteLaudoModel that = (ClienteLaudoModel) o;

        if (cdUsuario != that.cdUsuario) return false;
        if (leilao != that.leilao) return false;
        if (anoModelo != that.anoModelo) return false;
        if (anoFabricacao != that.anoFabricacao) return false;
        if (cdVistoriador != that.cdVistoriador) return false;
        if (cdCliente != that.cdCliente) return false;
        if (status != that.status) return false;
        if (tipoCliente != that.tipoCliente) return false;
        if (showHodemetro != that.showHodemetro) return false;
        if (cdCancelamento != that.cdCancelamento) return false;
        if (idCliente != null ? !idCliente.equals(that.idCliente) : that.idCliente != null)
            return false;
        if (cdLaudo != null ? !cdLaudo.equals(that.cdLaudo) : that.cdLaudo != null) return false;
        if (numLaudo != null ? !numLaudo.equals(that.numLaudo) : that.numLaudo != null)
            return false;
        if (placa != null ? !placa.equals(that.placa) : that.placa != null) return false;
        if (cidade != null ? !cidade.equals(that.cidade) : that.cidade != null) return false;
        if (uf != null ? !uf.equals(that.uf) : that.uf != null) return false;
        if (chassi != null ? !chassi.equals(that.chassi) : that.chassi != null) return false;
        if (identificacaoMotor != null ? !identificacaoMotor.equals(that.identificacaoMotor) : that.identificacaoMotor != null)
            return false;
        if (identificacaoCambio != null ? !identificacaoCambio.equals(that.identificacaoCambio) : that.identificacaoCambio != null)
            return false;
        if (identificacaoCarroceria != null ? !identificacaoCarroceria.equals(that.identificacaoCarroceria) : that.identificacaoCarroceria != null)
            return false;
        if (cor != null ? !cor.equals(that.cor) : that.cor != null) return false;
        if (modelo != null ? !modelo.equals(that.modelo) : that.modelo != null) return false;
        if (marca != null ? !marca.equals(that.marca) : that.marca != null) return false;
        if (tipoLaudo != null ? !tipoLaudo.equals(that.tipoLaudo) : that.tipoLaudo != null)
            return false;
        if (criadoEm != null ? !criadoEm.equals(that.criadoEm) : that.criadoEm != null)
            return false;
        if (observacao != null ? !observacao.equals(that.observacao) : that.observacao != null)
            return false;
        if (observacaoAdicional != null ? !observacaoAdicional.equals(that.observacaoAdicional) : that.observacaoAdicional != null)
            return false;
        if (nomeCliente != null ? !nomeCliente.equals(that.nomeCliente) : that.nomeCliente != null)
            return false;
        if (hodometro != null ? !hodometro.equals(that.hodometro) : that.hodometro != null)
            return false;
        if (dataCancelamento != null ? !dataCancelamento.equals(that.dataCancelamento) : that.dataCancelamento != null)
            return false;
        return idLaudoItem != null ? idLaudoItem.equals(that.idLaudoItem) : that.idLaudoItem == null;

    }

    @Override
    public int hashCode() {
        int result = idCliente != null ? idCliente.hashCode() : 0;
        result = 31 * result + (cdLaudo != null ? cdLaudo.hashCode() : 0);
        result = 31 * result + (numLaudo != null ? numLaudo.hashCode() : 0);
        result = 31 * result + cdUsuario;
        result = 31 * result + (placa != null ? placa.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (uf != null ? uf.hashCode() : 0);
        result = 31 * result + (chassi != null ? chassi.hashCode() : 0);
        result = 31 * result + (identificacaoMotor != null ? identificacaoMotor.hashCode() : 0);
        result = 31 * result + (identificacaoCambio != null ? identificacaoCambio.hashCode() : 0);
        result = 31 * result + (identificacaoCarroceria != null ? identificacaoCarroceria.hashCode() : 0);
        result = 31 * result + leilao;
        result = 31 * result + (cor != null ? cor.hashCode() : 0);
        result = 31 * result + anoModelo;
        result = 31 * result + anoFabricacao;
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        result = 31 * result + cdVistoriador;
        result = 31 * result + cdCliente;
        result = 31 * result + (tipoLaudo != null ? tipoLaudo.hashCode() : 0);
        result = 31 * result + (criadoEm != null ? criadoEm.hashCode() : 0);
        result = 31 * result + (observacao != null ? observacao.hashCode() : 0);
        result = 31 * result + (observacaoAdicional != null ? observacaoAdicional.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + tipoCliente;
        result = 31 * result + (nomeCliente != null ? nomeCliente.hashCode() : 0);
        result = 31 * result + (hodometro != null ? hodometro.hashCode() : 0);
        result = 31 * result + showHodemetro;
        result = 31 * result + cdCancelamento;
        result = 31 * result + (dataCancelamento != null ? dataCancelamento.hashCode() : 0);
        result = 31 * result + (idLaudoItem != null ? idLaudoItem.hashCode() : 0);
        return result;
    }
}
