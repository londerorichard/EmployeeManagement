package model;

public class FuncionarioComissionado extends Funcionario {

    private double vendas;
    private double percentualComissao;

    public FuncionarioComissionado(int id, String nome, String cpf,
                                   double salarioBase, double vendas,
                                   double percentualComissao) {

        super(id, nome, cpf, salarioBase);

        this.vendas = vendas;
        this.percentualComissao = percentualComissao;
    }

    public double getVendas() {
        return vendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }
}