package model;

public class FuncionarioPJ extends Funcionario {

    private double valorProjeto;
    private int quantidadeProjetos;

    public FuncionarioPJ(int id, String nome, String cpf,
                         double valorProjeto, int quantidadeProjetos) {

        super(id, nome, cpf, 0.0);

        this.valorProjeto = valorProjeto;
        this.quantidadeProjetos = quantidadeProjetos;
    }

    public double getValorProjeto() {
        return valorProjeto;
    }

    public int getQuantidadeProjetos() {
        return quantidadeProjetos;
    }
}