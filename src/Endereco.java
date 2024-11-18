public class Endereco extends Base{

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private int numeroCasa;
    private String complemento;
    private String pontoReferencia;
    private String cep;
    private Restaurante restaurante;

    Endereco(String rua, String bairro, String cidade, int numeroCasa, String pontoReferencia, String cep){
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numeroCasa = numeroCasa;
        this.pontoReferencia = pontoReferencia;
        this.cep = cep;

    }


    public String getRua() {
        return rua;
    }


    public String getBairro() {
        return bairro;
    }


    public String getCidade() {
        return cidade;
    }


    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


    public int getNumeroCasa() {
        return numeroCasa;
    }


    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


    public String getPontoReferencia() {
        return pontoReferencia;
    }


    public String getCep() {
        return cep;
    }


    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
