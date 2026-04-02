package Algx.GerenciamentoDeCarros;

public class RegraNegocioException extends RuntimeException {
    private String campo;

    public RegraNegocioException(String campo, String mensagem) {
        super(mensagem);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}